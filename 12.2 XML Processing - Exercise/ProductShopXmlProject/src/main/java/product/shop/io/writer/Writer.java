package product.shop.io.writer;

import java.io.IOException;

public interface Writer {
    void writeFile(String source, String fileName) throws IOException;
}
