package day1;

import java.io.IOException;
import java.net.URISyntaxException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.stream.Stream;

public class Day1Part2 {

    public static void main(String[] args) {
        input()
                .map(Integer::valueOf)
                .flatMap(a -> input()
                        .map(Integer::valueOf)
                        .flatMap(b -> input()
                                .map(Integer::valueOf)
                                .filter(c -> a + b + c == 2020)
                                .map(c -> a * b * c)))
                .findAny()
                .ifPresent(System.out::println);
    }

    private static Stream<String> input() {
        try {
            return Files.lines(Paths.get(Day1Part2.class.getResource("input").toURI()));
        } catch (IOException | URISyntaxException e) {
            throw new RuntimeException(e);
        }
    }

}
