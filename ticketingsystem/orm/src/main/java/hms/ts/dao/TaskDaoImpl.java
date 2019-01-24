package hms.ts.dao;

import hms.ts.model.Task;
import org.hibernate.Criteria;
import org.hibernate.Query;
import org.hibernate.criterion.Restrictions;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository("taskDao")
public class TaskDaoImpl extends AbstractDao<Integer, Task> implements TaskDao {

	public Task findById(int id) {
		return getByKey(id);
	}

	public void saveTask(Task task) {
		persist(task);
	}

	public void deleteTaskById(int id) {
		Query query = getSession().createSQLQuery("delete from task where id = :id");
		query.setInteger("id", id);
		query.executeUpdate();
	}

	public Task findTaskById(int id) {
		Criteria criteria = createEntityCriteria();
		criteria.add(Restrictions.eq("id", id));
		return (Task) criteria.uniqueResult();
	}

	@SuppressWarnings("unchecked")
	public List<Task> findAllTaskByProjectId(int projectId) {
		List<Task> ProjectTaskList = null;
		Query query = getSession().createSQLQuery("select * from task where project_id = :projectId");
		query.setInteger("projectId", projectId);
		ProjectTaskList = query.list();
		return ProjectTaskList;
	}

	public List<Task> findAllTaskByEmployeeId(int empId) {
		List<Task> employeeTaskList = null;
		Query query = getSession().createSQLQuery("select * from task where employee_id = :empId");
		query.setInteger("empId", empId);
		employeeTaskList = query.list();
		return employeeTaskList;
	}

}
