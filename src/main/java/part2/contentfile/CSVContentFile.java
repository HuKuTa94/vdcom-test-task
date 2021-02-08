package part2.contentfile;

import au.com.bytecode.opencsv.CSVReader;
import org.springframework.web.multipart.MultipartFile;

import java.io.*;
import java.util.ArrayList;
import java.util.List;

public class CSVContentFile extends ContentFile
{
    private CSVReader reader;

    public CSVContentFile( MultipartFile multipartFile ) {
        super( multipartFile );
        supportedExtension = "csv";
        init();
    }

    private void init() {
        File cvsFile = new File( "contentFile.csv" );
        try( OutputStream output = new FileOutputStream( cvsFile ))
        {
            this.file.getInputStream().transferTo( output );
            reader = new CSVReader( new FileReader( cvsFile ), ',', '"', 0 );
        } catch ( Exception e ) {
            e.printStackTrace();
        }
    }

    @Override
    public boolean validate() {
        if( !super.validate() )
            return false;

        try {
            String[] firstLine = reader.readNext();
            return firstLine[ 0 ].equals( "text" ) && firstLine[ 1 ].equals( "done" );
        } catch ( IOException e ) {
            e.printStackTrace();
        }

        return false;
    }

    @Override
    public List<String[]> read()
    {
        List<String[]> result = new ArrayList<>();
        String[] nextLine;
        try {
            while(( nextLine = reader.readNext() ) != null ) {
                result.add( nextLine );
            }
        } catch ( Exception e ) {
            e.printStackTrace();
        } finally {
            try {
                reader.close();
            } catch ( IOException e ) {
                e.printStackTrace();
            }
        }
        return result;
    }
}
