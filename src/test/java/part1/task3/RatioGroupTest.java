package part1.task3;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

public class RatioGroupTest
{
    private static final double LEFT_NUMBER = 4.0;
    private static final double RIGHT_NUMBER = 20.0;
    private static final double SPECIFIC_NUMBER_BY_RIGHT_UNIT_VALUE = 0.2;
    private static final double SPECIFIC_NUMBER_BY_LEFT_UNIT_VALUE = 5.0;

    @Test
    @DisplayName( "normalize() - normalizes values of the proportion in separate variables" )
    void normalizeTest() {
        Ratio ratio = new Ratio(
                new Value( LEFT_NUMBER, "kg" ),
                new Value( RIGHT_NUMBER, "apple" )
        );
        RatioGroup ratioGroup = new RatioGroup(ratio);

        Assertions.assertEquals( SPECIFIC_NUMBER_BY_LEFT_UNIT_VALUE, ratioGroup.getUnitByLeftValue().getRight().getNumber(), 0.00000001 );
        Assertions.assertEquals( SPECIFIC_NUMBER_BY_RIGHT_UNIT_VALUE, ratioGroup.getUnitByRightValue().getLeft().getNumber(), 0.00000001 );
    }
}
