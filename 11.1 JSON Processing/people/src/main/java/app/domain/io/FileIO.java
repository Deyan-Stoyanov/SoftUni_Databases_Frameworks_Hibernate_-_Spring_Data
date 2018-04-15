package app.domain.io;

import java.io.*;

public class FileIO {

    public String read(String path) throws IOException {
        StringBuilder jsonContent = new StringBuilder();
        try{
            BufferedReader reader = new BufferedReader(new FileReader(path));
            String currentLine;
            while((currentLine = reader.readLine()) != null){
                jsonContent.append(currentLine);
            }
        } catch (IOException e){
            System.out.println(e.getMessage());
        }
        return jsonContent.toString();
    }


    public void write(String content, String fileName) throws FileNotFoundException {
        OutputStream os = new FileOutputStream(fileName);
        try  {
            BufferedWriter bfw = new BufferedWriter(new OutputStreamWriter(os));
            bfw.write(content);

        } catch (IOException e) {
            e.printStackTrace();
        } finally{
            try {
                os.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }


    public void writeFile(String fileName, String content) throws IOException {
        String path = System.getProperty("user.dir") + File.separator + fileName;
        File f = new File(path);
        if(!f.exists()) {
            f.getParentFile().mkdirs();
            f.createNewFile();
        }
        try (OutputStream os = new FileOutputStream(fileName);
             BufferedWriter bfw = new BufferedWriter(new OutputStreamWriter(os))
        ) {
            bfw.write(String.valueOf(content));
        }
    }
}
