package part1.task3;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

class ValueTest
{
    @Test
    @DisplayName( "Constructor - known and unknown values")
    void constructorTest() {
       Value value1 = new Value( 5.0, "a" );
       Value value2 = new Value( "b" );
       Assertions.assertFalse( value1.isUnknown() );
       Assertions.assertTrue( value2.isUnknown() );
    }
}
