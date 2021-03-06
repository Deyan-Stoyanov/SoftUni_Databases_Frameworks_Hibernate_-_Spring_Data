package com.masdefect.parser;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.masdefect.parser.interfaces.FileParser;
import org.springframework.stereotype.Component;
import java.io.IOException;

@Component(value = "JSONParser")
public class JSONParser implements FileParser {
    private final Gson gson = new GsonBuilder()
                    .excludeFieldsWithoutExposeAnnotation()
                    .setPrettyPrinting()
                    .create();


    @Override
    public <T> T read(Class<T> objectClass, String fileContent) throws IOException {
        return this.gson.fromJson(fileContent, objectClass);
    }

    @Override
    public <T> String write(T object, String fileContent) throws IOException {
        return this.gson.toJson(object);
    }
}
