package br.com.wssonar.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import br.com.wssonar.model.WebService;

/**
 * Web Service class Repository
 * 
 */
public interface WebServiceRepository extends JpaRepository<WebService, Integer>{

}
