import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class maxSequenceOfEqualElements {
    public static void main(String[] args) throws IOException {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        int[] intArr = Arrays.stream(reader.readLine().split(" ")).mapToInt(Integer::parseInt).toArray();
        int count = 1;
        int bestCount = 1;
        int bestIndex = 0;
        for (int i = 1; i < intArr.length; i++) {
            if(intArr[i] > intArr[i - 1]){
                count++;
            } else {
                count = 1;
            }
            if(count == bestCount){
                bestCount = count;
                bestIndex = i - count + 1;
            }
        }
        for (int i = bestIndex; i < bestIndex + bestCount; i++) {
            System.out.print(intArr[i] + " ");
        }
    }
}
