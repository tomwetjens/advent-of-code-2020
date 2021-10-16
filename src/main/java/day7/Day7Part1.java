package day7;

import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class Day7Part1 {

    public static void main(String[] args) throws Exception {
        var rules = Files.lines(Paths.get(Day7Part1.class.getResource("/day7/input").toURI()))
                .map(Rule::parse)
                .collect(Collectors.toSet());

        System.out.println(possiblePaths(rules, "shiny gold")
                .map(path -> path.get(0))
                .distinct()
                .count());
    }

    static Stream<List<String>> possiblePaths(Set<Rule> rules, String color) {
        System.out.println("Checking rules which bag can contain " + color);
        return rules.stream()
                .filter(rule -> rule.innerColors.getOrDefault(color, 0) > 0)
                .flatMap(rule -> Stream.concat(Stream.of(List.of(rule.outerColor)),
                        possiblePaths(rules, rule.outerColor)
                                .map(path -> Stream.concat(path.stream(), Stream.of(rule.outerColor)).collect(Collectors.toList()))));
    }

    record Rule(String outerColor,
                Map<String, Integer> innerColors) {

        static Rule parse(String line) {
            var parts = line.split("( bags contain | bags?, | bags?\\.)");

            var outerColor = parts[0];

            var innerColors = Arrays.stream(parts, 1, parts.length)
                    .filter(p -> !"no other".equals(p))
                    .map(part -> part.split(" ", 2))
                    .collect(Collectors.toMap(p -> p[1], p -> Integer.valueOf(p[0])));

            return new Rule(outerColor, innerColors);
        }
    }
}
