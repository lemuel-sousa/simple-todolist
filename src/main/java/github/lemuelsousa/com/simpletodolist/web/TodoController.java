package github.lemuelsousa.com.simpletodolist.web;

import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import github.lemuelsousa.com.simpletodolist.DTO.TodoDTO;
import github.lemuelsousa.com.simpletodolist.service.TodoService;
import jakarta.validation.Valid;

@RestController
@RequestMapping("/todos")
public class TodoController {

    private TodoService todoService;

    public TodoController(TodoService todoService) {
        this.todoService = todoService;
    }

    @PostMapping
    public ResponseEntity<List<TodoDTO>> create(@RequestBody @Valid TodoDTO todo){
        return ResponseEntity.ok().body(todoService.create(todo));
    }
    
    @GetMapping
    public ResponseEntity<List<TodoDTO>> getAll(){
        return ResponseEntity.ok().body(todoService.list());
    }

    @PutMapping("{id}")
    public ResponseEntity<List<TodoDTO>> update(@PathVariable Long id, @RequestBody @Valid TodoDTO todo){
        return ResponseEntity.ok().body(todoService.update(id, todo));
    }

    @DeleteMapping("{id}")
    public ResponseEntity<List<TodoDTO>> delete(@PathVariable("id") Long id){
        return ResponseEntity.ok().body(todoService.delete(id));
    }
}
