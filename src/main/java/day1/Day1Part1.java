package day1;

import java.io.IOException;
import java.net.URISyntaxException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.stream.Stream;

public class Day1Part1 {

    public static void main(String[] args) {
        input()
                .map(Integer::valueOf)
                .flatMap(a -> input()
                        .map(Integer::valueOf)
                        .filter(b -> a + b == 2020)
                        .map(b -> a * b))
                .findAny()
                .ifPresent(System.out::println);
    }

    private static Stream<String> input() {
        try {
            return Files.lines(Paths.get(Day1Part1.class.getResource("/day1/input").toURI()));
        } catch (IOException | URISyntaxException e) {
            throw new RuntimeException(e);
        }
    }

}
