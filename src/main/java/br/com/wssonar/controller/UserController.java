package br.com.wssonar.controller;

import java.util.List;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import br.com.wssonar.jbcrypt.BCrypt;
import br.com.wssonar.model.User;
import br.com.wssonar.service.UserService;

@Controller
@RequestMapping("/user/**")
public class UserController {

	@Autowired
	private UserService userService;

	/**
	 *  Open user registration form
	 * @param modelMap
	 * @param session
	 * @return
	 */
	@RequestMapping(value = "/user", method = RequestMethod.GET)
	public String form(ModelMap modelMap, final HttpSession session) {

		User user = (User) session.getAttribute("whois_logged");
		if(user == null) {
			modelMap.addAttribute("user", new User());
			modelMap.addAttribute("message", "Por favor, logue no sistema.");
			return "/login";
		}

		modelMap.addAttribute("logged_user", user);
		modelMap.addAttribute("newUser", true);
		modelMap.addAttribute("user", new User());
		modelMap.addAttribute("page", "Registrar");

		return "/user/user_form";
	}

	/**
	 *  Open user update form
	 * @param userId
	 * @param modelMap
	 * @param session
	 * @return
	 */
	@RequestMapping(value = "/user/{user_id}", method = RequestMethod.POST)
	public String update(@PathVariable("user_id") Integer userId, ModelMap modelMap, final HttpSession session) {
		
		User loggedUser = (User) session.getAttribute("whois_logged");
		if(loggedUser == null) {
			modelMap.addAttribute("user", new User());
			modelMap.addAttribute("message", "Por favor, logue no sistema.");
			return "/login";
		}

		User user = userService.findById(userId);

		session.setAttribute("update", true);

		modelMap.addAttribute("logged_user", loggedUser);
		modelMap.addAttribute("message", "Atualizando o usuário " + user.getUsName() + ".");
		modelMap.addAttribute("newUser", false);
		modelMap.addAttribute("user", user);
		modelMap.addAttribute("page", "Editar");

		return "/user/user_form";
	}

	/**
	 *  Open user update form
	 * @param userId
	 * @param modelMap
	 * @param session
	 * @return
	 */
	@RequestMapping(value = "/user/{user_id}", method = RequestMethod.DELETE)
	public String delete(@PathVariable("user_id") Integer userId, ModelMap modelMap, final HttpSession session) {

		User user = (User) session.getAttribute("whois_logged");
		if(user == null) {
			modelMap.addAttribute("user", new User());
			modelMap.addAttribute("message", "Por favor, logue no sistema.");
			return "/login";
		}

		userService.delete(userId);

		List<User> users = userService.findAll();

		modelMap.addAttribute("message", "Usuário deletado com sucesso!");
		modelMap.addAttribute("users", users);
		modelMap.addAttribute("logged_user", user);
		return "/user/user_list";
	}

	/**
	 *  List users
	 * @param modelMap
	 * @param session
	 * @return
	 */
	@RequestMapping(value = "/user/list", method = RequestMethod.GET)
	public String list(ModelMap modelMap, final HttpSession session) {

		User user = (User) session.getAttribute("whois_logged");
		if(user == null) {
			modelMap.addAttribute("user", new User());
			modelMap.addAttribute("message", "Por favor, logue no sistema.");
			return "/login";
		}

		List<User> users = userService.findAll();

		modelMap.addAttribute("logged_user", user);
		modelMap.addAttribute("users", users);

		return "/user/user_list";
	}

	/**
	 *  Persist and update user
	 * @param user
	 * @param modelMap
	 * @param session
	 * @return
	 */
	@RequestMapping(value = "/user", method = RequestMethod.POST)
	public String create(@ModelAttribute("user") User user, final ModelMap modelMap, final HttpSession session) {

		User loggedUser = (User) session.getAttribute("whois_logged");
		Integer loggedUserId = 0; 
		if(loggedUser != null) {
			loggedUserId = loggedUser.getUsId();
		}

		modelMap.addAttribute("message", "Usuário editado com sucesso!");

		Boolean update = (Boolean) session.getAttribute("update");
		if(update == null || !update) {
			String encryptedPassword = BCrypt.hashpw(user.getUsPassword(), BCrypt.gensalt());
			user.setUsPassword(encryptedPassword);

			modelMap.addAttribute("message", "Usuário criado com sucesso!");
		}

		user = userService.create(user);

		session.setAttribute("update", false);

		List<User> users = userService.findAll();
		modelMap.addAttribute("users", users);
		modelMap.addAttribute("logged_user", loggedUser);

		// Checks if the updated user is the same as the current user
		if(loggedUserId == user.getUsId()) {
			session.setAttribute("logged_user", user);
		}

		return "/user/user_list";
	}

}
