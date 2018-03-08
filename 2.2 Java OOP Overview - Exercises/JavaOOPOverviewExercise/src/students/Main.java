package students;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        while(true){
            String name = reader.readLine();
            if(name.toUpperCase().equals("END")){
                break;
            }
            Student student = new Student(name);
        }
        System.out.println(Student.count);
    }
}
