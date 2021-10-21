package day8;

import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

public class Day8Part2 {

    public static void main(String[] args) throws Exception {
        var input = Files.lines(Paths.get(Day8Part2.class.getResource("/day8/input").toURI()))
                .collect(Collectors.toList());

        int index = 0;
        while (index < input.size()) {
            var instruction = input.get(index).split(" ", 2);

            switch (instruction[0]) {
                case "nop" -> {
                    var copy = new ArrayList<>(input);
                    copy.set(index, "jmp " + instruction[1]);
                    run(copy).ifPresent(System.out::println);
                }
                case "jmp" -> {
                    var copy = new ArrayList<>(input);
                    copy.set(index, "nop " + instruction[1]);
                    run(copy).ifPresent(System.out::println);
                }
            }

            index++;
        }
    }

    private static Optional<Integer> run(List<String> input) {
        var ran = new HashSet<Integer>();

        int index = 0;
        int acc = 0;
        while (index < input.size()) {
            if (!ran.add(index)) {
                return Optional.empty();
            }

            var instruction = input.get(index).split(" ", 2);

            var operation = instruction[0];

            switch (operation) {
                case "acc" -> {
                    acc += Integer.parseInt(instruction[1]);
                    index++;
                }
                case "jmp" -> index += Integer.parseInt(instruction[1]);
                case "nop" -> index++;
            }
        }

        return Optional.of(acc);
    }


}
