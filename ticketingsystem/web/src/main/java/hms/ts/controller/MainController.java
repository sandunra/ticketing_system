package hms.ts.controller;

import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.web.authentication.logout.SecurityContextLogoutHandler;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.security.Principal;

@Controller
public class MainController {

	@RequestMapping(value = { "/", "/home" }, method = RequestMethod.GET)
	public String homePage(ModelMap model) {
		model.addAttribute("greeting", " Welcome to Ticketing System");
		return "welcome";
	}

	@RequestMapping(value = "/admin", method = RequestMethod.GET)
	public String adminPage(ModelMap model) {
		model.addAttribute("user", getUser());
		return "admin";
	}

	@RequestMapping(value = "/user", method = RequestMethod.GET)
	public String userPage(ModelMap model, Principal principal) {
		String userName = principal.getName();
		model.addAttribute("user", userName);
		return "user";
	}

	@RequestMapping(value = "/Access_Denied", method = RequestMethod.GET)
	public String accessDeniedPage(ModelMap model) {
		model.addAttribute("user", getUser());
		return "accessDenied";
	}

	@RequestMapping(value = "/login", method = RequestMethod.GET)
	public String loginPage() {
		return "login";
	}

	@RequestMapping(value="/logout", method = RequestMethod.GET)
	public String logoutPage (HttpServletRequest request, HttpServletResponse response) {
		Authentication auth = SecurityContextHolder.getContext().getAuthentication();
		HttpSession session = request.getSession();
		if (auth != null){    
			new SecurityContextLogoutHandler().logout(request, response, auth);
			session.invalidate();
		}
		return "redirect:/?logout";//You can redirect wherever you want, but generally it's a good idea to show login screen again.
	}

	private String getUser(){
		String userName = null;
		Object principal = SecurityContextHolder.getContext().getAuthentication().getPrincipal();

		if (principal instanceof UserDetails) {
			userName = ((UserDetails)principal).getUsername();
		} else {
			userName = principal.toString();
		}
		return userName;
	}

}