import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class MapDistricts {
    public static void main(String[] args) throws IOException {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        Map<String, List<Integer>> townsAndPopulation = new HashMap<>();
        String[] tokens = reader.readLine().split("\\s+");
        for (String s:tokens) {
            String[] data = s.split(":");
            String town = data[0];
            int population = Integer.parseInt(data[1]);
            townsAndPopulation.putIfAbsent(town, new ArrayList<>());
            townsAndPopulation.get(town).add(population);
        }
        int populationToMatch = Integer.parseInt(reader.readLine());

        townsAndPopulation.entrySet()
                .stream()
                .filter(x -> x.getValue()
                        .stream()
                        .mapToInt(Integer::valueOf)
                        .sum() >= populationToMatch)
                .sorted((a1, a2) -> Integer.compare(
                        a2.getValue().stream().mapToInt(Integer::valueOf).sum(),
                        a1.getValue().stream().mapToInt(Integer::valueOf).sum()
                ))
                .forEach((x) -> {
                    if(!townsAndPopulation.entrySet().isEmpty()){
                        System.out.printf("%s: ", x.getKey());
                        x.getValue()
                                .stream()
                                .sorted(Comparator.reverseOrder())
                                .limit(5)
                                .forEach(y -> System.out.printf("%s ", y));
                        System.out.println();
                    }
        });
    }
}
