package br.com.wssonar.service;

import java.util.Date;
import java.util.List;

import org.resthub.common.service.CrudServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.wssonar.model.History;
import br.com.wssonar.model.WebService;
import br.com.wssonar.repository.HistoryRepository;

@Service
public class HistoryServiceImpl extends CrudServiceImpl<History, Integer, HistoryRepository> implements HistoryService {

	@Autowired
	private HistoryRepository repository;
	
	@Autowired
	public void setRepository(HistoryRepository historyRepository) {

		super.setRepository(historyRepository);
	}

	public History findDownHistoryByWebService(WebService webService) {
		return repository.findLastDownHistoryByWebService(webService);
	}
	
	public History findLastHistoryByWebService(WebService webService) {
		return repository.findLastHistoryByWebService(webService);
	}

	public List<History> findByWebServiceLimit(WebService webService) {
		
		List<History> histories =  repository.findByWebServiceLimit(webService);
		
		if(histories.size() > 10) {
			return histories.subList(0, 10);
		}
		
		return histories;
	}
	
	public List<History> findByWebServiceAndDate(WebService webService, Date date) {
		return repository.findByWebServiceAndDate(webService, date);
	}

	public List<History> findByWebServiceAndPeriod(WebService webService,
			Date date1, Date date2) {
		return repository.findByWebServiceAndPeriod(webService, date1, date2);
	}

}
