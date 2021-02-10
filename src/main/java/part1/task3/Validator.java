package part1.task3;

/** Валидатор строки ввода. Строка должна соответствовать шаблону a V = b W, где:
 * a,b - числа; V,W - величины (могут быть любые).
 * Например: 1 kg = 1000 g.
 *
 * Чтобы задать искомую величину, нужно в правой части равенства вместо числа поставить символ '?'.
 * Например: 1 g = ? kg.
 *  */
public class Validator
{
    private static final int LINE_PARTS_COUNT = 5;

    private static final int VALUE1_NUMBER = 0;
    private static final int VALUE1_NAME = 1;
    private static final int EQUAL_SIGN = 2;
    private static final int VALUE2_NUMBER = 3;
    private static final int VALUE2_NAME = 4;

    private static final String ERROR_MESSAGE = "Ошибка! Некорректный формат вода";

    public static Ratio validate(String line )
    {
        String[] parts = line.split( "\\s" );
        checkLinePartsCount( parts );
        checkEqualSign( parts );
        return getProportionFromParts( parts );
    }

    private static void checkLinePartsCount( String[] parts ) {
        if( parts.length != LINE_PARTS_COUNT ) {
            throw new IllegalArgumentException( ERROR_MESSAGE );
        }
    }

    private static void checkEqualSign( String[] parts ) {
        if( !parts[ EQUAL_SIGN ].equals( "=" )) {
            throw new IllegalArgumentException( ERROR_MESSAGE );
        }
    }

    private static Ratio getProportionFromParts(String[] parts ) {
        Value value1 = new Value(
                Double.parseDouble( parts[ VALUE1_NUMBER ]),
                parts[ VALUE1_NAME ]);

        Value value2;
        if( parts[ VALUE2_NUMBER ].equals( "?" )) {
            value2 = new Value( parts[ VALUE2_NAME ]);
        } else {
            value2 = new Value(
                    Double.parseDouble( parts[ VALUE2_NUMBER ]),
                    parts[ VALUE2_NAME ]);
        }
        return new Ratio( value1, value2 );
    }
}
