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
import java.util.Locale;

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
	public String saveEmployee(@Valid Employee employee, BindingResult result, @RequestParam("name") String name,
						   @RequestParam("email") String email,
						   @RequestParam("role") int roleId,
						   @RequestParam("username") String username,
						   @RequestParam("password") String password, ModelMap model) {

		if(roleId == 0){
			FieldError roleError =new FieldError("employee","role",messageSource.getMessage("non.unique.id", null, Locale.getDefault()));
			result.addError(roleError);
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


	/*
	 * This method will be called on form submission, handling POST request for
	 * saving employee in database. It also validates the user input
	 */
	/*@RequestMapping(value = { "/new" }, method = RequestMethod.POST)
	public String saveEmployee(@Valid Employee employee, BindingResult result,
			ModelMap model) {

		if (result.hasErrors()) {
			return "addEmployee";
		}

		*//*
		 * Preferred way to achieve uniqueness of field [ssn] should be implementing custom @Unique annotation 
		 * and applying it on field [ssn] of Model class [Employee].
		 * 
		 * Below mentioned peace of code [if block] is to demonstrate that you can fill custom errors outside the validation
		 * framework as well while still using internationalized messages.
		 * 
		 *//*
		if(!employeeService.isEmployeeIdUnique(employee.getId())){
			FieldError ssnError =new FieldError("employee","id",messageSource.getMessage("non.unique.id", new Integer[]{employee.getId()}, Locale.getDefault()));
		    result.addError(ssnError);
			return "addEmployee";
		}
		
		employeeService.saveEmployee(employee);

		model.addAttribute("success", "Employee " + employee.getName() + " registered successfully");
		model.addAttribute("from", true);
		return "success";
	}*/

	@RequestMapping(value = { "/edit-{id}" }, method = RequestMethod.GET)
	public String editEmployee(@PathVariable Integer id, ModelMap model) {
		Employee employee = employeeService.findEmployeeById(id);
		model.addAttribute("employee", employee);
		model.addAttribute("edit", true);
		return "addEmployee";
	}

	@Transactional
	@RequestMapping(value = { "/edit-{id}" }, method = RequestMethod.POST)
	public String updateEmployee( ModelMap model,
							  @RequestParam("id") int empid,
							  @RequestParam("name") String name,
							  @RequestParam("email") String email,
							  @RequestParam("role") int role,
							  @RequestParam("username") String username,
							  @RequestParam("password") String password) {

		Employee employee = new Employee();
		employee.setId(empid);
		employee.setName(name);
		employee.setEmail(email);
		employee.setRole(roleService.findRoleById(role));
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

	/*@ModelAttribute("roleList")
	public Map<String, String> getCountryList() {
		Map<String, String> roleList = new HashMap<String, String>();

		//List<Job> jobList = jobService.listjobsByPage(page);

		roleList.put("Manager", "Manager");
		roleList.put("Tech Lead", "Tech Lead");
		roleList.put("Senior Software Engineer", "Senior Software Engineer");
		roleList.put("Software Engineer", "Software Engineer");
		roleList.put("Associate Software Engineer", "Associate Software Engineer");
		roleList.put("Trainee Software Engineer", "Trainee Software Engineer");
		roleList.put("Marketing Officer", "Marketing Officer");
		return roleList;
	}*/

	@ModelAttribute("roleList")
	public List<Role> listRoles() {
		List<Role> roleList = roleService.findAllRoles();
		return roleList;
	}


}
