package github.lemuelsousa.com.simpletodolist.web;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;

import github.lemuelsousa.com.simpletodolist.entity.Todo;
import github.lemuelsousa.com.simpletodolist.service.TodoService;

@RequestMapping("/todos")
public class TodoController {
    
    private TodoService todoService;

    public TodoController(TodoService todoService){
        this.todoService = todoService;
    }

    @GetMapping
    @ResponseStatus(HttpStatus.OK)
    public List<Todo> getAll(){
        return todoService.list();
    }

}
