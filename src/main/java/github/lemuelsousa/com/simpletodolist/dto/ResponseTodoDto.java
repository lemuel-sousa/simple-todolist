package github.lemuelsousa.com.simpletodolist.dto;

public record ResponseTodoDto( 
    Long id,
    String name,
    String description,
    boolean finished,
    int priority
) {}