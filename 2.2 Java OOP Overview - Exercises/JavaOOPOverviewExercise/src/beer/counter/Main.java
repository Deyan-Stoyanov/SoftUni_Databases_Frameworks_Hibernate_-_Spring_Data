package beer.counter;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        try {
            String input = reader.readLine();
            while (!input.equalsIgnoreCase("END") && !input.isEmpty()) {
                int[] command = Arrays.stream(input.split(" ")).mapToInt(Integer::parseInt).toArray();
                int boughtBeers = command[0];
                int drankBeers = command[1];
                BeerCounter.buyBeer(boughtBeers);
                BeerCounter.drinkBeer(drankBeers);
                input = reader.readLine();
            }
        } catch (Exception e){

        }finally {
            System.out.printf("%d %d", BeerCounter.getBeerInStock(), BeerCounter.getDrankBeers());
        }
    }
}
