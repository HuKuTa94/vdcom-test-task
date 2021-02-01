package part1;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

class Task2Test
{
    private static final Path FILE_PATH = Paths.get( "out.txt" );

    @Test
    @DisplayName( "Задание 2. Недопустимое число" )
    void illegalInputTest()
    {
        Assertions.assertTrue( () -> Task2.validateInput( 2 ));
        Assertions.assertTrue( () -> Task2.validateInput( 4 ));
        Assertions.assertTrue( () -> Task2.validateInput( 20 ));

        Assertions.assertFalse( () -> Task2.validateInput( -2 ));
        Assertions.assertFalse( () -> Task2.validateInput( 0 ));
        Assertions.assertFalse( () -> Task2.validateInput( 1 ));
        Assertions.assertFalse( () -> Task2.validateInput( 3 ));
    }

    @Test
    @DisplayName( "Задание 2. Файл out.txt с исходным значением - 0")
    void initTextFileTest() {
        try {
            // Удалить файл, если существует
            if( Files.exists( FILE_PATH )) {
                Files.delete( FILE_PATH );
            }

            Task2.initTextFile();

            Assertions.assertTrue( Files.exists( FILE_PATH ));
            Assertions.assertEquals("0", Files.readString( FILE_PATH ));
        } catch ( IOException e ) {
            e.printStackTrace();
        }
    }

    @Test
    @DisplayName( "Задание 2. Потоки. Итоговое число в out.txt" )
    void threadsTest() {
        try {
            Task2.initTextFile();
            Task2.runThreads( 4 );
            Thread.sleep( 2000 );
            Assertions.assertEquals("4", Files.readString( FILE_PATH ));
        } catch ( Exception e ) {
            e.printStackTrace();
        }
    }
}
