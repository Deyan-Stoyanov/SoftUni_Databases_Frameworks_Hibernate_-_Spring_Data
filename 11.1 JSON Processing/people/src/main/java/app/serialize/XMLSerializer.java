package app.serialize;

import org.springframework.stereotype.Component;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Marshaller;
import javax.xml.bind.Unmarshaller;
import java.io.*;
import java.util.List;

@Component(value = "XMLSerializer")
public class XMLSerializer implements Serializer {

    private static final String PERSON_OUTPUT_DIRECTORY = "/src/main/resources/files/output/xml/";
    private static final String PERSON_INPUT_DIRECTORY = "/src/main/resources/files/input/xml/";



    @Override
    public <T> T importFromFile(Class<T> classObj, String fileName) throws JAXBException {
        String path = System.getProperty("user.dir") + PERSON_INPUT_DIRECTORY + fileName;
        File file = new File(path);
        JAXBContext jaxbContext = JAXBContext.newInstance(classObj);
        Unmarshaller unmarshaller = jaxbContext.createUnmarshaller();
        T importFromFileDTO = (T) unmarshaller.unmarshal(file);
        return null;
    }

    @Override
    public <T> void exportToFile(T t, String fileName) throws JAXBException, FileNotFoundException {
        String path = System.getProperty("user.dir") + PERSON_OUTPUT_DIRECTORY + fileName;
        JAXBContext jaxbContext = JAXBContext.newInstance(t.getClass());
        Marshaller marshaller = jaxbContext.createMarshaller();
        marshaller.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, true);
        marshaller.marshal(t, new FileOutputStream(path));
    }

    @Override
    public <T> void serialize(T t, String path) throws IOException {

    }

    @Override
    public <T> T deserialize(Class<T> t, String path) throws Exception {
        return null;
    }

    @Override
    public <T> void serializeList(List<T> t, String path) throws IOException {

    }

    @Override
    public <T> List<T> importJsonList(Class<T> classObj, String path) throws IOException {
        return null;
    }
}
