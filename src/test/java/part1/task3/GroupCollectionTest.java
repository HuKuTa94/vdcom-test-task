package part1.task3;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;
import java.util.ArrayList;
import java.util.List;

class GroupCollectionTest
{
    private static final String[] INPUT_LINES = {
            "1024 byte = 1 kilobyte",
            "2 bar = 12 ring",
            "16.8 ring = 2 pyramid",
            "4 hare = 1 cat",
            "5 cat = 0.5 giraffe",
            "1 byte = 8 bit",
            "15 ring = 2.5 bar",
            "1 pyramid = ? bar",
            "1 giraffe = ? hare",
            "0.5 byte = ? cat",
            "2 kilobyte = ? bit"
    };

    private static final String EXPECTED = "0.5 byte = ? cat - Преобразование невозможно\r\n" +
            "1.0 pyramid = 1.4000000000000001 bar\r\n" +
            "1.0 giraffe = 40.0 hare\r\n" +
            "2.0 kilobyte = ? bit - Преобразование невозможно\r\n";

    private static List<GroupCollection> groupCollections;
    private ByteArrayOutputStream output;


    GroupCollectionTest() {
        output = new ByteArrayOutputStream();
        System.setOut( new PrintStream( output ));
    }

    @BeforeAll
    static void initTest() {
        groupCollections = new ArrayList<>();
        for( String line : INPUT_LINES ) {
            Ratio ratio = Validator.validate( line );
            RatioGroup ratioGroup = new RatioGroup( ratio );
            tryAddRatioGroupInGroupCollection( ratioGroup );
        }
    }

    private static void tryAddRatioGroupInGroupCollection( RatioGroup ratioGroup ) {
        if( groupCollections.size() == 0 ) {
            createNewGroupCollectionAndAdd( ratioGroup );
            return;
        }

        for( GroupCollection groupCollection : groupCollections) {
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

    @Test
    @DisplayName( "Main logic - input data of test task must be calculated as in example" )
    void mainTest() {
        for( GroupCollection groupCollection : groupCollections ) {
            System.out.println( groupCollection.calculate() );
        }

        Assertions.assertEquals( EXPECTED, output.toString());
    }
}
