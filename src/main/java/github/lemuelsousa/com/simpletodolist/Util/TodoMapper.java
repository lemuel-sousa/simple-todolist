package github.lemuelsousa.com.simpletodolist.Util;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.data.domain.Sort;
import org.springframework.data.domain.Sort.Direction;
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

    public void create(TodoDTO todoDTO){
        todoRepository.save(new Todo(todoDTO));
    }

    public List<TodoDTO> toListDto(){
        Sort byPriority = Sort.by(Direction.ASC, "priority")
            .and(Sort.by(Direction.ASC, "id"));
        
        List<Todo> todos = todoRepository.findAll(byPriority);
        return todos.stream()
            .map(TodoDTO::new)
            .collect(Collectors.toList());
    }         
    
    public void update(Long id, TodoDTO todoDTO){
        Todo todo = new Todo(todoDTO);
        
        todoRepository
            .findById(id).ifPresentOrElse(existingTodo -> {
            todo.setId(id);
            todoRepository.save(todo);
        }, () -> {
            throw new BadRequestException("Task {%d} does not exist!".formatted(id));
        });
    }

    public void delete(Long id){
        todoRepository.findById(id).ifPresentOrElse(
            existingTodo -> todoRepository.delete(existingTodo),
            () -> {
                 throw new BadRequestException("Task {%d} does not exist!".formatted(id));
            }
        );
    }

}
