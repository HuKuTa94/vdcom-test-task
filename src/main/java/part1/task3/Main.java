package part1.task3;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Main
{
    private static final String COMMAND_START = "start";
    private static final String MESSAGE_INPUT = "Введите входные данные:";
    private static final String MESSAGE_OUTPUT = "Результат:";
    private static final String MESSAGE_INFO = "Команды: " + COMMAND_START + " - начать вычисления неизвестных величин\n" +
            "Вводите известные величины в формате a V = b W\nВводите неизвестные величины в формате a V = ? W\n" +
            "где a,b - числа; V,W - названия величин; ? - неизвестное число";

    private static Scanner scanner = new Scanner( System.in );
    private static List<GroupCollection> groupCollections = new ArrayList<>();

    public static void main( String[] args )
    {
        System.out.println( MESSAGE_INFO );
        System.out.println( MESSAGE_INPUT );
        while( true )
        {
            String inputLine = scanner.nextLine();

            if( inputLine.equals( COMMAND_START ))
            {
                System.out.println( MESSAGE_OUTPUT );
                for( GroupCollection groupCollection : groupCollections )
                {
                    if( groupCollection.isHasUnknownGroup() )
                        System.out.println( groupCollection.calculate() );
                }
                continue;
            }

            try {
                Ratio ratio = Validator.validate( inputLine );
                tryAddRatioGroupInGroupCollection( new RatioGroup( ratio ));
            } catch ( Exception e ) {
                System.out.println( "Ошибка! Некорректный формат!\n" + MESSAGE_INFO );
            }
        }
    }

    private static void tryAddRatioGroupInGroupCollection( RatioGroup ratioGroup ) {
        if( groupCollections.size() == 0 ) {
            createNewGroupCollectionAndAdd( ratioGroup );
            return;
        }

        for( GroupCollection groupCollection : groupCollections ) {
            if( groupCollection.put( ratioGroup ))
                return;
        }

        createNewGroupCollectionAndAdd( ratioGroup );
    }

    private static void createNewGroupCollectionAndAdd( RatioGroup ratioGroup ) {
        GroupCollection groupCollection = new GroupCollection();
        groupCollection.put( ratioGroup );
        groupCollections.add( groupCollection );
    }
}
