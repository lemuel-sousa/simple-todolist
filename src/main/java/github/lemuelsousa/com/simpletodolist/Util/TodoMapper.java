package github.lemuelsousa.com.simpletodolist.Util;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.stereotype.Component;

import github.lemuelsousa.com.simpletodolist.DTO.TodoDTO;
import github.lemuelsousa.com.simpletodolist.entity.Todo;
import github.lemuelsousa.com.simpletodolist.exceptions.BadRequestException;
import github.lemuelsousa.com.simpletodolist.repository.TodoRepository;

@Component
public class TodoMapper {
    
    private TodoRepository todoRepository;

    public TodoMapper(TodoRepository todoRepository) {
        this.todoRepository = todoRepository;
    }

    public TodoDTO toDTO(Todo todo){
        return new TodoDTO(todo);
    }
    
    public Todo toEntity(TodoDTO todoDTO){
        return new Todo(todoDTO);
    }

    public void create(TodoDTO todoDTO){
        todoRepository.save(toEntity(todoDTO));
    }

    public List<TodoDTO> toListDto(){
        List<Todo> todos = todoRepository.findAll();
        return todos.stream()
            .map(this::toDTO)
            .collect(Collectors.toList());
    }         
    

    public void update(Long id, TodoDTO todoDTO){
        Todo todo = toEntity(todoDTO);
        todoRepository.findById(id).ifPresentOrElse(existingTodo -> {
            todo.setId(id);
            todoRepository.save(todo);
        }, () -> {
            throw new BadRequestException("Todo %d não existe!".formatted(id));
        });
    }

    public void delete(Long id){

        todoRepository.findById(id).ifPresentOrElse(
            (existingTodo) -> todoRepository.delete(existingTodo),
            () -> {
                 throw new BadRequestException("Todo %d não existe!".formatted(id));
            }
        );
    }

}
