package github.lemuelsousa.com.simpletodolist.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import github.lemuelsousa.com.simpletodolist.entity.Todo;

public interface TodoRepository extends JpaRepository<Todo, Long>{
    
}
