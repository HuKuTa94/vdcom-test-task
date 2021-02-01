package part2.dao;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.ArrayList;
import java.util.List;

@Service
@Transactional
@AllArgsConstructor
public class TodoService
{
    private TodoRepository repository;


    public List<TodoEntity> getAll() {
        return new ArrayList<>( repository.findAll() );
    }

    public TodoEntity add( TodoEntity newTodo ) {
        return repository.save( newTodo );
    }

    public void updateById(long id, TodoEntity newTodo ) {
        TodoEntity oldTodo = repository.findById( id ).orElseThrow();
        oldTodo.updateFields( newTodo );
    }

    public void deleteById( long id ) {
        repository.deleteById( id );
    }
}
