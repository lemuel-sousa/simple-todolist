package github.lemuelsousa.com.simpletodolist.service;

import java.util.List;

import org.springframework.stereotype.Service;

import github.lemuelsousa.com.simpletodolist.entity.Todo;
import github.lemuelsousa.com.simpletodolist.exceptions.BadRequestException;
import github.lemuelsousa.com.simpletodolist.repository.TodoRepository;

@Service
public class TodoService {
    
    private TodoRepository todoRepository;

    public TodoService(TodoRepository todoRepository){
        this.todoRepository = todoRepository;
    }


    public List<Todo> create(Todo todo){
        todoRepository.save(todo);        
        return list();
    }
    
    public List<Todo> list(){
        return todoRepository.findAll();
    }

    public List<Todo> update(Long id, Todo todo){
        
        todoRepository.findById(id).ifPresentOrElse(existingTodo -> {
            todo.setId(id);
            todoRepository.save(todo);
        }, () -> {
            throw new BadRequestException("Todo %d não existe!".formatted(id));
        });
        
        todoRepository.save(todo);
        return list();
    }


    public List<Todo> delete(Long id){
        todoRepository.deleteById(id);
        return list();
    }

}
