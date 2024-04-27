package entities;

import jakarta.persistence.*;

@Entity
@Table(name = "task")
public class Task {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    private String task;
    private String status;
    private String date;

    public Task() {
    }

    public Task(int id, String task, String status, String date) {
        super();
        this.id = id;
        this.task = task;
        this.status = status;
        this.date = date;
    }

    public Task(String task, String status, String date) {
        super();
        this.task = task;
        this.status = status;
        this.date = date;
    }

    //getters and setters
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getTask() {
        return task;
    }

    public void setTask(String task) {
        this.task = task;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }
}
