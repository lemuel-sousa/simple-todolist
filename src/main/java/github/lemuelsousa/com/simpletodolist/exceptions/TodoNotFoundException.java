package github.lemuelsousa.com.simpletodolist.exceptions;

public class TodoNotFoundException extends RuntimeException{
    
    public TodoNotFoundException(Long id){
        super(String.format("Todo with id [%d] not found.", id));
    }
}