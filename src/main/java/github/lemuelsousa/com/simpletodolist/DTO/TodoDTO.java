package github.lemuelsousa.com.simpletodolist.DTO;

import github.lemuelsousa.com.simpletodolist.entity.Todo;
import jakarta.validation.constraints.NotBlank;

public class TodoDTO {
 
    private Long id;
    @NotBlank(message = "the name field is required.")
    private String name;
    @NotBlank(message = "the description field is required.")
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

    

    public TodoDTO(Long id, @NotBlank String name, @NotBlank String description, boolean finished, int priority) {
        this.id = id;
        this.name = name;
        this.description = description;
        this.finished = finished;
        this.priority = priority;
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
