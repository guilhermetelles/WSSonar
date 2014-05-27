package br.com.wssonar.controller;

import java.util.List;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import br.com.wssonar.model.History;
import br.com.wssonar.model.User;
import br.com.wssonar.model.WebService;
import br.com.wssonar.service.HistoryService;
import br.com.wssonar.service.WebServiceService;

@Controller
@RequestMapping("/sonar/**")
public class WebServiceController {
	
	@Autowired
	private WebServiceService webServiceService;
	
	@Autowired
	private HistoryService historyService;

	@RequestMapping(value = "/sonar", method = RequestMethod.GET)
	public String list(ModelMap modelMap, final HttpSession session) {
		
		User user = (User) session.getAttribute("whois_logged");
		if(user == null) {
			modelMap.addAttribute("user", new User());
			modelMap.addAttribute("message", "Por favor, logue no sistema.");
			return "/login";
		}

		List<WebService> webServices = webServiceService.findAll();
		for (WebService webService : webServices) {
			History lastHistory = historyService.findLastHistoryByWebService(webService);
			webService.setLastHistory(lastHistory);
		}
		
		modelMap.addAttribute("webServices", webServices);
		modelMap.addAttribute("logged_user", user);
		
		return "/webService/sonar";
	}
	
}
