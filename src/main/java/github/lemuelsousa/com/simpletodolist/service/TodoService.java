package github.lemuelsousa.com.simpletodolist.service;

import java.util.List;

import org.springframework.stereotype.Service;

import github.lemuelsousa.com.simpletodolist.entity.Todo;
import github.lemuelsousa.com.simpletodolist.repository.TodoRepository;

@Service
public class TodoService {
    
    private TodoRepository todoRepository;

    public TodoService(TodoRepository todoRepository){
        this.todoRepository = todoRepository;
    }


    public List<Todo> list(){
        return todoRepository.findAll();
    }



}
