import java.util.Scanner;

public class booleanVariable {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        String str = scanner.nextLine();
        boolean myBool = Boolean.parseBoolean(str);
        if(myBool){
            System.out.println("Yes");
        }   else{
            System.out.println("No");
        }
    }
}
