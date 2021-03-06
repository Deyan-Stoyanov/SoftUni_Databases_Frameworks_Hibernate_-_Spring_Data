package product.shop.io.reader;

import org.springframework.stereotype.Component;
import org.springframework.util.StreamUtils;

import java.io.*;
import java.nio.charset.Charset;
import java.nio.file.Files;
import java.nio.file.Paths;

@Component
public class ReaderImpl implements Reader {
    @Override
    public String readAll(String fileName) throws IOException {
        InputStream inputStream = Reader.class.getResourceAsStream(fileName);
        return StreamUtils.copyToString(inputStream, Charset.defaultCharset());
    }
}
