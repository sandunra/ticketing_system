package hms.ts.controller;

import hms.ts.model.Employee;
import hms.ts.model.Project;
import hms.ts.model.Task;
import hms.ts.service.EmployeeService;
import hms.ts.service.JavaEmailSender;
import hms.ts.service.ProjectService;
import hms.ts.service.TaskService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.*;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

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
	JavaEmailSender javaEmailSender;
	
	@Autowired
	MessageSource messageSource;

	@PersistenceContext
	private EntityManager entityManager;

	/*
	 * This method will list all existing tasks related with selected project.
	 */
	@RequestMapping(value = { "project-{id}/task-list" }, method = RequestMethod.GET)
	public String listProjectTasks(@PathVariable Integer id, ModelMap model) {

		List<Task> tasks = projectService.getProjectTasks(id);
		if(tasks.size() == 0)
			model.addAttribute("noTasks", true);
		model.addAttribute("tasks", tasks);
		model.addAttribute("project", projectService.findProjectById(id));
		model.addAttribute("admin", true);
		model.addAttribute("error", false);
		model.addAttribute("showpopup", false);
		return "projectTasks";
	}

	/*
	 * This method will list all existing tasks related with selected employee.
	 */
	@RequestMapping(value = { "employee-{id}/task-list" }, method = RequestMethod.POST)
	public String listEmployeeTasks(@PathVariable Integer id, ModelMap model) {

		List<Task> tasks = taskService.findAllTaskByEmployeeId(id);
		model.addAttribute("tasks", tasks);
		model.addAttribute("admin", false);
		return "employeeTasks";
	}

	@RequestMapping(value = { "project-{id}/task/new" }, method = RequestMethod.GET)
	public String newTask(@PathVariable Integer id, ModelMap model) {
		Task task = new Task();
		model.addAttribute("task", task);
		model.addAttribute("project", projectService.findProjectById(id));
		return "addTask";
	}

	@Transactional
	@RequestMapping(value = "project-{id}/task/new", method = RequestMethod.POST)
	public String saveTask(@PathVariable Integer id, ModelMap model, @RequestParam("title") String title,
								   @RequestParam("description") String description,
								   //@RequestParam("project") int projectId,
//								   @RequestParam("employee") int employeeId,
								   @RequestParam("assignedHours") int assignedHours){
//								   @RequestParam("spentHours") int spentHours,
//								   @RequestParam("status") int status,
//								   @RequestParam("comment") String comment) {

		Task task = new Task();
		task.setTitle(title);
		task.setDescription(description);
		task.setProject(projectService.findProjectById(id));
		//task.setEmployee(employeeService.findEmployeeById(employeeId));
		task.setAssignedHours(assignedHours);
//		task.setSpentHours(spentHours);
//		task.setComment(comment);
		task.setStatus(1);

		taskService.saveTask(task);

		model.addAttribute("id", id);
		model.addAttribute("success", "Task added successfully");
		model.addAttribute("task", true);

		return "success";
	}

	@RequestMapping(value = { "project-{projectId}/task-{id}/edit" }, method = RequestMethod.GET)
	public String editTask(@PathVariable Integer projectId, @PathVariable Integer id, ModelMap model) {
		Task task = taskService.findTaskById(id);
		model.addAttribute("task", task);
		model.addAttribute("project", projectService.findProjectById(projectId));
		return "editTask";
	}

	@Transactional
	@RequestMapping(value = { "project/task/edit" }, method = RequestMethod.POST)
	public String updateTask( ModelMap model,
							 @RequestParam("id") int taskId,
							 @RequestParam("project.id") int projectId,
							 @RequestParam("title") String title,
						     @RequestParam("description") String description,
//						     @RequestParam("employee") int employeeId,
						     @RequestParam("assignedHours") int assignedHours,
//						     @RequestParam("spentHours") int spentHours,
//						     @RequestParam("status") int status,
						     @RequestParam("comment") String comment, HttpServletRequest request, HttpServletResponse response) {

		Task task = new Task();
		task.setId(taskId);
		task.setTitle(title);
		task.setDescription(description);
		task.setProject(projectService.findProjectById(projectId));
//		task.setEmployee(employeeService.findEmployeeById(employeeId));
		task.setAssignedHours(assignedHours);
//		task.setSpentHours(spentHours);
		task.setComment(comment + " - (" + request.getSession().getAttribute("username") + ")");
//		task.setStatus(status);

		taskService.updateTask(task);
		model.addAttribute("id", projectId);
		model.addAttribute("task", true);
		model.addAttribute("success", "Task " + task.getTitle()	+ " updated successfully");
		return "success";
	}

	@RequestMapping(value = { "project-{projectId}/task-{taskId}/assign" }, method = RequestMethod.GET)
	public String assignTask(@PathVariable Integer projectId, @PathVariable Integer taskId, ModelMap model) {
		List<Task> tasks = projectService.getProjectTasks(projectId);
		model.addAttribute("tasks", tasks);
		model.addAttribute("project", projectService.findProjectById(projectId));
		Task task = taskService.findTaskById(taskId);
		model.addAttribute("task", task);
		model.addAttribute("error", false);
		model.addAttribute("showpopup", true);
		return "projectTasks";
	}

	@RequestMapping(value = { "project-{projectId}/task-{taskId}/assign" }, method = RequestMethod.POST)
	public String assignTask( @PathVariable Integer projectId, @PathVariable Integer taskId, ModelMap model,
							  @RequestParam("assignee") int employeeId,
							  @RequestParam("assignHours") int assignedHours, HttpServletRequest request, HttpServletResponse response) {

		int myId = (int)request.getSession().getAttribute("id");
		List<Task> tasks = projectService.getProjectTasks(projectId);
		model.addAttribute("tasks", tasks);
		model.addAttribute("project", projectService.findProjectById(projectId));
		Task task = taskService.findTaskById(taskId);
		model.addAttribute("task", task);
		model.addAttribute("showpopup", true);
		if( employeeId == 0){
			model.addAttribute("error", true);
			return "projectTasks";
		}

		task.setId(taskId);
		task.setEmployee(employeeService.findEmployeeById(employeeId));
		task.setAssignedHours(assignedHours);
		task.setStatus(2);

		taskService.assignAndUpdateTask(task);

		String emailAddressTo = employeeService.findEmployeeById(employeeId).getEmail();
		String msgSubject = taskService.findTaskById(taskId).getProject().getTitle() + " Project - task assigning";

		String line1 = "Dear " + employeeService.findEmployeeById(employeeId).getName() + ", You've been assigned below task.";
		String line2 = "<br><b>Project : </b>" +taskService.findTaskById(taskId).getProject().getTitle() + "<br><br>";
		String line3 = "<b>Task : </b>" +taskService.findTaskById(taskId).getTitle() + "<br><br>";
		String line4 = "<b>Description : </b>" +taskService.findTaskById(taskId).getDescription() + "<br><br>";
		String line5 = "<b>Assign Hours :</b>" +taskService.findTaskById(taskId).getAssignedHours() + "<br><br>";

		String msgText = line1 + "\n <br>" + line2 + "\n &#10;" +line3 + System.lineSeparator() + line4 + "\n" + line5 + "\n";

		javaEmailSender.setUsername(employeeService.findEmployeeById(myId).getEmail());
		javaEmailSender.setPassword(employeeService.findEmployeeById(myId).getPassword());
		javaEmailSender.setFromAddress(employeeService.findEmployeeById(myId).getEmail());
		javaEmailSender.createAndSendEmail(emailAddressTo, msgSubject, msgText);

		model.addAttribute("id", task.getProject().getId());
		model.addAttribute("task", true);
		model.addAttribute("success", "Task :" + task.getTitle()	+ " assigned to " +employeeService.findEmployeeById(employeeId).getName() + " successfully. <br> Email has been sent to " +employeeService.findEmployeeById(employeeId).getName() + " including task details.");
		return "success";
	}

	@RequestMapping(value = { "project-{projectId}/task-{taskId}/reverse-assign" }, method = RequestMethod.GET)
	public String reverseAssignTask(@PathVariable Integer projectId, @PathVariable Integer taskId, ModelMap model) {
		List<Task> tasks = projectService.getProjectTasks(projectId);
		model.addAttribute("tasks", tasks);
		model.addAttribute("project", projectService.findProjectById(projectId));
		Task task = taskService.findTaskById(taskId);
		model.addAttribute("task", task);
		model.addAttribute("error", false);
		model.addAttribute("showreversepopup", true);
		return "projectTasks";
	}

	@RequestMapping(value = { "project-{projectId}/task-{taskId}/reverse-assign" }, method = RequestMethod.POST)
	public String reverseAssignTask( @PathVariable Integer projectId, @PathVariable Integer taskId, ModelMap model,
							  @RequestParam("reason") String reason, HttpServletRequest request, HttpServletResponse response) {

		int myId = (int)request.getSession().getAttribute("id");
		List<Task> tasks = projectService.getProjectTasks(projectId);
		model.addAttribute("tasks", tasks);
		model.addAttribute("project", projectService.findProjectById(projectId));
		Task task = taskService.findTaskById(taskId);
		model.addAttribute("task", task);
		model.addAttribute("showreversepopup", true);

		int currentAssigneeId = task.getEmployee().getId();

		task.setId(taskId);
		task.setEmployee(null);
		task.setStatus(1);

		taskService.assignAndUpdateTask(task);

		String emailAddressTo = employeeService.findEmployeeById(currentAssigneeId).getEmail();
		String msgSubject = taskService.findTaskById(taskId).getProject().getTitle() + " Project - task reverse assigning";

		String line1 = "Dear " + employeeService.findEmployeeById(currentAssigneeId).getName() + ", Below mentioned task is reverse assigned from you.";
		String line2 = "<br><b>Project : </b>" +taskService.findTaskById(taskId).getProject().getTitle() + "<br><br>";
		String line3 = "<b>Task : </b>" +taskService.findTaskById(taskId).getTitle() + "<br><br>";
		String line4 = "<b>Reason to Reverse Assign : </b><br>" +reason + "<br><br>";
		String line5 = "Thank you so much for your contribution. Sorry if any inconvenience caused. <br><br>";

		String msgText = line1 + "\n <br>" + line2 + "\n &#10;" +line3 + System.lineSeparator() + line4 + "\n" + line5 + "\n";

		javaEmailSender.setUsername(employeeService.findEmployeeById(myId).getEmail());
		javaEmailSender.setPassword(employeeService.findEmployeeById(myId).getPassword());
		javaEmailSender.setFromAddress(employeeService.findEmployeeById(myId).getEmail());
		javaEmailSender.createAndSendEmail(emailAddressTo, msgSubject, msgText);

		model.addAttribute("id", task.getProject().getId());
		model.addAttribute("task", true);
		model.addAttribute("success", "Task :" + task.getTitle()	+ " reverse assigned from " +employeeService.findEmployeeById(currentAssigneeId).getName() + " successfully. <br> Email has been sent to " +employeeService.findEmployeeById(currentAssigneeId).getName() + " including details.");
		return "success";
	}
	
	/*
	 * This method will delete an employee by it's id value.
	 */
	@RequestMapping(value = { "project-{projectId}/task-{taskId}/delete" }, method = RequestMethod.GET)
	public String deleteTask(@PathVariable int projectId, @PathVariable int taskId) {
		taskService.deleteTaskById(taskId);
		return "redirect:/project-{projectId}/task-list";
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

	@ModelAttribute("taskStatusList")
	public Map<Integer, String> getAppTypeList() {
		Map<Integer, String> taskStatusList = new HashMap<Integer, String>();
		taskStatusList.put(0, "-- Select status --");
		taskStatusList.put(1, "Not assigned yet");
		taskStatusList.put(2, "Assigned");
		taskStatusList.put(3, "Ongoing");
		taskStatusList.put(4, "Terminate");
		taskStatusList.put(5, "Complete");
		return taskStatusList;
	}

}
