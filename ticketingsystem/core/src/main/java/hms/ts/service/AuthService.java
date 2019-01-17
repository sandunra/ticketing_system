package hms.ts.service;

import hms.ts.model.Employee;
import org.apache.log4j.Logger;
import org.springframework.orm.hibernate5.HibernateTemplate;

import java.util.List;

public class AuthService {

	private HibernateTemplate hibernateTemplate;
	private static Logger log = Logger.getLogger(AuthService.class);

	private AuthService() { }

	public void setHibernateTemplate(HibernateTemplate hibernateTemplate) {
		this.hibernateTemplate = hibernateTemplate;
	}

	@SuppressWarnings( { "unchecked", "deprecation" } )
	public boolean findUser(String uname,String upwd) {
		log.info("Checking the user in the database");
		boolean isValidUser = false;
		String sqlQuery = "from employee emp where emp.username=? and emp.password=?";
		try {
			List<Employee> userObj = (List<Employee>) hibernateTemplate.find(sqlQuery, uname, upwd);
			if(userObj != null && userObj.size() > 0) {
				log.info("Id= " + userObj.get(0).getId() + ", Name= " + userObj.get(0).getName() + ", Password= " + userObj.get(0).getPassword());
				isValidUser = true;
			}
		} catch(Exception e) {
			isValidUser = false;
			log.error("An error occurred while fetching the user details from the database", e);	
		}
		return isValidUser;
	}
}