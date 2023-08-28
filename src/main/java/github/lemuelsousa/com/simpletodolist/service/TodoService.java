package github.lemuelsousa.com.simpletodolist.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.stereotype.Service;

import github.lemuelsousa.com.simpletodolist.DTO.TodoDTO;
import github.lemuelsousa.com.simpletodolist.Util.TodoMapper;
import github.lemuelsousa.com.simpletodolist.entity.Todo;
import github.lemuelsousa.com.simpletodolist.exceptions.BadRequestException;
import github.lemuelsousa.com.simpletodolist.repository.TodoRepository;

@Service
public class TodoService {
    
    @Autowired
    private TodoMapper todoMapper;

    private TodoRepository todoRepository;
    public TodoService(TodoRepository todoRepository) {
        this.todoRepository = todoRepository;
    }

    public List<TodoDTO> create(TodoDTO todoDTO){
        todoRepository.save(todoMapper.todoEntityToDTO(todoDTO));
        return list();
    }
    
    public List<TodoDTO> list(){
        Sort byPriority = Sort.by(Direction.ASC, "priority")
            .and(Sort.by(Direction.ASC, "id"));
        
        List<Todo> todos = todoRepository.findAll(byPriority);
        
        return todoMapper.todoListEntityToDTO(todos);
    }

    public List<TodoDTO> update(Long id, TodoDTO todoDTO){
        Todo todo = new Todo(todoDTO);
        
        todoRepository
            .findById(id).ifPresentOrElse(existingTodo -> {
                todo.setId(id);
                todoRepository.save(todo);
        }, () -> {
            throw new BadRequestException("Task {%d} does not exist!".formatted(id));
        });
    
        return list();
    }

    public List<TodoDTO> delete(Long id){
        todoRepository.findById(id).ifPresentOrElse(
            existingTodo -> todoRepository.delete(existingTodo),
            () -> {
                 throw new BadRequestException("Task {%d} does not exist!".formatted(id));
            }
        );

        return list();
    }

}