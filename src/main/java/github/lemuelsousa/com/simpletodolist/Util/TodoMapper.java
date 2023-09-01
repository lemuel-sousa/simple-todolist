package github.lemuelsousa.com.simpletodolist.Util;

import java.util.List;

import org.springframework.stereotype.Component;

import github.lemuelsousa.com.simpletodolist.dto.RequestTodoDto;
import github.lemuelsousa.com.simpletodolist.dto.ResponseTodoDto;
import github.lemuelsousa.com.simpletodolist.entity.Todo;

@Component
public class TodoMapper {

    public Todo toEntity(RequestTodoDto request){     
            return new Todo(
            request.name(),
            request.description(),
            request.finished(),
            request.priority());
    }
    
    public ResponseTodoDto toResponse(Todo todo){
        return new ResponseTodoDto(
            todo.getId(),
            todo.getName(),
            todo.getDescription(),
            todo.isFinished(),
            todo.getPriority()
        );
    }

    public List<ResponseTodoDto> toListOfResponseTodo(List<Todo> todos){
        return todos.stream().map(this::toResponse).toList();
    }

}
