package hms.ts.service;

import hms.ts.dao.ProjectDao;
import hms.ts.model.Project;
import hms.ts.model.Task;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service("projectService")
@Transactional
public class ProjectServiceImpl implements ProjectService {

	@Autowired
	private ProjectDao dao;
	
	public Project findById(int id) {
		return dao.findById(id);
	}

	public void saveProject(Project project) {
		dao.saveProject(project);
	}

	/*
	 * Since the method is running with Transaction, No need to call hibernate update explicitly.
	 * Just fetch the entity from db and update it with proper values within transaction.
	 * It will be updated in db once transaction ends. 
	 */
	public void updateProject(Project project) {
		Project entity = dao.findById(project.getId());
		if(entity!=null){
			entity.setClient(project.getClient());
			entity.setDescription(project.getDescription());
			entity.setTitle(project.getTitle());
			entity.setType(project.getType());
		}
	}

	public void deleteProjectById(int id) {
		dao.deleteProjectById(id);
	}
	
	public List<Project> findAllProjects() {
		return dao.findAllProjects();
	}

	public Project findProjectById(int id) {
		return dao.findProjectById(id);
	}

	public boolean isProjectIdUnique(Integer id) {
		Project project = findProjectById(id);
		return ( project == null || ((id != null) && (project.getId() == id)));
	}

	public List<Task> getProjectTasks(int projectId) {
		return dao.getProjectTasks(projectId);
	}
	
}
