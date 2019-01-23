package hms.ts.dao;

import hms.ts.model.Employee;
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
		Criteria criteria = createEntityCriteria();
		return (List<Employee>) criteria.list();
	}

	public Employee findEmployeeById(int id) {
		Criteria criteria = createEntityCriteria();
		criteria.add(Restrictions.eq("id", id));
		return (Employee) criteria.uniqueResult();
	}

	public Employee findEmployeeByUsername(String userName) {
		String sql = "Select from " + Employee.class.getName() + " e where e.username = :username ";

		Query query = getSession().createQuery(sql);
		query.setParameter("username", userName);
		return (Employee) query.uniqueResult();
	}

}
