package part1.task3;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;

@Getter
@EqualsAndHashCode
public class Value
{
    @Setter
    private double number;
    private String name;
    private boolean unknown;

    public Value( double number, String name ) {
        this.number = number;
        this.name = name;
        unknown = false;
    }

    public Value( String name ) {
        this.name = name;
        unknown = true;
    }

    @Override
    public String toString() {
        return number + " " + name;
    }
}
