package hms.ts.service;

import hms.ts.model.Role;

import java.util.List;

public interface RoleService {

	Role findById(int id);
	
	void saveRole(Role role);
	
	void updateRole(Role role);
	
	void deleteRoleById(int id);

	List<Role> findAllRoles();

	Role findRoleById(int id);

	boolean isRoleIdUnique(Integer id);
	
}
