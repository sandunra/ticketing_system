package hms.ts.service;

import hms.ts.model.Employee;

import java.util.List;

public interface EmployeeService {

	Employee findById(int id);
	
	void saveEmployee(Employee employee);
	
	void updateEmployee(Employee employee);
	
	void deleteEmployeeById(int id);

	List<Employee> findAllEmployees(); 

	Employee findEmployeeById(int id);

	Employee findEmployeeByUsername(String username);

	boolean isEmployeeUsernameUnique(String username, int id);

	public  boolean isValidEmailAddress(String email);
	
}
