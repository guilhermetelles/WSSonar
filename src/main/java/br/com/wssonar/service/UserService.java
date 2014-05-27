package br.com.wssonar.service;

import org.resthub.common.service.CrudService;

import br.com.wssonar.model.User;

public interface UserService extends CrudService<User, Integer>{
	
	User findByUsername(String username);

}
