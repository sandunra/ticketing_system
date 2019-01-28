package hms.ts.controller;

import hms.ts.service.AuthService;
import hms.ts.service.EmployeeService;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.SessionAttributes;

import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Map;

@Controller
@SessionAttributes("username")
public class LoginController extends HttpServlet {

	@Autowired
	private AuthService authenticateService;
	// This will auto-inject the authentication service into the controller.

	@Autowired
	EmployeeService employeeService;

	public static final String isValidUser = "isValidUser";
	public static final String isAdminUser = "isAdminUser";


	private static Logger log = Logger.getLogger(LoginController.class);

//	@ModelAttribute("username")

	// Checks if the user credentials are valid or not.
	@RequestMapping(value = "/home", method = RequestMethod.POST)
	public String  validateUsr(ModelMap model, HttpServletRequest request, HttpServletResponse response) throws IOException {
		String msg = "";
		boolean isAdmin = false;
		response.setContentType("text/html");
		PrintWriter out = response.getWriter();
		String username = request.getParameter("username");
		String password = request.getParameter("password");
		Map<String, Boolean> userTags = authenticateService.findUser(username, password);
		log.info("Is user valid?= " + userTags.get(isValidUser));
		log.info("Is user admin?= " + userTags.get(isAdminUser));
		log.info("username?= " + username);

		if(userTags.get(isValidUser)) {
			msg = "Welcome " + username + "!";
			model.addAttribute("user", username);
			HttpSession session = request.getSession();
			session.setAttribute("username", username);
			session.setAttribute("id", employeeService.findEmployeeByUsername(username).getId());
			session.setMaxInactiveInterval(240); // 240 seconds

			if(userTags.get(isAdminUser)){
				isAdmin = true;
				session.setAttribute("isAdmin", isAdmin);
				return "admin";
			}
			session.setAttribute("isAdmin", isAdmin);
			return "user";
		} else {
			msg = "Invalid credentials";
			return "redirect:/?error";
		}

	}


//	@RequestMapping(value="/logout", method = RequestMethod.GET)
//	public String logoutPage (HttpServletRequest request, HttpServletResponse response, SessionStatus session) throws IOException {
//
//		response.setContentType("text/html");
//		PrintWriter out = response.getWriter();
//		out.println("thank you!!, Your session was destroyed successfully!!");
//		/*SessionStatus session = request.getSession(false);
//		session.setAttribute("username", null);
//		session.removeAttribute("username");
//		session.removeAttribute("isAdmin");
//		session.getMaxInactiveInterval();*/
//		session.setComplete();
//		request.getSession().invalidate();
//		return "redirect:/?logout";//You can redirect wherever you want, but generally it's a good idea to show login screen again.
//	}

	/*@RequestMapping("/logout")
	public String logout(HttpSession session ) {
		session.setAttribute("username", null);
		session.removeAttribute("username");
		session.removeAttribute("isAdmin");
		session.getMaxInactiveInterval();
		session.invalidate();
		return "redirect:/?logout";

	}*/
}