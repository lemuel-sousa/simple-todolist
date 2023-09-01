package github.lemuelsousa.com.simpletodolist.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.stereotype.Service;

import github.lemuelsousa.com.simpletodolist.Util.TodoMapper;
import github.lemuelsousa.com.simpletodolist.dto.RequestTodoDto;
import github.lemuelsousa.com.simpletodolist.dto.ResponseTodoDto;
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

    public List<ResponseTodoDto> create(RequestTodoDto requestTodo){
        todoRepository.save(todoMapper.toEntity(requestTodo));
        return list();
    }
    
    public List<ResponseTodoDto> list(){
        Sort byPriority = Sort.by(Direction.ASC, "priority")
        .and(Sort.by(Direction.ASC, "id"));
        
        var todos = todoRepository.findAll(byPriority);

        return todoMapper.toListOfResponseTodo(todos);
    }

    public List<ResponseTodoDto> update(Long id, RequestTodoDto requestTodo){
        var todo = todoMapper.toEntity(requestTodo);

        todoRepository
            .findById(id).ifPresentOrElse(existingTodo -> {
                todo.setId(id);
                todoRepository.save(todo);
        }, () -> {
            throw new BadRequestException("Task {%d} does not exist!".formatted(id));
        });
        return list();
    }

    public List<ResponseTodoDto> delete(Long id){
        todoRepository.findById(id).ifPresentOrElse(
            existingTodo -> todoRepository.delete(existingTodo),
            () -> {
                 throw new BadRequestException("Task {%d} does not exist!".formatted(id));
            }
        );
        return list();
    }

}