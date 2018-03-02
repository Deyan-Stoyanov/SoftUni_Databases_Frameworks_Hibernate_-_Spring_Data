import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class integerToHexAndBinary {
    public static void main(String[] args) throws IOException {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        int number = Integer.parseInt(reader.readLine());
        String numToHex = (Integer.toHexString(number));
        String numToBinary = (Integer.toBinaryString(number));
        System.out.println(numToHex.toUpperCase());
        System.out.println(numToBinary.toUpperCase());
    }
}
