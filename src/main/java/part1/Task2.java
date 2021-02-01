package part1;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Scanner;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.locks.ReadWriteLock;
import java.util.concurrent.locks.ReentrantReadWriteLock;

//TODO 2. Консольное приложение
// На вход передается целое число, больше 0, кратное 2 (n). Создается файл с именем out.txt, в который пишется
// значение 0. Создается два потока, работающих параллельно, каждый поток в цикле, считывает значение из файла out.txt
// увеличивает его на 1, выводит в консоли старое значение, новое значение и идентификатор потока
// (идентификатор может быть произвольным), и записывает обратно в файл. В конечном итоге оба потока должны успешно
// завершить свою работу, и в консоль должно вывестись содержимое файла out.txt (которое должно быть равно заданному на входе n).
public class Task2
{
    private static Scanner scanner = new Scanner( System.in );
    private static final int LOW_NUMBER = 0;
    private static final int MULTIPLICITY = 2;
    private static final int THREADS_COUNT = 2;

    private static final Path FILE_PATH = Paths.get( "out.txt" );

    public static void main( String[] args )
    {
        System.out.println( "Введите целое число больше 0 и кратное 2 (n):" );
        int enteredNumber = getNumberFromInput();
        initTextFile();
        runThreads( enteredNumber );
    }

    private static int getNumberFromInput() {
        int number = scanner.nextInt();
        if( !validateInput( number )) {
            throw new IllegalArgumentException( "Вы ввели недопустимое число! Оно должно быть больше " +
                    LOW_NUMBER + " и кратное числу " + MULTIPLICITY );
        }
        return number;
    }

    static boolean validateInput( int enteredNumber ) {
        return enteredNumber > LOW_NUMBER && enteredNumber % MULTIPLICITY == 0;
    }

    static void initTextFile() {
        try {
            if( Files.notExists( FILE_PATH )) {
                Files.createFile( FILE_PATH );
            }
            Files.write( FILE_PATH, "0".getBytes() );
        } catch ( IOException e ) {
            e.printStackTrace();
        }
    }

    static void runThreads( int enteredNumber ) {
        ExecutorService executor = Executors.newFixedThreadPool( THREADS_COUNT );
        ReadWriteLock lock = new ReentrantReadWriteLock();

        for( int i = 0; i < THREADS_COUNT; i++ ) {
            executor.submit( () -> {
                while( true )
                {
                    try {
                        lock.readLock().lock();
                        int number = Integer.parseInt( Files.readString( FILE_PATH ));
                        if( number >= enteredNumber )
                            break;
                        lock.readLock().unlock();

                        System.out.println( Thread.currentThread().getName() + " - старое значение: " + number + "; новое значение:" + ++number );

                        lock.writeLock().lock();
                        try {
                            if( Integer.parseInt( Files.readString( FILE_PATH )) < enteredNumber )
                                Files.writeString( FILE_PATH, String.valueOf( number ));
                        }
                        finally {
                            lock.writeLock().unlock();
                        }
                    } catch ( Exception e ) {
                        e.printStackTrace();
                    }
                }
                // Вывод содержимого файла в консоль
                try {
                    System.out.println( "Итоговое число в out.txt: " + Files.readString( FILE_PATH ));
                } catch (IOException e) {
                    e.printStackTrace();
                }
            });
        }
        executor.shutdown();
    }
}
