package hms.ts.dao;

import hms.ts.model.Role;
import org.hibernate.Criteria;
import org.hibernate.Query;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.Restrictions;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository("roleDao")
public class RoleDaoImpl extends AbstractDao<Integer, Role> implements RoleDao {

	public Role findById(int id) {
		return getByKey(id);
	}

	public void saveRole(Role role) {
		persist(role);
	}

	public void deleteRoleById(int id) {
		Query query = getSession().createSQLQuery("delete from role where id = :id");
		query.setInteger("id", id);
		query.executeUpdate();
	}

	@SuppressWarnings("unchecked")
	public List<Role> findAllRoles() {
		Criteria criteria = (Criteria) getSession().
				createCriteria(Role.class).
				setResultTransformer(Criteria.DISTINCT_ROOT_ENTITY).addOrder(Order.asc("id"));
		return (List<Role>) criteria.list();
	}

	public Role findRoleById(int id) {
		Criteria criteria = createEntityCriteria();
		criteria.add(Restrictions.eq("id", id));
		return (Role) criteria.uniqueResult();
	}

}
