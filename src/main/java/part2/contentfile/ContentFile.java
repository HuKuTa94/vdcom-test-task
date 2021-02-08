package part2.contentfile;

import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.util.List;
import java.util.Objects;

public abstract class ContentFile
{
    protected MultipartFile file;
    protected String extension;
    protected String supportedExtension;

    public ContentFile( MultipartFile file ) {
        this.file = file;
    }

    public String getExtension() {
        if( extension == null ) {
            String name = Objects.requireNonNull( file.getOriginalFilename() );
            int i = name.lastIndexOf('.');
            extension = i > 0 ? name.substring( i + 1 ) : "";
        }
        return extension;
    }

    public boolean validate() {
        if( file.isEmpty() )
            return false;

        return getExtension().equals( supportedExtension );
    }

    public abstract List<String[]> read();
}
