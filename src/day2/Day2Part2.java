package day2;

import java.io.IOException;
import java.net.URISyntaxException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.regex.Pattern;
import java.util.stream.Stream;

public class Day2Part2 {

    public static final Pattern PATTERN = Pattern.compile("^(\\d+)-(\\d+) (.): (.+)$");

    public static void main(String[] args) {
        System.out.println(input()
                .filter(Day2Part2::isValid)
                .count());
    }

    private static boolean isValid(String line) {
        var matcher = PATTERN.matcher(line);
        matcher.find();

        var min = Integer.valueOf(matcher.group(1));
        var max = Integer.valueOf(matcher.group(2));
        var chr = matcher.group(3).charAt(0);
        var password = matcher.group(4);

        return password.charAt(min - 1) == chr ^ password.charAt(max - 1) == chr;
    }

    private static Stream<String> input() {
        try {
            return Files.lines(Paths.get(Day2Part2.class.getResource("input").toURI()));
        } catch (IOException | URISyntaxException e) {
            throw new RuntimeException(e);
        }
    }

}
