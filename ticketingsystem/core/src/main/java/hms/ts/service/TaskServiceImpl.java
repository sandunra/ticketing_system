package hms.ts.service;

import hms.ts.dao.TaskDao;
import hms.ts.model.Employee;
import hms.ts.model.Project;
import hms.ts.model.Task;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service("taskService")
@Transactional
public class TaskServiceImpl implements TaskService {

	@Autowired
	private TaskDao dao;
	
	public Task findById(int id) {
		return dao.findById(id);
	}

	public void saveTask(Task task) {
		dao.saveTask(task);
	}

	/*
	 * Since the method is running with Transaction, No need to call hibernate update explicitly.
	 * Just fetch the entity from db and update it with proper values within transaction.
	 * It will be updated in db once transaction ends. 
	 */
	public void updateTask(Task task) {
		Task entity = dao.findById(task.getId());
		if(entity!=null){
			entity.setComment(task.getComment());
			entity.setDescription(task.getDescription());
			entity.setTitle(task.getTitle());
			entity.setAssignedHours(task.getAssignedHours());
			entity.setSpentHours(task.getSpentHours());
			entity.setEmployee(task.getEmployee());
			entity.setProject(task.getProject());
			entity.setStatus(task.getStatus());

		}
	}

	public void deleteTaskById(int id) {
		dao.deleteTaskById(id);
	}
	
	public List<Task> findAllTaskByProjectId(int projectId) {
		return dao.findAllTaskByProjectId(projectId);
	}

	public List<Task> findAllTaskByEmployeeId(int empId) {
		return dao.findAllTaskByEmployeeId(empId);
	}

	public boolean isTaskIdUnique(Integer id) {
		Task task = findById(id);
		return ( task == null || ((id != null) && (task.getId() == id)));
	}

	/*public List<Employee> listAllEmployees() {
		return dao.listAllEmployees();
	}*/

//	public List<Project> listAllProjects() {
//		return dao.listAllProjects();
//	}
	
}
