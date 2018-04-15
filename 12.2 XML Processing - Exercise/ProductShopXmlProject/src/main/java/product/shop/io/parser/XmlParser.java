package product.shop.io.parser;

import javax.xml.bind.JAXBException;

public interface XmlParser {
     <T> String serialize(T t) throws JAXBException;
     <T> T deserialize(String source, Class<T> clas) throws JAXBException;
}
