package part1.task3;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

class ValidatorTest
{
    @Test
    @DisplayName( "method: validate( String line ) does not throw exception" )
    void validateTest() {
        String[] correctInput = {
                "2 kg = 2000 g",
                "1000 g = ? kg"
        };
        String[] incorrectInput = {
                "2 kg 2000 g",
                "1 a",
                "",
                "2 = kg 200 g",
                "a b = 1 2",
                "1 2 = a b",
                "kg 2 = 2000 g",
                "? kg = 2000 g",
                "? kg = ? g",
                "2 kg = 2000 kg",
                "2kg = 2000g",
                "2kg=2000g",
                "2 kg = 2000g",
                "2 kg= 2000 g",
                "2 kg =2000 g",
                "2 kg=2000 g"
        };

        for( String line : correctInput )
            Assertions.assertDoesNotThrow( () -> Validator.validate( line ));

        for( String line : incorrectInput )
            Assertions.assertThrows( IllegalArgumentException.class, () -> Validator.validate( line ));
    }
}
