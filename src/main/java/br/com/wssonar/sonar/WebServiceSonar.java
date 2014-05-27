package br.com.wssonar.sonar;

import java.util.Date;
import java.util.List;
import java.util.TimerTask;

import org.apache.commons.lang.time.DurationFormatUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import br.com.wssonar.mail.MailSender;
import br.com.wssonar.model.History;
import br.com.wssonar.model.WebService;
import br.com.wssonar.service.HistoryService;
import br.com.wssonar.service.WebServiceService;

@Component
public class WebServiceSonar extends TimerTask{

	@Autowired
	private HistoryService historyService;
	@Autowired
	private WebServiceService webServiceService;

	@Override
	public void run() {

		WebService webService = null;
		List<WebService> webServices = webServiceService.findAll();

		try {
			
			for(final WebService ws : webServices) {
				
				webService = ws;

				WebServiceTester.testWebService(ws.getWsId());

				success(ws);
			}
		} catch (Exception e) {
			e.printStackTrace();
			
			failure(webService, e);
		} 

	}

	/**
	 * In case the web service test in a success.
	 * 1. Checks if the web service was off-line.
	 * 2. Persist status
	 * 3. Send e-mails
	 * @param webService
	 */
	private void success(WebService webService) {
		History history = historyService.findDownHistoryByWebService(webService);
		
		if(history != null) {
			history.setHtBackOnline(new Date());
			
			historyService.update(history);
			
			String timeOffline = DurationFormatUtils.formatPeriod(history.getHtDownDate().getTime(), new Date().getTime(), "HH 'h' mm 'm' ss 's'");
			
			MailSender.backOnlineEmail(webService, timeOffline);
		}

		if(webService.getWsStatus() == false) {
			webService.setWsStatus(true);
		}

		int countPositive = webService.getWsCountPositive();
		int countTotal = webService.getWsCountTotal();
		
		webService.setWsCountPositive(++countPositive);
		webService.setWsCountTotal(++countTotal);
		webServiceService.update(webService);
	}

	/**
	 * In case the web service test in a failure.
	 * 1. Checks if the web service was on-line.
	 * 2. Persist status and error
	 * 3. Send e-mails
	 * @param webService
	 */
	private void failure(WebService webService, Exception exception) {

		// Update Web Service
		if(webService.getWsStatus() == true) {
			webService.setWsStatus(false);
		}
		
		int countNegative = webService.getWsCountNegative();
		int countTotal = webService.getWsCountTotal();
		
		webService.setWsCountNegative(++countNegative);
		webService.setWsCountTotal(++countTotal);
		webServiceService.update(webService);

		// Insert History
		History history = historyService.findDownHistoryByWebService(webService);

		if(history == null) {

			history = new History();
			String errorMessage = exception.getMessage();
			if(errorMessage.length() > 100) {
				errorMessage = errorMessage.substring(0, 100);
			}
			history.setHtErrorResult(errorMessage);
			history.setHtDownDate(new Date());
			history.setWebService(webService);
			
			historyService.update(history);
		} 
		
		MailSender.errorEmail(webService);

	}
	
}
