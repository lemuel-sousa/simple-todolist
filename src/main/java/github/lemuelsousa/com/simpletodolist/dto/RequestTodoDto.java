package github.lemuelsousa.com.simpletodolist.dto;

import jakarta.validation.constraints.NotBlank;

public record RequestTodoDto(
    @NotBlank(message = "the name field is required.")
    String name,
    @NotBlank(message = "the description field is required.")
    String description,
    boolean finished,
    int priority
){}