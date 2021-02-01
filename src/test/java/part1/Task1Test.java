package part1;

import org.junit.jupiter.api.*;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;

class Task1Test
{
    private final int FIRST_NUMBER = 1;
    private final int SECOND_NUMBER = 15;
    private final String EXPECTED =  "1\r\n2\r\nFoo\r\n4\r\nBar\r\nFoo\r\n7\r\n8\r\nFoo\r\nBar\r\n11\r\nFoo\r\n13\r\n14\r\nFooBar\r\n";

    private ByteArrayOutputStream output;

    // Init tests
    Task1Test() {
        output = new ByteArrayOutputStream();
        System.setOut( new PrintStream( output ));
    }

    @BeforeEach
    void resetOutput() {
        output.reset();
    }

    @Test
    @DisplayName( "Задание 1. Решение 1 - цикл и условия" )
    void solution1Test() {
        Task1.solution1( FIRST_NUMBER, SECOND_NUMBER );
        Assertions.assertEquals( EXPECTED, output.toString() );
    }

    @Test
    @DisplayName( "Задание 1. Решение 2 - Stream API" )
    void solution2Test() {
        Task1.solution2( FIRST_NUMBER, SECOND_NUMBER );
        Assertions.assertEquals( EXPECTED, output.toString() );
    }
}
