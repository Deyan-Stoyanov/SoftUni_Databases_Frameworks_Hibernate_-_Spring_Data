package org.softuni.mostwanted.io.impl;

import org.softuni.mostwanted.io.interfaces.FileIO;
import org.softuni.mostwanted.terminal.Terminal;
import org.springframework.stereotype.Component;

import java.io.*;

@Component
public class FileIOImpl implements FileIO {
    @Override
    public String read(String file) throws IOException {
        StringBuilder sb = new StringBuilder();
        InputStream stream = Terminal.class.getResourceAsStream(file);
        BufferedReader reader = new BufferedReader(new InputStreamReader(stream));
        String line;
        while ((line = reader.readLine()) != null) {
            sb.append(line);
        }
        return sb.toString();
    }

    @Override
    public void write(String fileContent, String file) throws IOException {
        String path = System.getProperty("user.dir") + "/src/main/resources" + file;
        try (OutputStream outputStream = new FileOutputStream(path);
                BufferedWriter bufferedWriter = new BufferedWriter(new OutputStreamWriter(outputStream))) {
            bufferedWriter.write(fileContent);
        }
    }
}
