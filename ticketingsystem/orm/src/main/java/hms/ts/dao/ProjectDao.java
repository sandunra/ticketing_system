package hms.ts.dao;

import hms.ts.model.Project;
import hms.ts.model.Task;

import java.util.List;

public interface ProjectDao {

	Project findById(int id);

	void saveProject(Project project);
	
	void deleteProjectById(int id);
	
	List<Project> findAllProjects();

	Project findProjectById(int id);

	List<Task> getProjectTasks(Project project);

}
