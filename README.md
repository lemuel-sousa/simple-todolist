<h1 align="center">
  TODO List
</h1>




## Tecnologias
 
- [Spring Boot](https://spring.io/projects/spring-boot)
- [Spring MVC](https://docs.spring.io/spring-framework/reference/web/webmvc.html)
- [Spring Data JPA](https://spring.io/projects/spring-data-jpa)
- [SpringDoc OpenAPI 3](https://springdoc.org/v2/#spring-webflux-support)
- [Mysql](https://dev.mysql.com/downloads/)
- [Docker](https://docs.docker.com/get-started/)

## Práticas adotadas

- API REST
- Consultas com Spring Data JPA
- Injeção de Dependências
- Tratamento de respostas de erro
- Geração automática do Swagger com a OpenAPI 3

## Como Executar

A aplicação pode ser executada em um [container Docker](https://docs.docker.com/get-started/) ou construída pelo [Gradle](https://docs.gradle.org/current/userguide/userguide.html) e executada tradicionalmente.

- Clonar repositório git


<strong>Método 1: subir o container</strong>

```
$ docker-compose up
```
- Para derrubar o container
```
$ docker-compose down
```
<strong>Método 2: Construir o projeto</strong>
```
$ ./gradlew bootjar
```
- Executar a aplicação:
```
$ java -jar build/libs/todolist-0.0.1-SNAPSHOT.jar
```

A API poderá ser acessada em [localhost:8080](http://localhost:8080).
O Swagger poderá ser visualizado em [localhost:8080/swagger-ui.html](http://localhost:8080/swagger-ui.html)

## API Endpoints

Para fazer as requisições HTTP abaixo, foi utilizada a ferramenta [httpie](https://httpie.io):

- Criar Tarefa 
```
$ http POST :8080/todos name="Todo 1" description="Desc Todo 1" priority=1

[
  {
    "description": "Desc Todo 1",
    "id": 1,
    "name": "Todo 1",
    "priority": 1,
    "finished": false
  }
]
```

- Listar Tarefas
```
$ http GET :8080/todos

[
  {
    "description": "Desc Todo 1",
    "id": 1,
    "name": "Todo 1",
    "priority": 1,
    "finished": false
  }
]
```

- Atualizar Tarefa
```
$ http PUT :8080/todos/1 name="Todo 1 Up" description="Desc Todo 1 Up" priority=2

[
  {
    "description": "Desc Todo 1 Up",
    "id": 1,
    "name": "Todo 1 Up",
    "priority": 2,
    "finished": false
  }
]
```

- Remover Tarefa
```
http DELETE :8080/todos/1

[ ]
```
