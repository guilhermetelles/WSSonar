package br.com.wssonar.service;

import org.resthub.common.service.CrudServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.wssonar.model.WebService;
import br.com.wssonar.repository.WebServiceRepository;

@Service
public class WebServiceServiceImpl extends CrudServiceImpl<WebService, Integer, WebServiceRepository> implements WebServiceService {

	@Autowired
	private WebServiceRepository repository;
	
	@Autowired
	public void setRepository(WebServiceRepository webServiceRepository) {

		super.setRepository(webServiceRepository);
	}
	
}
