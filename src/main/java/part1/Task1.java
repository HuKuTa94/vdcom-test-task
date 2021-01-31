package part1;

import java.util.Scanner;
import java.util.stream.Stream;

//TODO 1. Консольное приложение - FooBar
// На вход приложение получает целое число больше 0 (n),
// далее в консоль выводится следующее, все числа от 1 до n, при этом:
// - Если число кратно 3, выводится Foo;
// - Если число кратно 5, выводится Bar;
// - Если число кратно и 3, и 5, выводится FooBar;
// - Если число не кратно 3 или 5, выводится само число.
// Необходимо предоставить минимум три разных решения, используя разные подходы (минимум ветвлений, без циклов и т.п.).
public class Task1
{
    private static Scanner scanner = new Scanner( System.in );

    public static void main( String[] args )
    {
        int firstNumber;
        int secondNumber;

        while( true )
        {
            try {
                firstNumber = getNumberFromInput( "Введите первое число больше 0: ", 0 );
                secondNumber = getNumberFromInput( "Введите первое число больше " + firstNumber + ": ", firstNumber );

                System.out.println( "\nРешение 1. Используя цикл и ветвления" );
                solution1( firstNumber, secondNumber );

                System.out.println( "\nРешение 2. Используя функциональные интерфейсы и Stream API." );
                solution2( firstNumber, secondNumber );

            } catch ( Exception e ) {
                System.out.println( e.getMessage() );
            }
        }
    }

    private static int getNumberFromInput( String message, int lowBoundary ) {
        System.out.println( message );
        int number = scanner.nextInt();
        if( !validateInput( lowBoundary, number )) {
            throw new IllegalArgumentException( "Вы ввели недопустимое число! Оно должно быть больше числа " + lowBoundary );
        }
        return number;
    }

    private static boolean validateInput( int lowBoundary, int enteredNumber ) {
        return enteredNumber > lowBoundary;
    }

    /** Решение 1. Используя цикл и ветвления*/
    static void solution1(int firstNumber, int secondNumber) {
        for( int i = firstNumber; i <= secondNumber; i++ ) {
            float x3 = i % 3;
            float x5 = i % 5;

            if( x3 == 0 && x5 != 0 ) {
                System.out.println( "Foo" );
                continue;
            }
            else if( x3 != 0 && x5 == 0 ) {
                System.out.println( "Bar" );
                continue;
            }
            else if( x3 == 0 && x5 == 0 ) {
                System.out.println( "FooBar" );
                continue;
            }
            System.out.println( i );
        }
    }

    /** Решение 2. Используя функциональные интерфейсы и Stream API. */
    static void solution2(int firstNumber, int secondNumber) {
        Stream.iterate( firstNumber, n -> n + 1 )
                .limit( secondNumber )
                .forEach( (x) -> {
                    if( x % 3 == 0 && x % 5 != 0 )
                        System.out.println( "Foo" );
                    else if( x % 3 != 0 && x % 5 == 0 )
                        System.out.println( "Bar" );
                    else if( x % 3 == 0 )
                        System.out.println( "FooBar" );
                    else
                        System.out.println( x );
                });
    }
}
