package br.com.wssonar.controller;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import br.com.wssonar.business.ChartGenerator;
import br.com.wssonar.business.ReportGenerator;
import br.com.wssonar.model.History;
import br.com.wssonar.model.User;
import br.com.wssonar.model.WebService;
import br.com.wssonar.service.HistoryService;
import br.com.wssonar.service.WebServiceService;
import br.com.wssonar.util.HistoryUtil;

@Controller
@RequestMapping("/history/**")
public class HistoryController {

	@Autowired
	private HistoryService historyService;

	@Autowired
	private WebServiceService webServiceService;
	
	ApplicationContext context = new ClassPathXmlApplicationContext("classpath:spring/application-config.xml");

	@RequestMapping(value="/history/{webServiceId}", method=RequestMethod.POST)
	public String list(@PathVariable("webServiceId") Integer webServiceId, 
			ModelMap modelMap, final HttpSession session) {

		User user = (User) session.getAttribute("whois_logged");
		if(user == null) {
			modelMap.addAttribute("user", new User());
			modelMap.addAttribute("message", "Por favor, logue no sistema.");
			return "/login";
		}

		WebService webService = webServiceService.findById(webServiceId);

		List<History> histories = historyService.findByWebServiceLimit(webService);
		String tempoTotalOffline = HistoryUtil.defineDowntime(histories);

		ChartGenerator chartGenerator = (ChartGenerator) context.getBean("chartGenerator");
		Map<String, String> chartInfo = chartGenerator.generateChartInfo(webService);

		modelMap.addAttribute("tempoTotalOffline", tempoTotalOffline);
		modelMap.addAttribute("chartInfo", chartInfo);
		modelMap.addAttribute("histories", histories);
		modelMap.addAttribute("webService", webService);
		modelMap.addAttribute("logged_user", user);

		return "/history/history_list";
	}
	
	@RequestMapping(value="/history/report", method=RequestMethod.GET)
	public String chart(ModelMap modelMap, final HttpSession session) {

		User user = (User) session.getAttribute("whois_logged");
		//		User user = new User();
		if(user == null) {
			modelMap.addAttribute("user", new User());
			modelMap.addAttribute("message", "Por favor, logue no sistema.");
			return "/login";
		}
		
		return "/history/history_report";
	}

	@RequestMapping(value="/history/report", method=RequestMethod.POST)
	public String generate(ModelMap modelMap, final HttpSession session, final HttpServletRequest request) {

		User user = (User) session.getAttribute("whois_logged");
		if(user == null) {
			modelMap.addAttribute("user", new User());
			modelMap.addAttribute("message", "Por favor, logue no sistema.");
			return "/login";
		}
		
		int webServiceId = Integer.parseInt(request.getParameter("webService"));
		
		String datepicker1 = request.getParameter("datepicker1");
		String datepicker2 = request.getParameter("datepicker2");
		
		SimpleDateFormat formater = new SimpleDateFormat("dd/MM/yyyy");
		Date date1 = null;
		Date date2 = null;
		try {
			date1 = formater.parse(datepicker1);
			date2 = formater.parse(datepicker2);
		} catch (ParseException e) {
			e.printStackTrace();
		}
		
		WebService webService = webServiceService.findById(webServiceId);

		List<History> histories = historyService.findByWebServiceAndPeriod(webService, date1, date2);
		String tempoTotalOffline = HistoryUtil.defineDowntime(histories);

		ChartGenerator chartGenerator = (ChartGenerator) context.getBean("chartGenerator");
		Map<String, String> chartInfo = chartGenerator.generateChartInfoByPeriod(webService, date1, date2);

		modelMap.addAttribute("datepicker1", datepicker1);
		modelMap.addAttribute("datepicker2", datepicker2);
		modelMap.addAttribute("tempoTotalOffline", tempoTotalOffline);
		modelMap.addAttribute("chartInfo", chartInfo);
		modelMap.addAttribute("histories", histories);
		modelMap.addAttribute("webService", webService);
		modelMap.addAttribute("logged_user", user);

		return "/history/history_report";
	}
	
	@RequestMapping(value="/history/report/download", method=RequestMethod.POST)
	public HttpEntity<byte[]> download(ModelMap modelMap, final HttpServletRequest request) {

		int webServiceId = Integer.parseInt(request.getParameter("hiddenWebService"));
		
		String datepicker1 = request.getParameter("hiddenDatepicker1");
		String datepicker2 = request.getParameter("hiddenDatepicker2");
		
		SimpleDateFormat formater = new SimpleDateFormat("dd/MM/yyyy");
		Date date1 = null;
		Date date2 = null;
		try {
			date1 = formater.parse(datepicker1);
			date2 = formater.parse(datepicker2);
		} catch (ParseException e) {
			e.printStackTrace();
		}
		
		WebService webService = webServiceService.findById(webServiceId);

		List<History> histories = historyService.findByWebServiceAndPeriod(webService, date1, date2);
		
		ReportGenerator reportGenerator = (ReportGenerator) context.getBean("reportGenerator");
		byte[] sheetFile = reportGenerator.createXlsReport(webService, histories);
		
		HttpHeaders header = new HttpHeaders();
	    header.setContentType(new MediaType("application", "xls"));
	    header.set("Content-Disposition",
	                   "attachment; filename=relatorio_de_quedas.xls");
	    header.setContentLength(sheetFile.length);

	    return new HttpEntity<byte[]>(sheetFile, header);
	}

}
