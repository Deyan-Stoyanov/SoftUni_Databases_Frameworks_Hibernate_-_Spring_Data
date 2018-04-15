package app.serialize;

import app.domain.io.FileIO;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.reflect.TypeToken;
import org.springframework.stereotype.Component;

import java.io.*;
import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.List;

@Component(value = "JsonSerializer")
public class JsonSerializer implements Serializer {

    private Gson gson;

    private FileIO fileIO;


    public JsonSerializer() {
        gson = new GsonBuilder().excludeFieldsWithoutExposeAnnotation().setPrettyPrinting().create();

        fileIO = new FileIO();
    }

    @Override
    public <T> void serializeList(List<T> t, String fileName) {
        Type type = new TypeToken<List<T>>() {}.getType();

        String json = this.gson.toJson(t, type);

        writeFileJson(json, fileName);
    }


    @Override
    public <T> void serialize(T t, String fileName) {

        String json = gson.toJson(t);
        writeFileJson(json, fileName);
    }



    @Override
    public <T> T deserialize(Class<T> classObj, String fileName) throws Exception {

        try {
            String json = fileIO.read(fileName);
            return gson.fromJson(json, classObj);
        } catch (IOException e) {
            e.printStackTrace();
            throw new Exception("Deserializing not possible");
        }
    }


    public <T> List<T> importJsonList(Class<T> classObj, String fileName) throws IOException {
        Type type = new TypeToken<ArrayList<T>>() {
        }.getType();
        String json = this.fileIO.read(fileName);
        return this.gson.fromJson(json, type);
    }



    public void writeFileJson(String json, String fileName){

        String path = System.getProperty("user.dir") + File.separator + fileName;
        File f = new File(path);
        if (!f.exists()) {
            f.getParentFile().mkdirs();
            try {
                f.createNewFile();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        try (OutputStream os = new FileOutputStream(fileName); BufferedWriter bfw = new BufferedWriter(new OutputStreamWriter(os))) {

            bfw.write(json);
            bfw.flush();

        } catch (IOException e) {
            e.printStackTrace();
        }
    }



    public String readFileJson(String fileName) throws IOException {
        StringBuilder fileContent = new StringBuilder();
        try (
                InputStream is = getClass().getResourceAsStream(fileName);
                BufferedReader bfr = new BufferedReader(new InputStreamReader(is))
        ) {
            String line = null;
            while ((line = bfr.readLine()) != null) {
                fileContent.append(line);
            }
        }

        return fileContent.toString();
    }

    @Override
    public <T> T importFromFile(Class<T> classObj, String fileName){

        try {

            String path = System.getProperty("user.dir") + PERSON_INPUT_DIRECTORY +fileName;

            System.out.println("File path: " + path);
            BufferedReader bufferedReader = new BufferedReader(
                    new FileReader(path));

            return gson.fromJson(bufferedReader, classObj);
        } catch (IOException e) {
            e.printStackTrace();
            throw new SerializeException(fileName
                    + " cannot be read and nothing is deserialized to "
                    + fileName + ".\n"
                    + "Message: " + e.getMessage() + ".\n"
                    + "Cause: " + e.getCause() + ".\n", e);
        }

    }


    @Override
    public <T> void exportToFile(T t, String fileName) {

        String path = System.getProperty("user.dir") + PERSON_OUTPUT_DIRECTORY +fileName;

        System.out.println("File path: " + path);
        try {
            FileWriter writer = new FileWriter(path);

            writer.write(gson.toJson(t));
            writer.close();
        } catch (IOException e) {
            e.printStackTrace();
            throw new SerializeException(t.toString() + " cannot be written " +
                    "and nothing is serialized to " + fileName + ".\n" +
                    "Message: " + e.getMessage() + ".\n" +
                    "Cause: " + e.getCause() + ".\n", e);

        }


    }

    private static final String PERSON_OUTPUT_DIRECTORY = "/src/main/resources/files/output/json/";
    private static final String PERSON_INPUT_DIRECTORY = "/src/main/resources/files/input/json/";
}