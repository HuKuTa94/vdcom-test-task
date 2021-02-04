package part2.dao;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.lang.NonNull;

import javax.persistence.*;

@Entity
@Table( name = "todo_list" )
@Data
@NoArgsConstructor
@AllArgsConstructor
public class TodoEntity
{
    @Id
    @GeneratedValue( strategy = GenerationType.AUTO )
    @NonNull
    @Column( name = "id" )
    private long id;

    @NonNull
    @Column( name = "text" )
    private String text;

    @NonNull
    @Column( name = "done" )
    private boolean done;

    void updateFields( TodoEntity newTodo ) {
        this.text = newTodo.text;
        this.done = newTodo.done;
    }
}
