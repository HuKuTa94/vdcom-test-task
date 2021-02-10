package part1.task3;

import org.junit.jupiter.api.*;

import java.util.Optional;

class RatioTest
{
    private static Value knownLeft;
    private static Value knownRight;
    private static Value unknownLeft;
    private static Value unknownRight;
    private static Ratio knownRatio;
    private static Ratio unknownRatio;

    @BeforeAll
    static void initValues() {
        knownLeft = new Value( 4.0, "kg" );
        knownRight = new Value( 20.0, "apple" );
        unknownLeft = new Value( "kg" );
        unknownRight = new Value( "apple" );
        knownRatio = new Ratio( knownLeft, knownRight );
        unknownRatio = new Ratio( knownLeft, unknownRight );
    }

    @Test
    @DisplayName( "Constructor - known values" )
    void constructorKnownValuesTest() {
        Assertions.assertDoesNotThrow( () -> new Ratio( knownLeft, knownRight ));
    }

    @Test
    @DisplayName( "Constructor - same value names not allowed" )
    void constructorWithSameValueNamesTest() {
        Assertions.assertThrows( IllegalArgumentException.class, () -> new Ratio( knownLeft, knownLeft ));
    }

    @Test
    @DisplayName( "Constructor - 1 unknown value (must be in right position)" )
    void constructorUnknownValueTest() {
        Assertions.assertDoesNotThrow( () -> new Ratio( knownLeft, unknownRight ));
        Assertions.assertThrows( IllegalArgumentException.class, () -> new Ratio( unknownLeft, knownRight ));
        Assertions.assertThrows( IllegalArgumentException.class, () -> new Ratio( unknownLeft, unknownRight ));
    }

    @Test
    @DisplayName( "hasUnknownValue() - returns true if such value exists in proportion" )
    void hasUnknownValueTest() {
        Assertions.assertFalse( knownRatio.hasUnknownValue() );
        Assertions.assertTrue( unknownRatio.hasUnknownValue() );
    }

    @Test
    @DisplayName( "getUnknownValue() - returns Optional of unknown Value, and returns empty Optional if unknown Value does not exists in this proportion" )
    void getUnknownValueTest() {
        Optional<Value> actualUnknownValue = unknownRatio.getUnknownValue();
        Assertions.assertTrue( actualUnknownValue.isPresent() );
        Assertions.assertTrue( actualUnknownValue.get().isUnknown() );

        Optional<Value> mustBeEmptyResult = knownRatio.getUnknownValue();
        Assertions.assertTrue( mustBeEmptyResult.isEmpty() );
    }

    @Test
    @DisplayName( "getKnownValueTest() - returns known Value of this proportion" )
    void getKnownValueTest() {
        Value valueFromKnownProportion = knownRatio.getKnownValue();
        Value valueFromUnknownProportion = unknownRatio.getKnownValue();

        Assertions.assertFalse( valueFromKnownProportion.isUnknown() );
        Assertions.assertFalse( valueFromUnknownProportion.isUnknown() );
    }
}
