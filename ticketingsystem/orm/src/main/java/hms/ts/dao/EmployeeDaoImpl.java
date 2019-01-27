package hms.ts.dao;

import hms.ts.model.Employee;
import hms.ts.model.Task;
import org.hibernate.Criteria;
import org.hibernate.Query;
import org.hibernate.criterion.Restrictions;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository("employeeDao")
public class EmployeeDaoImpl extends AbstractDao<Integer, Employee> implements EmployeeDao {

	public Employee findById(int id) {
		return getByKey(id);
	}

	public void saveEmployee(Employee employee) {
		persist(employee);
	}

	public void deleteEmployeeById(int id) {
		Query query = getSession().createSQLQuery("delete from employee where id = :id");
		query.setInteger("id", id);
		query.executeUpdate();
	}

	@SuppressWarnings("unchecked")
	public List<Employee> findAllEmployees() {
		Criteria criteria = (Criteria) getSession().
				createCriteria(Employee.class).
				setResultTransformer(Criteria.DISTINCT_ROOT_ENTITY);
		return (List<Employee>) criteria.list();
	}

	public Employee findEmployeeById(int id) {
		Criteria criteria = createEntityCriteria();
		criteria.add(Restrictions.eq("id", id));
		return (Employee) criteria.uniqueResult();
	}

	public Employee findEmployeeByUsername(String userName) {
		Criteria criteria = createEntityCriteria();
		criteria.add(Restrictions.eq("username", userName));
		return (Employee) criteria.uniqueResult();
	}

	public List<Employee> findEmployeeByRole(int roleId) {
		Criteria criteria = createEntityCriteria();
		criteria.add(Restrictions.eq("role.id", roleId));
		return (List<Employee>) criteria.list();
	}

	@SuppressWarnings("unchecked")
	public List<Task> getAssignTasksList(int empId) {
		List<Task> assignTasksList = null;
		/*Query query = getSession().createQuery("from Task where project = :project ");
		query.setParameter("project", project);
		projectTaskList = query.list();*/
		Employee employee = findEmployeeById(empId);
		assignTasksList = employee.getTaskList();
		return assignTasksList;
	}

}
