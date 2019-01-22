package hms.ts.service;

import hms.ts.model.Project;
import hms.ts.model.Task;

import java.util.List;

public interface ProjectService {

	Project findById(int id);
	
	void saveProject(Project project);
	
	void updateProject(Project project);
	
	void deleteProjectById(int id);

	List<Project> findAllProjects();

	Project findProjectById(int id);

	boolean isProjectIdUnique(Integer id);

	List<Task> getProjectTasks(int projectId);
	
}
