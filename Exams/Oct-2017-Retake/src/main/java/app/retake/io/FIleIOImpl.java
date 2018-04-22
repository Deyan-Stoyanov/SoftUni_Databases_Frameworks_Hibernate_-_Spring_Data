package app.retake.io;

import app.retake.Terminal;
import app.retake.io.api.FileIO;
import org.springframework.stereotype.Component;

import java.io.*;

@Component
public class FIleIOImpl implements FileIO {

    @Override
    public String read(String file) throws IOException {
        StringBuilder sb = new StringBuilder();
        InputStream stream = Terminal.class.getResourceAsStream(file);
        BufferedReader reader = new BufferedReader(new InputStreamReader(stream));
        String line;
        while ((line = reader.readLine()) != null) {
            sb.append(line);
        }
        return sb.toString();
    }

    @Override
    public void write(String fileContent, String file) throws IOException {
        String path = System.getProperty("user.dir") + "/src/main/resources" + file;
        OutputStream stream = new FileOutputStream(path);
        BufferedWriter writer = new BufferedWriter(new OutputStreamWriter(stream));
        writer.write(fileContent);
    }
}
