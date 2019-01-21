package hms.ts.controller;

import hms.ts.service.AuthService;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.Map;

@Controller
@RequestMapping("/user")
public class LoginController {

	@Autowired
	private AuthService authenticateService;
	// This will auto-inject the authentication service into the controller.

	public static final String isValidUser = "isValidUser";
	public static final String isAdminUser = "isAdminUser";


	private static Logger log = Logger.getLogger(LoginController.class);

	// Checks if the user credentials are valid or not.
	@RequestMapping(value = "/validate", method = RequestMethod.POST)
	public String validateUsr(@RequestParam("username")String username,@RequestParam("password")String password, ModelMap model) {
		String msg = "";
		Map<String, Boolean> userTags = authenticateService.findUser(username, password);
		log.info("Is user valid?= " + userTags.get(isValidUser));
		log.info("Is user admin?= " + userTags.get(isAdminUser));
		log.info("username?= " + username);

		if(userTags.get(isValidUser)) {
			msg = "Welcome " + username + "!";
			if(userTags.get(isAdminUser)){
				model.addAttribute("user", username);
				return "admin";
			}
			model.addAttribute("greeting", "Hi, Welcome to mysite");
			return "welcome";
		} else {
			msg = "Invalid credentials";
			return "redirect:/?error";
		}

	}

	/*@RequestMapping("/logout")
	public String logout(HttpSession session ) {
		session.invalidate();
		return "redirect:/";

	}*/
}