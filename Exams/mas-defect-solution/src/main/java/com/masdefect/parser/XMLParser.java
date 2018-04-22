package com.masdefect.parser;

import com.masdefect.parser.interfaces.FileParser;
import org.springframework.stereotype.Component;

import javax.xml.bind.*;
import java.io.*;

@Component(value = "XMLParser")
public class XMLParser implements FileParser {

    @Override
    public <T> T read(Class<T> objectClass, String fileContent) throws IOException, JAXBException {
        JAXBContext jaxbContext = JAXBContext.newInstance(objectClass);
        Unmarshaller unmarshaller = jaxbContext.createUnmarshaller();
        return (T) unmarshaller.unmarshal(new StringReader(fileContent));
    }

    @Override
    public <T> String write(T object, String fileContent) throws IOException, JAXBException {
        StringWriter stringWriter = new StringWriter();
        JAXBContext jaxbContext = JAXBContext.newInstance(object.getClass());
        Marshaller marshaller = jaxbContext.createMarshaller();
        marshaller.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, true);
        marshaller.marshal(object, stringWriter);
        return stringWriter.toString();
    }
}
