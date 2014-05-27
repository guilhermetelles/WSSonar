package br.com.wssonar.service;

import org.resthub.common.service.CrudServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.wssonar.model.User;
import br.com.wssonar.repository.UserRepository;

@Service
public class UserServiceImpl extends CrudServiceImpl<User, Integer, UserRepository> implements UserService {

	@Autowired
	private UserRepository repository;
	
	@Autowired
	public void setRepository(UserRepository userRepository) {

		super.setRepository(userRepository);
	}

	public User findByUsername(String username) {
		return repository.findByUsUsername(username);
	}
	
}
