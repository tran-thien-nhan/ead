package beans;

import jakarta.ejb.*;
import entities.Task;
import java.util.List;

@Local
public interface TodoSessionBeanLocal {
    //find all
	List<Task> findAllTask();
    //create
    void create(Task task);
    //update
    void update(Task task);
    //delete
    void delete(Task task);
    //find by task
    Task findByTask(String task);
    //find by id
    Task findById(int id);
}
