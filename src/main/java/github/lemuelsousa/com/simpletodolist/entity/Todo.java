package github.lemuelsousa.com.simpletodolist.entity;

import github.lemuelsousa.com.simpletodolist.DTO.TodoDTO;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table( name = "todos")
public class Todo {
    
    @Id
    @GeneratedValue( strategy = GenerationType.IDENTITY)
    private Long id;
   
    private String name;   
    private String description;
    private boolean finished;
    private int priority;

    public Todo(){}

    public Todo(String name, String description, boolean finished, int priority) {
        this.name = name;
        this.description = description;
        this.finished = finished;
        this.priority = priority;
    }

    public Todo(TodoDTO dto) {
        this.name = dto.getName();
        this.description = dto.getDescription();
        this.finished = dto.isFinished();
        this.priority = dto.getPriority();
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public boolean isFinished() {
        return finished;
    }

    public void setFinished(boolean finished) {
        this.finished = finished;
    }

    public int getPriority() {
        return priority;
    }

    public void setPriority(int priority) {
        this.priority = priority;
    }

}
