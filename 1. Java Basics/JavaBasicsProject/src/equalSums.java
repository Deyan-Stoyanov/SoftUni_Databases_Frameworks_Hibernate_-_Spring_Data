import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;

public class equalSums {
    public static void main(String[] args) throws IOException {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        int[] nums = Arrays.stream(reader.readLine().split("\\s+")).mapToInt(Integer::parseInt).toArray();
        int sum1 = 0;
        int sum2 = 0;
        int special = -1;
        for (int i = 0; i < nums.length; i++) {
            sum1 = 0;
            sum2 = 0;
            for (int j = 0; j < i; j++)
            {
                sum1 += nums[j];
            }

            for (int j = i + 1; j < nums.length; j++)
            {
                sum2 += nums[j];
            }

            if (sum1 == sum2)
            {
                System.out.println(i);
                return;
            }
        }
        System.out.println("no");

    }
}
