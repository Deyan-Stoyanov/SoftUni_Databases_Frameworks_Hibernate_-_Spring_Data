import java.util.Scanner;

public class variableInHexadecimalFormat {
    public static void main(String[] args) {
            Scanner scanner = new Scanner(System.in);
            String numInHex=scanner.nextLine();
            long numInDec=Integer.parseInt(numInHex, 16);
            System.out.println(numInDec);
    }
}
