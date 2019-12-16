package urlParser;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.net.MalformedURLException;
import java.net.URL;
import java.nio.file.Files;

public class FileDownload {


    public boolean fileDownload(String URL, String userName) {
        try {
            File file = new File("/Users/maxim/Desktop/text"+userName+".csv");
            if (!file.exists()) {
                URL url = new URL(URL);
                InputStream inputStream = url.openStream();
                Files.copy(inputStream, new File("/Users/maxim/" +
                        "Desktop/text"+userName+".csv").toPath());
                inputStream.close();
                return true;
            } else {
                System.out.println("Файл уже существует");
            }
        } catch (MalformedURLException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return false;
    }
}
