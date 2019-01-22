package hms.ts.service;

import hms.ts.model.Task;

import java.util.List;

public interface TaskService {

	Task findById(int id);
	
	void saveTask(Task task);
	
	void updateTask(Task task);
	
	void deleteTaskById(int id);

	List<Task> findAllTaskByProjectId(int projectId);

	List<Task> findAllTaskByEmployeeId(int empId);

	boolean isTaskIdUnique(Integer id);

	/*List<Employee> listAllEmployees();

	List<Project> listAllProjects();*/
	
}
