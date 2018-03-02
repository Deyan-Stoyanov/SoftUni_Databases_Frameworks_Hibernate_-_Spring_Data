import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;

public class mostFrequentNumber {
    public static void main(String[] args) throws IOException {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        int[] nums = Arrays.stream(reader.readLine().split("\\s+")).mapToInt(Integer::parseInt).toArray();
        int bestNum = 0;
        int count = 0;
        int bestCount = 0;
        for (int i = 0; i < nums.length; i++) {
            for (int j = i; j < nums.length; j++) {
                if(nums[j] == nums[i]){
                    count++;
                }
            }
            if(count>bestCount){
                bestCount = count;
                bestNum = nums[i];
            }
            count = 0;
        }
        System.out.println(bestNum);
    }
}
