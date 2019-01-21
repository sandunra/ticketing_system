package hms.ts.service;

import hms.ts.model.Employee;
import hms.ts.model.Role;

import java.util.List;

public interface EmployeeService {

	Employee findById(int id);
	
	void saveEmployee(Employee employee);
	
	void updateEmployee(Employee employee);
	
	void deleteEmployeeById(int id);

	List<Employee> findAllEmployees(); 

	Employee findEmployeeById(int id);

	boolean isEmployeeIdUnique(Integer id);

	List<Role> getAllRoles();
	
}
