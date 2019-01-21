package hms.ts.service;

import hms.ts.dao.RoleDao;
import hms.ts.model.Role;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service("roleService")
@Transactional
public class RoleServiceImpl implements RoleService {

	@Autowired
	private RoleDao dao;
	
	public Role findById(int id) {
		return dao.findById(id);
	}

	public void saveRole(Role role) {
		dao.saveRole(role);
	}

	/*
	 * Since the method is running with Transaction, No need to call hibernate update explicitly.
	 * Just fetch the entity from db and update it with proper values within transaction.
	 * It will be updated in db once transaction ends. 
	 */
	public void updateRole(Role role) {
		Role entity = dao.findById(role.getId());
		if(entity!=null){
			entity.setTitle(role.getTitle());
		}
	}

	public void deleteRoleById(int id) {
		dao.deleteRoleById(id);
	}
	
	public List<Role> findAllRoles() {
		return dao.findAllRoles();
	}

	public Role findRoleById(int id) {
		return dao.findRoleById(id);
	}

	public boolean isRoleIdUnique(Integer id) {
		Role role = findRoleById(id);
		return ( role == null || ((id != null) && (role.getId() == id)));
	}
	
}
