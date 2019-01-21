package hms.ts.dao;

import hms.ts.model.Employee;
import hms.ts.model.Role;

import java.util.ArrayList;
import java.util.List;

public interface RoleDao {

	Role findById(int id);

	void saveRole(Role role);
	
	void deleteRoleById(int id);
	
	List<Role> findAllRoles();

	Role findRoleById(int id);

}
