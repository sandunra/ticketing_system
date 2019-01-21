package hms.ts.dao;

import hms.ts.model.Employee;
import hms.ts.model.Role;
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
		/*Query query = getSession().createSQLQuery("insert into emplyoee (email, name, password, username, role_id)" + "'emil', 'name', 'password', 'username', 'role_id'");
		query.setInteger("role_id", employee.getRole().getId());
		query.setString("email", employee.getEmail());
		query.setString("name", employee.getName());
		query.setString("password", employee.getPassword());
		query.setString("username", employee.getUsername());
		query.executeUpdate();*/


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

	public List<Role> findAllRoles() {
		List<Role> result = null;
		try{
			Query query = getSession().createQuery("SELECT R.title FROM Role R");
			result = query.list();
			query.executeUpdate();
		} catch(Exception e){
			e.printStackTrace();
		}
		return result;
	}
}
