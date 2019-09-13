package collecting;

import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.Stream;

/**
 * 如果从流中收集元素
 */
public class CollectingResults {
    public static Stream<String> noVowels() throws IOException {
        String contents = new String(Files.readAllBytes(Paths.get("E:\\learn_project\\javalearn\\learn\\src\\main\\java\\temp\\file\\alice30.txt")), StandardCharsets.UTF_8);
        List<String> wordList = Arrays.asList(contents.split("\\PL+"));
        Stream<String> words = wordList.stream();


        return words.map(s -> s.replaceAll("[aeiouAEIOU]", ""));
    }

    public static <T> void show(String label, Set<T> set) {
        System.out.println(label + ": " + set.getClass().getName());
        System.out.println("[" +
                set.stream().limit(10).map(Objects::toString)
                        .collect(Collectors.joining(",")) + "]");
    }

    public static void main(String[] args) throws IOException {
        Iterator<Integer> iter = Stream.iterate(0, n -> n + 1).limit(10).iterator();
        while (iter.hasNext()) {
            System.out.println(iter.next());
        }
        Object[] numbers = Stream.iterate(0, n -> n + 1).limit(10).toArray();
        System.out.println("Object array:" + numbers); // Note it's an object array
        try {
            Integer number = (Integer) numbers[0];//ok
            System.out.println("number: " + number);
            System.out.println("The following statement throws an ecpection:");
            Integer[] numbers2 = (Integer[]) numbers; //Throws exception
        } catch (ClassCastException ex) {
            //noinspection ThrowablePrintedToSystemOut
            System.out.println(ex);
        }
        Integer[] numbers3 = Stream.iterate(0, n -> n + 1).limit(10).toArray(Integer[]::new);
        System.out.println("Integer array: " + numbers3); //Note it's an Integer[] array
        Set<String> noVowelSet = noVowels().collect(Collectors.toSet());
        show("noVowelSet", noVowelSet);
        TreeSet<String> noVoweTreeSet = noVowels().collect(Collectors.toCollection(TreeSet::new));
        show("noVoweTreeSet", noVoweTreeSet);

        String result = noVowels().limit(10).collect(Collectors.joining());
        System.out.println("Joining" + result);
        result = noVowels().limit(10).collect(Collectors.joining(", "));
        System.out.println("Joining with commas: " + result);
        IntSummaryStatistics summmary = noVowels().collect(Collectors.summarizingInt(String::length));
        double averageWordLength = summmary.getAverage();
        double maxWordLength = summmary.getMax();
        System.out.println("Average word length: " + averageWordLength);
        System.out.println("Max word length: " + maxWordLength);
        System.out.println("foreEach");
        noVowels().limit(10).forEach(System.out::println);
    }
}
