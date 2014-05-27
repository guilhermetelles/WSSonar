package br.com.wssonar.controller;

import java.util.Timer;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import br.com.wssonar.jbcrypt.BCrypt;
import br.com.wssonar.model.User;
import br.com.wssonar.service.UserService;
import br.com.wssonar.sonar.WebServiceSonar;

@Controller
@RequestMapping("/login/**")
public class LoginController {

	@Autowired
	private UserService userService;
	
	private static Integer var = 0;

	@RequestMapping(value = "/login", method = RequestMethod.GET)
	public String login(ModelMap modelMap) {

		modelMap.addAttribute("user", new User());

		return "/login";
	}

	@RequestMapping(value = "/login", method = RequestMethod.POST)
	public String login(@ModelAttribute("user") User user, ModelMap modelMap, final HttpSession session) {

		String password = user.getUsPassword();

		user = userService.findByUsername(user.getUsUsername());

		if(user != null) {
			
			//Checks if the password is valid
			if(BCrypt.checkpw(password, user.getUsPassword())){

				session.setAttribute("whois_logged", user);
				
				if(var == 0) {
					
					System.out.println("TIMER EXECUTING!");
					
					Timer timer = new Timer();
					
					@SuppressWarnings("resource")
					ApplicationContext context = new ClassPathXmlApplicationContext("classpath:spring/application-config.xml");
					WebServiceSonar webServiceSonar = (WebServiceSonar) context.getBean("webServiceSonar");
					timer.scheduleAtFixedRate(webServiceSonar, 00, 10000);
					
					var = 1;
				}

				return "redirect:/sonar";
			}
		}

		modelMap.addAttribute("message", "Usuário ou senha inválidos!");

		return "/login";

	}
	
	@RequestMapping(value = "/login/logout", method = RequestMethod.GET)
	public String logout(ModelMap modelMap, final HttpSession session) {

		session.invalidate(); 
		
		return "redirect:/login";
	}
	
	

}