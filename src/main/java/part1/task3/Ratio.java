package part1.task3;

import lombok.Getter;

import java.util.Optional;

@Getter
public class Ratio
{
    private Value left;
    private Value right;

    public Ratio( Value left, Value right ) {
        if( left.isUnknown() )
            throw new IllegalArgumentException( "Unknown value not allowed in let position!" );

        if( left.getName().equals( right.getName() ))
            throw new IllegalArgumentException( "Not allowed same value names! Left value '" + left.getName() +
                    "' = right value '" + right.getName() + "'" );

        this.left = left;
        this.right = right;
    }

    public Optional<Value> getUnknownValue() {
        if( left.isUnknown() )
            return Optional.of( left );

        if( right.isUnknown() )
            return  Optional.of( right );

        return Optional.empty();
    }

    public Value getKnownValue() {
        if( !left.isUnknown() )
            return left;

        return right;
    }

    public boolean hasUnknownValue() {
        return getUnknownValue().isPresent();
    }

    @Override
    public String toString() {
        return left.toString() + " = " + right.toString();
    }
}
