package part2.web;

import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.*;
import part2.dao.TodoEntity;
import part2.dao.TodoService;

import java.util.List;

@RestController
@AllArgsConstructor
@RequestMapping( "/todo" )
public class ApiTodoController
{
    private TodoService service;

    @GetMapping
    public List<TodoEntity> getTodoList() {
        return service.findAll();
    }

    @PostMapping
    public TodoEntity addNewTodo( @RequestBody TodoEntity newTodo ) {
        return service.save( newTodo );
    }

    @PutMapping( "{id}" )
    public void updateTodoById(
            @PathVariable( "id" ) long id,
            @RequestBody TodoEntity todo )
    {
        service.updateById( id, todo );
    }

    @DeleteMapping( "{id}" )
    public void deleteTodoById( @PathVariable( "id" ) long id ) {
        service.deleteById( id );
    }
}
