package hms.ts.controller;

import hms.ts.model.Project;
import hms.ts.model.Task;
import hms.ts.service.EmployeeService;
import hms.ts.service.JavaEmailSender;
import hms.ts.service.ProjectService;
import hms.ts.service.TaskService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;
import java.util.HashMap;
import java.util.List;
import java.util.Locale;
import java.util.Map;

@Controller
public class ProjectController {

	@Autowired
	ProjectService projectService;

	@Autowired
	EmployeeService employeeService;

	@Autowired
	TaskService taskService;

	@Autowired
	JavaEmailSender javaEmailSender;
	
	@Autowired
	MessageSource messageSource;

	/*
	 * This method will list all existing projects.
	 */
	@RequestMapping(value = { "/project", "/project/list" }, method = RequestMethod.GET)
	public String listProjects(ModelMap model) {

		List<Project> projects = projectService.findAllProjects();
		model.addAttribute("projects", projects);
		return "allprojects";
	}

	@RequestMapping(value = {"/empId-{id}/assignTasks" }, method = RequestMethod.GET)
	public String userAssignProjects(@PathVariable int id, ModelMap model) {

		//String userName=principal.getName();
		List<Task> tasks = employeeService.getAssignTasksList(id);
		if(tasks.size() == 0)
			model.addAttribute("noTasks", true);
		model.addAttribute("tasks", tasks);
		model.addAttribute("admin", false);
		model.addAttribute("error", false);
		model.addAttribute("showpopup", false);
		return "assignTasks";
	}

	@RequestMapping(value = {"/emp-{empId}/task-{id}/update" }, method = RequestMethod.GET)
	public String updateStatus(@Valid Task task, @PathVariable int id, @PathVariable int empId, ModelMap model) {

		List<Task> tasks = employeeService.getAssignTasksList(empId);
		if(tasks.size() == 0)
			model.addAttribute("noTasks", true);
		task = taskService.findTaskById(id);
		model.addAttribute("tasks", tasks);
		model.addAttribute("task", task);
		model.addAttribute("admin", false);
		model.addAttribute("error", false);
		model.addAttribute("showpopup", true);
		return "assignTasks";
	}

	@RequestMapping(value = { "/emp-{empId}/task-{id}/update" }, method = RequestMethod.POST)
	public String assignTask( @PathVariable Integer empId, @PathVariable Integer id, ModelMap model,
							  @RequestParam("status") int status,
							  @RequestParam("spentHours") int spentHours,
							  @RequestParam("comment") String comment, HttpServletRequest request, HttpServletResponse response) {

		List<Task> tasks = employeeService.getAssignTasksList(empId);
		if(tasks.size() == 0)
			model.addAttribute("noTasks", true);
		Task task = taskService.findTaskById(id);
		model.addAttribute("tasks", tasks);
		model.addAttribute("task", task);
		model.addAttribute("admin", false);
		model.addAttribute("showpopup", true);
		if( status == 0){
			model.addAttribute("error", true);
			return "assignTasks";
		}

		task.setId(id);
		task.setSpentHours(spentHours);
		task.setStatus(status);
		task.setComment(comment + " - (" + request.getSession().getAttribute("username") + ")");

		taskService.UpdateTaskStatus(task);

		String emailAddressTo = employeeService.findEmployeeByRole(1).get(0).getEmail();
		String msgSubject = taskService.findTaskById(id).getProject().getTitle() + " Project - task status update ";
		String taskStatus;
		if(status == 3)
			taskStatus = "Ongoing";
		else if(status == 3)
		taskStatus = "Terminate";
		else
		taskStatus = "Complete";


		String line1 = "<br><b>Project : </b>" +taskService.findTaskById(id).getProject().getTitle() + "<br><br>";
		String line2 = "<b>Task : </b>" +taskService.findTaskById(id).getTitle() + "<br><br>";
		String line3 = "<b>Status : </b>" +taskStatus + "<br><br>";
		String line4 = "<b>Spent Hours :</b>" +taskService.findTaskById(id).getSpentHours() + "<br><br>";
		String line5 = "<b>Comment :</b>" +taskService.findTaskById(id).getComment() + "<br><br>";

		String msgText = line1 + "\n" + line2 + "\n &#10;" +line3 + System.lineSeparator() + line4 + "\n" +line5 ;

		javaEmailSender.setUsername(employeeService.findEmployeeById(empId).getEmail());
		javaEmailSender.setPassword(employeeService.findEmployeeById(empId).getPassword());
		javaEmailSender.setFromAddress(employeeService.findEmployeeById(empId).getEmail());

		javaEmailSender.createAndSendEmail(emailAddressTo, msgSubject, msgText);

		model.addAttribute("empId", empId);
		model.addAttribute("employeetask", true);
		model.addAttribute("task", false);
		model.addAttribute("success", "Task :" + task.getTitle()	+ " status updated successfully. <br> Email has been sent to admin including details.");
		return "success";
	}


	@RequestMapping(value = { "/project/new" }, method = RequestMethod.GET)
	public String newProject(ModelMap model) {
		Project project = new Project();
		model.addAttribute("project", project);
		model.addAttribute("edit", false);
		return "addProject";
	}

	@RequestMapping(value = { "/project/new" }, method = RequestMethod.POST)
	public String saveProject(@Valid Project project, BindingResult result,
			ModelMap model) {

		if (result.hasErrors()) {
			return "addProject";
		}

		/*
		 * Preferred way to achieve uniqueness of field [ssn] should be implementing custom @Unique annotation 
		 * and applying it on field [ssn] of Model class [Employee].
		 * 
		 * Below mentioned peace of code [if block] is to demonstrate that you can fill custom errors outside the validation
		 * framework as well while still using internationalized messages.
		 * 
		 */
		if(!projectService.isProjectIdUnique(project.getId())){
			FieldError ssnError =new FieldError("project","id",messageSource.getMessage("non.unique.id", new Integer[]{project.getId()}, Locale.getDefault()));
		    result.addError(ssnError);
			return "addProject";
		}

		if(project.getType().equalsIgnoreCase("none")){
			FieldError typeError =new FieldError("project","type","Project type need to be select here.");
			result.addError(typeError);
			return "addProject";
		}

		projectService.saveProject(project);

		model.addAttribute("success", "Project " + project.getTitle() + " added successfully");
		model.addAttribute("project", true);
		return "success";
	}

	@RequestMapping(value = { "/project/edit-{id}" }, method = RequestMethod.GET)
	public String editProject(@PathVariable Integer id, ModelMap model) {
		Project project = projectService.findProjectById(id);
		model.addAttribute("project", project);
		return "editProject";
	}
	
	/*
	 * This method will be called on form submission, handling POST request for
	 * updating project in database. It also validates the user input
	 */
	@RequestMapping(value = { "/project/edit-{id}" }, method = RequestMethod.POST)
	public String updateProject(@Valid Project project, BindingResult result,
			ModelMap model, @PathVariable String id) {

		if (result.hasErrors()) {
			return "editProject";
		}

		if(!projectService.isProjectIdUnique(project.getId())){
			FieldError idError =new FieldError("project","id",messageSource.getMessage("non.unique.id", new Integer[]{project.getId()}, Locale.getDefault()));
		    result.addError(idError);
			return "editProject";
		}

		if(project.getType().equalsIgnoreCase("none")){
			FieldError typeError =new FieldError("project","type","Project type need to be select here.");
			result.addError(typeError);
			return "editProject";
		}

		projectService.updateProject(project);

		model.addAttribute("project", true);
		model.addAttribute("success", "Project " + project.getTitle()	+ " updated successfully");
		return "success";
	}
	
	/*
	 * This method will delete an employee by it's id value.
	 */
	@RequestMapping(value = { "/project/delete-{id}" }, method = RequestMethod.GET)
	public String deleteProject(@PathVariable int id) {
		projectService.deleteProjectById(id);
		return "redirect:/project/list";
	}

	@ModelAttribute("appTypeList")
	public Map<String, String> getAppTypeList() {
		Map<String, String> appTypeList = new HashMap<String, String>();
		appTypeList.put("Android", "Android");
		appTypeList.put("ios", "ios");
		appTypeList.put("Web", "Web");
		appTypeList.put("Other", "Other");
		return appTypeList;
	}

}
