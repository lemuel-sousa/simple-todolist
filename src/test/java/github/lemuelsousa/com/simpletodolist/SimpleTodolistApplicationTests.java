package github.lemuelsousa.com.simpletodolist;

import static github.lemuelsousa.com.simpletodolist.TestConstants.TODO;
import static github.lemuelsousa.com.simpletodolist.TestConstants.TODOS;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.context.SpringBootTest.WebEnvironment;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.jdbc.Sql;
import org.springframework.test.web.reactive.server.WebTestClient;

import github.lemuelsousa.com.simpletodolist.dto.RequestTodoDto;
import github.lemuelsousa.com.simpletodolist.entity.Todo;

@SpringBootTest( webEnvironment = WebEnvironment.RANDOM_PORT)
@ActiveProfiles("test")
@Sql("/remove.sql")
class SimpleTodolistApplicationTests {

	@Autowired
	private WebTestClient webTestClient;

	@Test
	void testCreateTodoSuccessfully() {
		var todo = new Todo(1L, "Teste 1", "test description", false, 0);

		webTestClient
				.post()
				.uri("/todos")
				.bodyValue(todo)
				.exchange()
				.expectStatus().isOk()
				.expectBody()
				.jsonPath("$").isArray()
				.jsonPath("$.length()").isEqualTo(1)
				.jsonPath("$[0].id").isEqualTo(todo.getId())
				.jsonPath("$[0].name").isEqualTo(todo.getName())
				.jsonPath("$[0].description").isEqualTo(todo.getDescription())
				.jsonPath("$[0].finished").isEqualTo(todo.isFinished())
				.jsonPath("$[0].priority").isEqualTo(todo.getPriority());
	}

	@Test
	void testCreateTodoFailed() {
		
		var invalidTodo = new RequestTodoDto("", "", false, 1);

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
			var todoUp = new Todo(TODO.getId(), TODO.getName() + " Up", TODO.getDescription() + " Up", !TODO.isFinished(), TODO.getPriority() + 1);
			webTestClient
				.put()
				.uri("/todos/" + TODO.getId())
				.bodyValue(todoUp)
				.exchange()
				.expectStatus().isOk()
				.expectBody()
				.jsonPath("$").isArray()
				.jsonPath("$[0].length()").isEqualTo(5)
				.jsonPath("$[0].id").isEqualTo(todoUp.getId())
				.jsonPath("$[0].name").isEqualTo(todoUp.getName())
				.jsonPath("$[0].description").isEqualTo(todoUp.getDescription())
				.jsonPath("$[0].finished").isEqualTo(todoUp.isFinished())
				.jsonPath("$[0].priority").isEqualTo(todoUp.getPriority());
	}

	@Test
	void testUpdateTodoFailedByNonExistentId(){
		var nonExistentId = 1L;

		var invalidTodo = new Todo("test", "test desc", false, 1);

		webTestClient
			.put()
			.uri("/todos/" + nonExistentId)
			.bodyValue(invalidTodo)
			.exchange()
			.expectStatus().isNotFound();
	}

	@Test
	@Sql("/import.sql")
	void testUpdateTodoFailedByFieldsValidation(){

		var invalidTodo = new Todo(999L, "", "", false, 1);

		webTestClient
			.put()
			.uri("/todos/" + invalidTodo.getId())
			.bodyValue(invalidTodo)
			.exchange()
			.expectStatus().isBadRequest();
	}
	
	@Test
	@Sql("/remove.sql")
	@Sql("/import.sql")
	void testDeleteTodoSuccess(){
			webTestClient
			.delete()
			.uri("/todos/" + 999L)
			.exchange().expectStatus().isOk()
			.expectBody()
			.jsonPath("$").isArray()
			.jsonPath("$.length()").isEqualTo(3);

	}

	@Test
	void testDeleteTodoFailure(){
		webTestClient
			.delete()
			.uri("/todos/" + 1L)
			.exchange().expectStatus().isNotFound();
	}
	
	@Test
	@Sql("/remove.sql")
	@Sql("/import.sql")
	void testGetTodos() throws Exception{
		webTestClient
			.get()
			.uri("/todos")
			.exchange()
			.expectStatus().isOk()
			.expectBody()
			.jsonPath("$").isArray()
			.jsonPath("$.length()").isEqualTo(4)
			.jsonPath("$[0]").isEqualTo(TODOS.get(0))
			.jsonPath("$[1]").isEqualTo(TODOS.get(1))
			.jsonPath("$[2]").isEqualTo(TODOS.get(2))
			.jsonPath("$[3]").isEqualTo(TODOS.get(3));
	}
}
