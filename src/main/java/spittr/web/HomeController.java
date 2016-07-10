package spittr.web;

import static org.springframework.web.bind.annotation.RequestMethod.GET;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping({"/", "/home", "/homepage"})
public class HomeController {
	
	@RequestMapping(method = GET)
	public String home() {
		return "home";
	}
	
	@RequestMapping(value = "/login", method = GET)
	public String login() {
		return "login";
	}
}