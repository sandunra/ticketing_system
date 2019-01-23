package hms.ts.dao;

import hms.ts.model.Employee;

import java.util.List;

public interface EmployeeDao {

	Employee findById(int id);

	void saveEmployee(Employee employee);
	
	void deleteEmployeeById(int id);
	
	List<Employee> findAllEmployees();

	Employee findEmployeeById(int id);

	Employee findEmployeeByUsername(String userName) ;

}
