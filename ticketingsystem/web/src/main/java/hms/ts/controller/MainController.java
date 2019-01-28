package hms.ts.controller;

import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.support.SessionStatus;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.security.Principal;

@Controller
public class MainController {

	@RequestMapping(value = { "/", "/home" }, method = RequestMethod.GET)
	public String homePage(ModelMap model, HttpServletRequest request) {


		if ((boolean)request.getSession().getAttribute("isAdmin")) {
			return "admin";
		} else {
			return "user" ;
		}
	}

	@RequestMapping(value = "/admin", method = RequestMethod.GET)
	public String adminPage(ModelMap model) {
		model.addAttribute("user", getUser());
		return "admin";
	}

	@RequestMapping(value = "/user", method = RequestMethod.GET)
	public String userPage(ModelMap model, Principal principal) {
//		String userName = principal.getName();
		model.addAttribute("user", getUser());
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
	public String logoutPage (HttpServletRequest request, HttpServletResponse response, SessionStatus session) throws IOException {

		response.setContentType("text/html");
		PrintWriter out = response.getWriter();
//		HttpSession session = request.getSession(false);
//		session.setAttribute("username", null);
//		session.removeAttribute("username");
//		session.removeAttribute("isAdmin");
//		session.getMaxInactiveInterval();
		session.setComplete();
		request.getSession().invalidate();
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