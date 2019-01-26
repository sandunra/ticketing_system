package hms.ts.service;

import hms.ts.dao.EmployeeDao;
import hms.ts.model.Employee;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.mail.internet.AddressException;
import javax.mail.internet.InternetAddress;
import java.util.List;

@Service("employeeService")
@Transactional
public class EmployeeServiceImpl implements EmployeeService {

	@Autowired
	private EmployeeDao dao;
	
	public Employee findById(int id) {
		return dao.findById(id);
	}

	public void saveEmployee(Employee employee) {
		dao.saveEmployee(employee);

		/*Employee entity = dao.findById(employee.getId());
		if(entity!=null){
			entity.setName(employee.getName());
			entity.setEmail(employee.getEmail());
			entity.setPassword(employee.getPassword());
			entity.setUsername(employee.getUsername());
			entity.setRole(employee.getRole());
		}

		dao.saveEmployee(entity);*/


	}

	/*
	 * Since the method is running with Transaction, No need to call hibernate update explicitly.
	 * Just fetch the entity from db and update it with proper values within transaction.
	 * It will be updated in db once transaction ends. 
	 */
	public void updateEmployee(Employee employee) {
		Employee entity = dao.findById(employee.getId());
		if(entity!=null){
			entity.setName(employee.getName());
			entity.setEmail(employee.getEmail());
			entity.setPassword(employee.getPassword());
			entity.setUsername(employee.getUsername());
			entity.setRole(employee.getRole());
		}
	}

	public void deleteEmployeeById(int id) {
		dao.deleteEmployeeById(id);
	}
	
	public List<Employee> findAllEmployees() {
		return dao.findAllEmployees();
	}

	public Employee findEmployeeById(int id) {
		return dao.findEmployeeById(id);
	}

	public Employee findEmployeeByUsername(String username) {
		return dao.findEmployeeByUsername(username);
	}

	public boolean isEmployeeUsernameUnique(String username, int id) {
		Employee employee = findEmployeeByUsername(username);
		return ( employee == null || ((username != null) && (employee.getId() == id)));
	}

	public boolean isValidEmailAddress(String email) {
		boolean result = true;
		try {
			InternetAddress emailAddr = new InternetAddress(email);
			emailAddr.validate();
		} catch (AddressException ex) {
			result = false;
		}
		return result;
	}

	/*protected Map getRoleList(HttpServletRequest request) throws Exception {
		Map roleList = new HashMap();
		Map<Integer,String> role = new LinkedHashMap<Integer,String>();
		role.put(0, "Manager");
		role.put(1, "Tech Lead");
		role.put(2, "Senior Software Engineer");
		role.put(3, "Software Engineer");
		role.put(4, "Senior Software Engineer");
		role.put(5, "Associate Software Engineer");
		role.put(6, "Trainee Software Engineer");
		role.put(7, "Marketing Officer");
		roleList.put("countryList", role);
		return roleList;
	}*/
	
}
