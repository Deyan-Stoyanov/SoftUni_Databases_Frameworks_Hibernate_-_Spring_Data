package product.shop.io.reader;

import java.io.IOException;
import java.util.List;

public interface Reader {
    String readAll(String fileName) throws IOException;
}
