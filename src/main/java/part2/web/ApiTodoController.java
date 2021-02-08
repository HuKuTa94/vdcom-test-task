package part2.web;

import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
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

    @PostMapping( value = "/file", consumes = { "multipart/form-data" })
    public ResponseEntity uploadTodoListFromFile( @RequestParam( "file" ) MultipartFile multipartFile ) {
        return service.uploadTodoListFromFile( multipartFile );
    }
}
