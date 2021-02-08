package part2.dao;

import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;
import part2.contentfile.CSVContentFile;
import part2.contentfile.ContentFile;

import javax.transaction.Transactional;
import java.util.ArrayList;
import java.util.List;

@Service
@Transactional
@AllArgsConstructor
public class TodoService
{
    private TodoRepository repository;


    public List<TodoEntity> findAll() {
        return new ArrayList<>( repository.findAll() );
    }

    public TodoEntity save( TodoEntity newTodo ) {
        return repository.save( newTodo );
    }

    public void updateById( long id, TodoEntity newTodo ) {
        TodoEntity oldTodo = findById( id );
        oldTodo.updateFields( newTodo );
    }

    public void deleteById( long id ) {
        repository.deleteById( id );
    }

    public TodoEntity findById( long id ) {
        return repository.findById( id )
                         .orElseThrow( ()->
                                 new IllegalArgumentException( "Invalid todo Id:" + id ));
    }

    public ResponseEntity uploadTodoListFromFile( MultipartFile file ) {
        ContentFile contentFile = new CSVContentFile( file );
        if( !contentFile.validate() )
            return ResponseEntity.status( HttpStatus.UNSUPPORTED_MEDIA_TYPE )
                    .body( "The uploaded file is empty, has an unsupported extension or has a wrong content" );

        List<String[]> content = contentFile.read();
        List<TodoEntity> entities = new ArrayList<>( content.size() );
        for( String[] str : content ) {
            entities.add( new TodoEntity( str[0], Boolean.parseBoolean( str[1] ) ));
        }

        repository.saveAll( entities );
        return ResponseEntity.status( HttpStatus.OK ).body( entities );
    }
}
