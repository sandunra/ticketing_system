package hms.ts.service;

import hms.ts.model.Employee;
import org.apache.log4j.Logger;
import org.springframework.orm.hibernate5.HibernateTemplate;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class AuthService {

	private HibernateTemplate hibernateTemplate;
	private static Logger log = Logger.getLogger(AuthService.class);

	private AuthService() { }

	public void setHibernateTemplate(HibernateTemplate hibernateTemplate) {
		this.hibernateTemplate = hibernateTemplate;
	}

	@SuppressWarnings( { "unchecked", "deprecation" } )
	public Map<String, Boolean> findUser(String uname,String upwd) {
		Map<String, Boolean> userTags = new HashMap<String, Boolean>();
		log.info("Checking the user in the database");
		boolean isValidUser = false;
		boolean isAdminUser = false;
		String sqlQuery = "from Employee emp where emp.username=? and emp.password=?";
		try {
			List<Employee> userObj = (List<Employee>) hibernateTemplate.find(sqlQuery, uname, upwd);
			if(userObj != null && userObj.size() > 0) {
				List<GrantedAuthority> authorities = new ArrayList<>();
				authorities.add(new SimpleGrantedAuthority(userObj.get(0).getRole().getTitle()));
				log.info("Id= " + userObj.get(0).getId() + ", Name= " + userObj.get(0).getName() + ", Password= " + userObj.get(0).getPassword());
				isValidUser = true;
				if(userObj.get(0).getRole().getTitle().trim().equalsIgnoreCase("Manager")){
					isAdminUser = true;
				}
			}
		} catch(Exception e) {
			log.error("An error occurred while fetching the user details from the database", e);
		}

		userTags.put("isValidUser", isValidUser);
		userTags.put("isAdminUser", isAdminUser);

		return userTags;
	}

}