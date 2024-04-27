package beans;

import abstracts.AbstractFacade;
import jakarta.ejb.*;
import jakarta.persistence.*;
import entities.Task;
import java.util.List;

/**
 * Session Bean implementation class TodoSessionBean
 */
@Stateless(mappedName = "TodoSessionBean")
@LocalBean
public class TodoSessionBean extends AbstractFacade<Task> implements TodoSessionBeanLocal {

	@PersistenceContext(unitName = "todolist")
	private EntityManager em;
    public TodoSessionBean() {
        // TODO Auto-generated constructor stub
    	super(Task.class);
    }

	@Override
	public List<Task> findAllTask() {
		// TODO Auto-generated method stub
		return super.findAll();
	}

	@Override
	public void create(Task task) {
		// TODO Auto-generated method stub
		super.create(task);
	}

	@Override
	public void update(Task task) {
		// TODO Auto-generated method stub
		super.update(task);
	}

	@Override
	public void delete(Task task) {
		// TODO Auto-generated method stub
		super.remove(task);
	}

	@Override
	public Task findByTask(String task) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	protected EntityManager getEntityManager() {
		// TODO Auto-generated method stub
		return em;
	}

	@Override
	public Task findById(int id) {
		// TODO Auto-generated method stub
		return super.find(id);
	}

}
