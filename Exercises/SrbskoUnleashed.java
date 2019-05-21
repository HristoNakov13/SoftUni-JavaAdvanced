import java.io.IOException;
import java.util.*;
import java.util.concurrent.atomic.AtomicLong;

public class SrbskoUnleashed {
    public static void main(String[] args) throws IOException {

        Scanner scanner = new Scanner(System.in);

        LinkedHashMap<String, LinkedHashMap<String, Integer>> populationStats = new LinkedHashMap<>();
        String input;

        while (!"report".equals(input = scanner.nextLine())) {

            String[] lineData = input.split("\\|");
            String country = lineData[1];
            String city = lineData[0];
            int population = Integer.parseInt(lineData[2]);

            populationStats.putIfAbsent(country, new LinkedHashMap<>());
            populationStats.get(country).put(city, population);
        }
        ;
        Map<String, Long> totalPopulationByCountry = new HashMap<>();

        for (Map.Entry<String, LinkedHashMap<String, Integer>> country : populationStats.entrySet()) {
            long popSum = 0;
            for (Map.Entry<String, Integer> city : country.getValue().entrySet()) {
                popSum += city.getValue();
            }
            totalPopulationByCountry.put(country.getKey(), popSum);
        }

        populationStats.entrySet().stream()
                .sorted((country1, country2) ->
                        Long.compare(totalPopulationByCountry.get(country2.getKey()),
                                totalPopulationByCountry.get(country1.getKey())))
                .forEach(country -> {

                    System.out.println(String.format("%s (total population: %d)"
                            , country.getKey(), totalPopulationByCountry.get(country.getKey())));

                    country.getValue().entrySet().stream()
                            .sorted((city1, city2) -> Integer.compare(city2.getValue(), city1.getValue()))
                            .forEach(city -> System.out.println(String.format("=>%s: %d"
                                    , city.getKey(), city.getValue())));
                });
    }
}