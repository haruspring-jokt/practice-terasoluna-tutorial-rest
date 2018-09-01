package todo.api.todo;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import javax.inject.Inject;

import org.dozer.Mapper;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import todo.domain.model.Todo;
import todo.domain.service.todo.TodoService;

@RestController // (1)
@RequestMapping("todos") // (2)
public class TodoRestController {

    @Inject
    TodoService todoService;
    @Inject
    Mapper beanMapper;

    @RequestMapping(method = RequestMethod.GET) // (1)
    @ResponseStatus(HttpStatus.OK) // (2)
    public List<TodoResource> getTodos() {
        Collection<Todo> todos = todoService.findAll();
        List<TodoResource> todoResources = new ArrayList<>();
        for (Todo todo : todos) {
            todoResources.add(beanMapper.map(todo, TodoResource.class)); // (3)
        }
        return todoResources; // (4)
    }
}