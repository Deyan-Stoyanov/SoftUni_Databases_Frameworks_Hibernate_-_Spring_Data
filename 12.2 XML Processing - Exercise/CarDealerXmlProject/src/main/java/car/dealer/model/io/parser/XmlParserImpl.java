package car.dealer.model.io.parser;

import org.springframework.stereotype.Component;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Marshaller;
import javax.xml.bind.Unmarshaller;
import javax.xml.transform.stream.StreamSource;
import java.io.StringReader;
import java.io.StringWriter;

@Component
public class XmlParserImpl implements XmlParser {
    @Override
    public <T> String serialize(T t) throws JAXBException {
        StringWriter stringWriter = new StringWriter();
        JAXBContext jaxbContext = JAXBContext.newInstance(t.getClass());
        Marshaller marshaller = jaxbContext.createMarshaller();
        marshaller.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, true);
        marshaller.marshal(t, stringWriter);
        return stringWriter.toString();
    }

    @Override
    public <T> T deserialize(String source, Class<T> clas) throws JAXBException {
        StringReader reader = new StringReader(source);
        JAXBContext jaxbContext = JAXBContext.newInstance(clas);
        Unmarshaller unmarshaller = jaxbContext.createUnmarshaller();
        return (T) unmarshaller.unmarshal(reader);
    }
}
