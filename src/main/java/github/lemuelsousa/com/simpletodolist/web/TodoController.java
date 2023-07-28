package github.lemuelsousa.com.simpletodolist.web;

import java.util.List;

import github.lemuelsousa.com.simpletodolist.entity.Todo;
import github.lemuelsousa.com.simpletodolist.service.TodoService;

public class TodoController {
    
    private TodoService todoService;

    public TodoController(TodoService todoService){
        this.todoService = todoService;
    }

    public List<Todo> getAll(){
        return todoService.list();
    }

}
