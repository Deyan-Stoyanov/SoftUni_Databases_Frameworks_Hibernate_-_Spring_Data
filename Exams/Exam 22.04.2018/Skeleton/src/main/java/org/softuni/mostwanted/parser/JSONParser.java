package org.softuni.mostwanted.parser;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import org.softuni.mostwanted.parser.interfaces.Parser;
import org.springframework.stereotype.Component;

import javax.xml.bind.JAXBException;
import java.io.IOException;

@Component(value = "JSONParser")
public class JSONParser implements Parser {

    private Gson gson;

    public JSONParser(Gson gson) {
        this.gson = new GsonBuilder()
                        .excludeFieldsWithoutExposeAnnotation()
                        .setPrettyPrinting()
                        .create();
    }

    @Override
    public <T> T read(Class<T> objectClass, String fileContent) throws IOException, JAXBException {
        return this.gson.fromJson(fileContent, objectClass);
    }

    @Override
    public <T> String write(T object) throws IOException, JAXBException {
        return this.gson.toJson(object);
    }
}
