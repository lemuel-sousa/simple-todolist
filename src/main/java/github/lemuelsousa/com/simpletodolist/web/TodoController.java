package github.lemuelsousa.com.simpletodolist.web;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import github.lemuelsousa.com.simpletodolist.DTO.TodoDTO;
import github.lemuelsousa.com.simpletodolist.entity.Todo;
import github.lemuelsousa.com.simpletodolist.service.TodoService;
import jakarta.validation.Valid;

@RestController
@RequestMapping("/todos")
public class TodoController {

    @Autowired
    private TodoService todoService;


    @PostMapping
    @ResponseStatus( HttpStatus.CREATED)
    public List<TodoDTO> create(@RequestBody @Valid TodoDTO todo){
        return todoService.create(todo);
    }
    
    @GetMapping
    public List<TodoDTO> getAll(){
        return todoService.list();
    }

    @PutMapping("{id}")
    public List<TodoDTO> update(@PathVariable Long id, @RequestBody TodoDTO todo){
        return todoService.update(id, todo);
    }

    @DeleteMapping("{id}")
    public List<TodoDTO> delete(@PathVariable("id") Long id){
        return todoService.delete(id);
    }



}
