package app.serialize;

import app.domain.dto.json.PersonDto;
import org.springframework.stereotype.Component;

import javax.xml.bind.JAXBException;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.List;

@Component
public interface Serializer {
    <T> void serialize(T t, String path) throws IOException;
    <T> T deserialize(Class<T> t, String path) throws Exception;
    <T> void serializeList(List<T> t, String path) throws IOException;
    <T> List<T> importJsonList(Class<T> classObj, String path) throws IOException;
    <T> T importFromFile(Class<T> classObj, String fileName) throws JAXBException;
    <T> void exportToFile(T t, String fileName) throws JAXBException, FileNotFoundException;
}
