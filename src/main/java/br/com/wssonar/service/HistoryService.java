package br.com.wssonar.service;

import java.util.Date;
import java.util.List;

import org.resthub.common.service.CrudService;

import br.com.wssonar.model.History;
import br.com.wssonar.model.WebService;

public interface HistoryService extends CrudService<History, Integer>{
	
	History findDownHistoryByWebService(WebService webService);
	
	History findLastHistoryByWebService(WebService webService);
	
	List<History> findByWebServiceLimit(WebService webService);
	
	List<History> findByWebServiceAndDate(WebService webService, Date date);
	
	List<History> findByWebServiceAndPeriod(WebService webService, Date date1, Date date2);

}
