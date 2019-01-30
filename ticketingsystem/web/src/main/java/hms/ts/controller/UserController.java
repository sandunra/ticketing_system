package hms.ts.controller;

import hms.ts.model.Employee;
import hms.ts.model.Role;
import hms.ts.service.EmployeeService;
import hms.ts.service.RoleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@Controller
@RequestMapping("/employee")
public class UserController {

	@Autowired
	EmployeeService employeeService;

	@Autowired
	RoleService roleService;
	
	@Autowired
	MessageSource messageSource;


	@RequestMapping(value = { "", "/list" }, method = RequestMethod.GET)
	public String listEmployees(ModelMap model) {

		List<Employee> employees = employeeService.findAllEmployees();
		model.addAttribute("employees", employees);
		return "allemployees";
	}

	@RequestMapping(value = { "/new" }, method = RequestMethod.GET)
	public String newEmployee(ModelMap model) {
		Employee employee = new Employee();
		model.addAttribute("employee", employee);
		model.addAttribute("edit", false);
		return "addEmployee";
	}

	@RequestMapping(value = "/new", method = RequestMethod.POST)
	public String saveEmployee(@Valid Employee employee, BindingResult result,
							   @RequestParam("name") String name,
							   @RequestParam("email") String email,
							   @RequestParam("role.id") int roleId,
							   @RequestParam("username") String username,
							   @RequestParam("password") String password, ModelMap model) {

		if(roleId == 0){
			FieldError roleError =new FieldError("employee","role","*Employee role need to select here.");
			result.addError(roleError);
			return "addEmployee";
		}

		if(!employeeService.isValidEmailAddress(email)){
			FieldError emailError =new FieldError("employee","email","*Not a valid email address.");
			result.addError(emailError);
			return "addEmployee";
		}

		if(!employeeService.isEmployeeUsernameUnique(username,0)){
			FieldError usernameError =new FieldError("employee","username","*Username already has been taken someone.");
			result.addError(usernameError);
			return "addEmployee";
		}

		employee.setName(name);
		employee.setEmail(email);
		employee.setRole(roleService.findRoleById(roleId));
		employee.setUsername(username);
		employee.setPassword(password);

		employeeService.saveEmployee(employee);

		model.addAttribute("success", "Employee " + employee.getName() + " added successfully");
		model.addAttribute("employee", true);
		return "success";
	}

	@RequestMapping(value = { "/edit-{id}" }, method = RequestMethod.GET)
	public String editEmployee(@PathVariable Integer id, ModelMap model) {
		Employee employee = employeeService.findEmployeeById(id);
		model.addAttribute("employee", employee);
		return "editEmployee";
	}

	@Transactional
	@RequestMapping(value = { "/edit-{id}" }, method = RequestMethod.POST)
	public String updateEmployee(@Valid Employee employee, BindingResult result,
							  @RequestParam("id") int empid,
							  @RequestParam("name") String name,
							  @RequestParam("email") String email,
							  @RequestParam("role.id") int roleId,
							  @RequestParam("username") String username,
							  @RequestParam("password") String password, ModelMap model) {

		if(roleId == 0){
			FieldError roleError =new FieldError("employee","role","*Employee role need to select here.");
			result.addError(roleError);
			return "editEmployee";
		}

		if(!employeeService.isValidEmailAddress(email)){
			FieldError emailError =new FieldError("employee","email","*Not a valid email address.");
			result.addError(emailError);
			return "editEmployee";
		}

		if(!employeeService.isEmployeeUsernameUnique(username, empid)){
			FieldError usernameError =new FieldError("employee","username","*Username already has been taken someone.");
			result.addError(usernameError);
			return "editEmployee";
		}

		employee.setId(empid);
		employee.setName(name);
		employee.setEmail(email);
		employee.setRole(roleService.findRoleById(roleId));
		employee.setUsername(username);
		employee.setPassword(password);

		employeeService.updateEmployee(employee);
		model.addAttribute("employee", true);
		model.addAttribute("success", "Employee " + employee.getName()	+ " updated successfully");
		return "success";
	}

	@RequestMapping(value = { "/delete-{id}" }, method = RequestMethod.GET)
	public String deleteEmployee(@PathVariable int id) {
		employeeService.deleteEmployeeById(id);
		return "redirect:/employee/list";
	}

	@ModelAttribute("roleList")
	public List<Role> listRoles() {
		List<Role> roleList = roleService.findAllRoles();
		return roleList;
	}


}
