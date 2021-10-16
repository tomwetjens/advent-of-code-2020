package day7;

import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Arrays;
import java.util.Map;
import java.util.Set;
import java.util.stream.Collectors;

public class Day7Part2 {

    public static void main(String[] args) throws Exception {
        var rules = Files.lines(Paths.get(Day7Part2.class.getResource("/day7/input").toURI()))
                .map(Rule::parse)
                .collect(Collectors.toSet());

        System.out.println(requiredBags(rules, "shiny gold"));
    }

    static int requiredBags(Set<Rule> rules, String color) {
        return rules.stream()
                .filter(rule -> rule.outerColor.equals(color))
                .mapToInt(rule -> rule.innerColors.entrySet().stream()
                        .mapToInt(ic -> ic.getValue() + ic.getValue() * requiredBags(rules, ic.getKey()))
                        .sum())
                .sum();
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
