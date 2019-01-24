package hms.ts.dao;

import hms.ts.model.Task;

import java.util.List;

public interface TaskDao {

	Task findById(int id);

	void saveTask(Task task);
	
	void deleteTaskById(int id);

	Task findTaskById(int id);

	List<Task> findAllTaskByProjectId(int projectId);

	List<Task> findAllTaskByEmployeeId(int empId);

}
