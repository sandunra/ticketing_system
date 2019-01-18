package hms.ts.controller;

import hms.ts.model.Project;
import hms.ts.service.ProjectService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import javax.validation.Valid;
import java.util.List;
import java.util.Locale;

@Controller
@RequestMapping("/")
public class ProjectController {

	@Autowired
	ProjectService projectService;
	
	@Autowired
	MessageSource messageSource;

	/*
	 * This method will list all existing projects.
	 */
	@RequestMapping(value = { "/", "/projectlist" }, method = RequestMethod.GET)
	public String listProjects(ModelMap model) {

		List<Project> projects = projectService.findAllProjects();
		model.addAttribute("projects", projects);
		return "allprojects";
	}

	/*
	 * This method will provide the medium to add a new project.
	 */
	@RequestMapping(value = { "/newProject" }, method = RequestMethod.GET)
	public String newProject(ModelMap model) {
		Project project = new Project();
		model.addAttribute("project", project);
		model.addAttribute("edit", false);
		return "registration";
	}

	/*
	 * This method will be called on form submission, handling POST request for
	 * saving employee in database. It also validates the user input
	 */
	@RequestMapping(value = { "/newProject" }, method = RequestMethod.POST)
	public String saveProject(@Valid Project project, BindingResult result,
			ModelMap model) {

		if (result.hasErrors()) {
			return "registration";
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
			return "registration";
		}

		projectService.saveProject(project);

		model.addAttribute("success", "Project " + project.getTitle() + " added successfully");
		return "success";
	}


	/*
	 * This method will provide the medium to update an existing employee.
	 */
	@RequestMapping(value = { "/edit-{id}-project" }, method = RequestMethod.GET)
	public String editEmployee(@PathVariable Integer id, ModelMap model) {
		Project project = projectService.findProjectById(id);
		model.addAttribute("project", project);
		model.addAttribute("edit", true);
		return "registration";
	}
	
	/*
	 * This method will be called on form submission, handling POST request for
	 * updating employee in database. It also validates the user input
	 */
	@RequestMapping(value = { "/edit-{id}-project" }, method = RequestMethod.POST)
	public String updateProject(@Valid Project project, BindingResult result,
			ModelMap model, @PathVariable String id) {

		if (result.hasErrors()) {
			return "registration";
		}

		if(!projectService.isProjectIdUnique(project.getId())){
			FieldError idError =new FieldError("employee","id",messageSource.getMessage("non.unique.id", new Integer[]{project.getId()}, Locale.getDefault()));
		    result.addError(idError);
			return "registration";
		}

		projectService.updateProject(project);

		model.addAttribute("success", "Project " + project.getTitle()	+ " updated successfully");
		return "success";
	}
	
	/*
	 * This method will delete an employee by it's SSN value.
	 */
	@RequestMapping(value = { "/delete-{id}-project" }, method = RequestMethod.GET)
	public String deleteProject(@PathVariable int id) {
		projectService.deleteProjectById(id);
		return "redirect:/list";
	}

}
