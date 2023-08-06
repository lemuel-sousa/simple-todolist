package github.lemuelsousa.com.simpletodolist.service;

import java.util.List;

import org.springframework.stereotype.Service;

import github.lemuelsousa.com.simpletodolist.DTO.TodoDTO;
import github.lemuelsousa.com.simpletodolist.Util.TodoMapper;

@Service
public class TodoService {
    
    private TodoMapper todoMapper;

    public TodoService(TodoMapper todoMapper) {
        this.todoMapper = todoMapper;
    }

    public List<TodoDTO> create(TodoDTO todoDTO){
        todoMapper.create(todoDTO);
        return list();
    }
    
    public List<TodoDTO> list(){
        return todoMapper.toListDto();         
    }

    public List<TodoDTO> update(Long id, TodoDTO todoDTO){
        todoMapper.update(id, todoDTO);
        return list();
    }

    public List<TodoDTO> delete(Long id){
        todoMapper.delete(id);
        return list();
    }

}
