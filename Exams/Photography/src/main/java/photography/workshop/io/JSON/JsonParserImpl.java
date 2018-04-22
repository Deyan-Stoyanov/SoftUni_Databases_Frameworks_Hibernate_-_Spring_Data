package photography.workshop.io.JSON;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import org.modelmapper.ModelMapper;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Component;
import org.springframework.util.StreamUtils;
import photography.workshop.Runner;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStream;
import java.nio.charset.Charset;

@Component
public class JsonParserImpl implements JSONParser{

    @Override
    public InputStream loadData(String filePath) {
        return Runner.class.getResourceAsStream(filePath);
    }

    @Override
    public String readAllData(InputStream stream) throws IOException {
        return StreamUtils.copyToString(stream, Charset.defaultCharset());
    }

    @Override
    public void writeFile(String fileName, String source) throws IOException {
        String fullPath = System.getProperty("user.dir") + "/src/main/resources";
        FileWriter writer = new FileWriter(new File(fullPath + fileName));
        writer.write(source);
        writer.flush();
    }
}
