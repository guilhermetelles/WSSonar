package br.com.wssonar.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import br.com.wssonar.model.User;

/**
 * User class Repository
 * 
 * All the methods all self-explanatory 
 */
public interface UserRepository extends JpaRepository<User, Integer>{
	
	User findByUsUsername(String usUsername);

}
