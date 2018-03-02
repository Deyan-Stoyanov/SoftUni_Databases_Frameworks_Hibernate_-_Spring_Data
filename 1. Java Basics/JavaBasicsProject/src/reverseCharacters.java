import java.util.Scanner;

public class reverseCharacters {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        char[] charArray = new char[3];
        for (int i = 0; i < 3; i++) {
            char s = scanner.nextLine().charAt(0);
            charArray[i] = s;
        }
        for (int j = charArray.length - 1; j >= 0; j--) {
            System.out.print(charArray[j]);
        }
    }
}
