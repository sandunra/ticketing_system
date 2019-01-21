package hms.ts.dao;

import hms.ts.model.Employee;
import hms.ts.model.Project;
import hms.ts.model.Task;

import java.util.List;

public interface TaskDao {

	Task findById(int id);

	void saveTask(Task task);
	
	void deleteTaskById(int id);
	
	List<Task> findAllTaskByProjectId(int projectId);

	List<Task> findAllTaskByEmployeeId(int empId);

	List<Employee> listAllEmployees();

	List<Project> listAllProjects();

}
