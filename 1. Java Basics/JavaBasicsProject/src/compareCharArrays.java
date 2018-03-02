import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class compareCharArrays {
    public static void main(String[] args) throws IOException {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        String[] firstArray = reader.readLine().split(" ");
        String[] secondArray = reader.readLine().split(" ");
        boolean first = false;
        if(firstArray.length==secondArray.length){
            for (int i = 0; i < firstArray.length; i++) {
                if(firstArray[i].charAt(0) > secondArray[i].charAt(0)){
                    first = false;
                    break;
                } else if(firstArray[i].charAt(0) < secondArray[i].charAt(0)){
                    first = true;
                    break;
                }
            }
        }  else {
            if(firstArray.length < secondArray.length){
                first = true;
            }
        }
        if(first){
            for (String s:
                firstArray) {
                System.out.print(s);
            }
            System.out.println();
            for (String s:
                    secondArray) {
                System.out.print(s);
            }
        } else {
            for (String s:
                    secondArray) {
                System.out.print(s);
            }
            System.out.println();
            for (String s:
                    firstArray) {
                System.out.print(s);
            }
        }
    }
}
