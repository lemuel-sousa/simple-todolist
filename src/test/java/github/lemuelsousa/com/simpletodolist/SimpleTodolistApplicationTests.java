package github.lemuelsousa.com.simpletodolist;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.context.SpringBootTest.WebEnvironment;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.jdbc.Sql;
import org.springframework.test.web.reactive.server.WebTestClient;

import github.lemuelsousa.com.simpletodolist.DTO.TodoDTO;
import github.lemuelsousa.com.simpletodolist.repository.TodoRepository;

@SpringBootTest( webEnvironment = WebEnvironment.RANDOM_PORT)
@ActiveProfiles("test")
@Sql("/remove.sql")
class SimpleTodolistApplicationTests {

	@Autowired
	private WebTestClient webTestClient;

	@Autowired
	private TodoRepository todoRepository;	

	@Test
	void testCreateTodoSuccess() {
		var todo = new TodoDTO("Teste 1", "test description", false, 0);

		webTestClient
				.post()
				.uri("/todos")
				.bodyValue(todo)
				.exchange()
				.expectStatus().isCreated()
				.expectBody()
				.jsonPath("$").isArray()
				.jsonPath("$.length()").isEqualTo(1)
				.jsonPath("$[0].name").isEqualTo(todo.getName())
				.jsonPath("$[0].description").isEqualTo(todo.getDescription())
				.jsonPath("$[0].finished").isEqualTo(todo.isFinished())
				.jsonPath("$[0].priority").isEqualTo(todo.getPriority());
	}


	@Test
	void testCreateTodoFailure() {
		
		var invalidTodo = new TodoDTO("", "", false, 1);

		webTestClient
			.post()
			.uri("/todos")
			.bodyValue(invalidTodo)
			.exchange()
			.expectStatus().isBadRequest();
	}

	@Test
	@Sql("/import.sql")
	void testUpdateTodoSuccess(){

		var todo = todoRepository.findAll().get(0);
		
		var todoUp = new TodoDTO(todo.getId(), todo.getName() + " Up", todo.getDescription() + " Up", !todo.isFinished(), todo.getPriority() + 1); 

		webTestClient
			.put()
			.uri("/todos/" + todoUp.getId())
			.bodyValue(todoUp)
			.exchange()
			.expectStatus().isOk()
			.expectBody()
			.jsonPath("$").isArray()
			.jsonPath("$[0].length()").isEqualTo(5)
			.jsonPath("$[0].name").isEqualTo(todoUp.getName())
			.jsonPath("$[0].description").isEqualTo(todoUp.getDescription())
			.jsonPath("$[0].finished").isEqualTo(todoUp.isFinished())
			.jsonPath("$[0].priority").isEqualTo(todoUp.getPriority());
	}

	@Test
	void testUpdateTodoFailure(){
		
		var nonExistentId = 1L;

		var invalidTodo = new TodoDTO(nonExistentId, "", "", false, 1);

		webTestClient
			.put()
			.uri("/todos/" + nonExistentId)
			.bodyValue(invalidTodo)
			.exchange()
			.expectStatus().isBadRequest();
	}
	
	@Test
	@Sql("/import.sql")
	void testDeleteTodoSuccess(){
		
		webTestClient
			.delete()
			.uri("/todos/" + 1)
			.exchange().expectStatus().isOk();
	}

	@Test
	void testDeleteTodoFailure(){
		webTestClient
			.delete()
			.uri("/todos/" + 1)
			.exchange().expectStatus().isBadRequest();
	}
	

}
