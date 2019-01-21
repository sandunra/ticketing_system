package hms.ts.controller;

import hms.ts.model.Employee;
import hms.ts.model.Project;
import hms.ts.model.Task;
import hms.ts.service.EmployeeService;
import hms.ts.service.ProjectService;
import hms.ts.service.TaskService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;
import java.util.Locale;

@Controller
@RequestMapping("/")
public class TaskController {

	@Autowired
	TaskService taskService;

	@Autowired
	ProjectService projectService;

	@Autowired
	EmployeeService employeeService;
	
	@Autowired
	MessageSource messageSource;

	/*
	 * This method will list all existing tasks related with selected project.
	 */
	@RequestMapping(value = { "project-{id}/task-list" }, method = RequestMethod.GET)
	public String listProjectTasks(@PathVariable Integer id, ModelMap model) {

		List<Task> tasks = taskService.findAllTaskByProjectId(id);
		model.addAttribute("tasks", tasks);
		model.addAttribute("projectId", id);
		model.addAttribute("admin", true);
		return "projectTasks";
	}

	/*
	 * This method will list all existing tasks related with selected employee.
	 */
	@RequestMapping(value = { "employee-{id}/task-list" }, method = RequestMethod.GET)
	public String listEmployeeTasks(@PathVariable Integer id, ModelMap model) {

		List<Task> tasks = taskService.findAllTaskByEmployeeId(id);
		model.addAttribute("tasks", tasks);
		model.addAttribute("admin", false);
		return "employeeTasks";
	}

	/*
	 * This method will provide the medium to add a new project.
	 */
	@RequestMapping(value = { "task/new" }, method = RequestMethod.GET)
	public String newTask(ModelMap model) {
		Task task = new Task();
		model.addAttribute("task", task);
		model.addAttribute("edit", false);
		return "addTask";
	}

	/*
	 * This method will be called on form submission, handling POST request for
	 * saving employee in database. It also validates the user input
	 */

	@RequestMapping(value = "task/new", method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_VALUE)
	@ResponseBody
	public String saveTask(@RequestParam("title") String title,
								   @RequestParam("description") String description,
								   @RequestParam("project") int projectId,
								   @RequestParam("employee") int employeeId,
								   @RequestParam("assignedHours") int assignedHours,
								   @RequestParam("spentHours") int spentHours,
								   @RequestParam("status") int status,
								   @RequestParam("comment") String comment) {

		Task task = new Task();
		task.setTitle(title);
		task.setDescription(description);
		task.setProject(projectService.findProjectById(projectId));
		task.setEmployee(employeeService.findEmployeeById(employeeId));
		task.setTitle(title);
		task.setAssignedHours(assignedHours);
		task.setSpentHours(spentHours);
		task.setComment(comment);
		task.setComment(comment);
		task.setStatus(status);

		taskService.saveTask(task);

		return "success";
	}


	public String saveTask(@Valid Task task, BindingResult result, ModelMap model) {

		if (result.hasErrors()) {
			return "addTask";
		}

		/*
		 * Preferred way to achieve uniqueness of field [ssn] should be implementing custom @Unique annotation 
		 * and applying it on field [ssn] of Model class [Employee].
		 * 
		 * Below mentioned peace of code [if block] is to demonstrate that you can fill custom errors outside the validation
		 * framework as well while still using internationalized messages.
		 * 
		 */
		if(!taskService.isTaskIdUnique(task.getId())){
			FieldError ssnError =new FieldError("task","id",messageSource.getMessage("non.unique.id", new Integer[]{task.getId()}, Locale.getDefault()));
		    result.addError(ssnError);
			return "addTask";
		}

		taskService.saveTask(task);

		model.addAttribute("success", "Task " + task.getTitle() + " added successfully");
		model.addAttribute("from", false);
		return "success";
	}


	/*
	 * This method will provide the medium to update an existing employee.
	 */
	@RequestMapping(value = { "task-{id}/edit" }, method = RequestMethod.GET)
	public String editProject(@PathVariable Integer id, ModelMap model) {
		Task task = taskService.findById(id);
		model.addAttribute("task", task);
		model.addAttribute("edit", true);
		return "addTask";
	}
	
	/*
	 * This method will be called on form submission, handling POST request for
	 * updating employee in database. It also validates the user input
	 */
	@RequestMapping(value = { "task-{id}/edit" }, method = RequestMethod.POST)
	public String updateTask(@Valid Task task, BindingResult result,
			ModelMap model, @PathVariable String id) {

		if (result.hasErrors()) {
			return "addTask";
		}

		if(!taskService.isTaskIdUnique(task.getId())){
			FieldError idError =new FieldError("task","id",messageSource.getMessage("non.unique.id", new Integer[]{task.getId()}, Locale.getDefault()));
		    result.addError(idError);
			return "addTask";
		}

		taskService.updateTask(task);

		model.addAttribute("success", "Task " + task.getTitle()	+ " updated successfully");
		return "success";
	}
	
	/*
	 * This method will delete an employee by it's SSN value.
	 */
	@RequestMapping(value = { "task-{id}/delete" }, method = RequestMethod.GET)
	public String deleteTask(@PathVariable int id) {
		taskService.deleteTaskById(id);
		return "redirect:/project-{id}/task-list";
	}

	@ModelAttribute("employeeList")
	public List<Employee> listEmployees() {
		List<Employee> employeeList = employeeService.findAllEmployees();
		return employeeList;
	}

	@ModelAttribute("projectList")
	public List<Project> listProjects() {
		List<Project> projectList = projectService.findAllProjects();
		return projectList;
	}

}
