package collecting;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.*;
import java.util.stream.Stream;

import static java.util.stream.Collectors.*;

/**
 * 下游收集器
 */
public class DownstreamCollectors {
    public static class City {
        private String name;
        private String state;
        private int population;

        public City(String name, String state, int population) {
            this.name = name;
            this.state = state;
            this.population = population;
        }

        public String getName() {
            return name;
        }

        public String getState() {
            return state;
        }

        public int getPopulation() {
            return population;
        }
    }

    public static Stream<City> readCities(String filname) throws IOException {
        return Files.lines(Paths.get(filname)).map(l -> l.split(", ")).map(a -> new City(a[0], a[1], Integer.parseInt(a[2])));
    }

    public static void main(String[] args) throws IOException {
        Stream<Locale> locales = Stream.of(Locale.getAvailableLocales());
        locales = Stream.of(Locale.getAvailableLocales());
        Map<String, Set<Locale>> CountryToLocaleSet = locales.collect(groupingBy(Locale::getCountry, toSet()));
        locales = Stream.of(Locale.getAvailableLocales());
        Map<String, Long> countryToLocalCounts = locales.collect(groupingBy(Locale::getCountry, counting()));
        System.out.println("countryToLocalCounts:" + countryToLocalCounts);

        Stream<City> cities = readCities("E:\\learn_project\\javalearn\\learn\\src\\main\\java\\temp\\file\\cities.txt");
        Map<String, Integer> stateToCityPopulation = cities.collect(groupingBy(City::getState, summingInt(City::getPopulation)));
        System.out.println("stateToCityPopulation: " + stateToCityPopulation);
        cities = readCities("E:\\learn_project\\javalearn\\learn\\src\\main\\java\\temp\\file\\cities.txt");
        Map<String, Optional<String>> stateToLongestCityName = cities.collect(
                groupingBy(
                        City::getState,
                        mapping(City::getName,
                                maxBy(
                                        Comparator.comparing(String::length)
                                )
                        )
                )
        );
        System.out.println("stateTolongestName: " + stateToLongestCityName);


        locales = Stream.of(Locale.getAvailableLocales());
        Map<String, Set<String>> countryToLanguages = locales.collect(
                groupingBy(
                        Locale::getDisplayCountry,
                        mapping(
                                Locale::getDisplayLanguage,
                                toSet()
                        )
                )
        );
        System.out.println("countryToLanguage: " + countryToLanguages);

        cities = readCities("E:\\learn_project\\javalearn\\learn\\src\\main\\java\\temp\\file\\cities.txt");
        Map<String, IntSummaryStatistics> stateToCityPopulationSummary = cities.collect(
                groupingBy(
                        City::getState,
                        summarizingInt(City::getPopulation)
                )
        );
        System.out.println(stateToCityPopulation.get("NY"));

        cities = readCities("E:\\learn_project\\javalearn\\learn\\src\\main\\java\\temp\\file\\cities.txt");
        Map<String, String> stateToCityNames = cities.collect(
                groupingBy(
                        City::getState,
                        reducing(
                                "",
                                City::getName,
                                (s, t) -> s.length() == 0 ? t : s + ", " + t
                        )
                )
        );
        cities = readCities("E:\\learn_project\\javalearn\\learn\\src\\main\\java\\temp\\file\\cities.txt");
        stateToCityNames = cities.collect(
                groupingBy(
                        City::getState,
                        mapping(
                                City::getName,
                                joining(", ")
                        )
                )
        );
        System.out.println("stateToCityNames: " + stateToCityNames);
    }
}
