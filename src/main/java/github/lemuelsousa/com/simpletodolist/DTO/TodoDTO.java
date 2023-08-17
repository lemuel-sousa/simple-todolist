package github.lemuelsousa.com.simpletodolist.DTO;

import github.lemuelsousa.com.simpletodolist.entity.Todo;
import jakarta.validation.constraints.NotBlank;

public class TodoDTO {
 
    private Long id;
    @NotBlank
    private String name;
    @NotBlank
    private String description;
    private boolean finished;
    private int priority;
 
    public TodoDTO(){}

    public TodoDTO(String name, String description, boolean finished, int priority) {
        this.name = name;
        this.description = description;
        this.finished = finished;
        this.priority = priority;
    }

    public TodoDTO(Todo todo) {
        this.id = todo.getId();
        this.name = todo.getName();
        this.description = todo.getDescription();
        this.finished = todo.isFinished();
        this.priority = todo.getPriority();
    }

    public Long getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getDescription() {
        return description;
    }

    public boolean isFinished() {
        return finished;
    }

    public int getPriority() {
        return priority;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public void setFinished(boolean finished) {
        this.finished = finished;
    }

    public void setPriority(int priority) {
        this.priority = priority;
    }
}
