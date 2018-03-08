import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Random;

public class AdvertismentMessage {
    public static void main(String[] args) throws IOException {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        String[] phrases = {"Excellent product.", "Such a great product.", "I always use that product.", "Best product of its category.", "Exceptional product.", "I can’t live without this product."};
        String[] events = {"Now I feel good.", "I have succeeded with this product.", "Makes miracles. I am happy of the results!", "I cannot believe but now I feel awesome.", "Try it yourself, I am very satisfied.", "I feel great!"};
        String[] authors = {"Diana", "Petya", "Stella", "Elena", "Katya", "Iva", "Annie", "Eva"};
        String[] cities = {"Burgas", "Sofia", "Plovdiv", "Varna", "Ruse"};
        int n = Integer.parseInt(reader.readLine());
        Random random = new Random();
        for (int i = 0; i < n; i++) {
            int phraseIndex = random.nextInt(phrases.length);
            int authorIndex = random.nextInt(authors.length);
            int eventIndex = random.nextInt(events.length);
            int cityIndex = random.nextInt(cities.length);
            System.out.printf("%s %s %s - %s%n", phrases[phraseIndex], events[eventIndex], authors[authorIndex], cities[cityIndex]);
        }
    }
}
