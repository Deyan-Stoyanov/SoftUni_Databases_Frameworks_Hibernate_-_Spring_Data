package photography.workshop.io.JSON;

import java.io.IOException;
import java.io.InputStream;

public interface JSONParser {
    InputStream loadData(String filePath);
    String readAllData(InputStream stream) throws IOException;
    void writeFile(String fileName, String source) throws IOException;

}
