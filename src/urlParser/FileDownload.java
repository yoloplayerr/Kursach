package urlParser;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.net.MalformedURLException;
import java.net.URL;
import java.nio.file.Files;

public class FileDownload {
    public FileDownload(String URL, String userName) {
        fileDowndload(URL, userName);
    }

    private void fileDowndload(String URL, String userName) {
        try {
            File file = new File("/Users/maxim/Desktop/text.csv");
            if (!file.exists()) {
                URL url = new URL(URL);
                InputStream inputStream = url.openStream();
                Files.copy(inputStream, new File("/Users/maxim/" +
                        "Desktop/text.csv").toPath());
            } else {
                System.out.println("Файл уже существует");
            }
            Parser parser = new Parser(userName);
        } catch (MalformedURLException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
