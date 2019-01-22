package hms.ts.dao;

import hms.ts.model.Project;
import hms.ts.model.Task;
import org.hibernate.Criteria;
import org.hibernate.Query;
import org.hibernate.criterion.Restrictions;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository("projectDao")
public class ProjectDaoImpl extends AbstractDao<Integer, Project> implements ProjectDao {

	public Project findById(int id) {
		return getByKey(id);
	}

	public void saveProject(Project project) {
		persist(project);
	}

	public void deleteProjectById(int id) {
		Query query = getSession().createSQLQuery("delete from project where id = :id");
		query.setInteger("id", id);
		query.executeUpdate();
	}

	@SuppressWarnings("unchecked")
	public List<Project> findAllProjects() {
		Criteria criteria = createEntityCriteria();
		return (List<Project>) criteria.list();
	}

	public Project findProjectById(int id) {
		Criteria criteria = createEntityCriteria();
		criteria.add(Restrictions.eq("id", id));
		return (Project) criteria.uniqueResult();
	}

	@SuppressWarnings("unchecked")
	public List<Task> getProjectTasks(Project project) {
		List<Task> projectTaskList = null;
		Query query = getSession().createQuery("from Task where project = :project ");
		query.setParameter("project", project);
		projectTaskList = query.list();
		return projectTaskList;
	}
}
