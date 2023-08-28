package github.lemuelsousa.com.simpletodolist.Util;

import java.util.List;

import org.springframework.stereotype.Component;

import github.lemuelsousa.com.simpletodolist.DTO.TodoDTO;
import github.lemuelsousa.com.simpletodolist.entity.Todo;

@Component
public class TodoMapper {

    public Todo todoEntityToDTO(TodoDTO todoDTO){
        return new Todo(todoDTO);
    }

    public List<TodoDTO> todoListEntityToDTO(List<Todo> todos){
        return todos.stream()
            .map(TodoDTO::new).toList();
    }   
}
