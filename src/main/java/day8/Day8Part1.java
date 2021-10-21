package day8;

import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.*;
import java.util.stream.Collectors;

public class Day8Part1 {

    public static void main(String[] args) throws Exception {
        var input = Files.lines(Paths.get(Day8Part1.class.getResource("/day8/input").toURI()))
                .collect(Collectors.toList());

        run(input);
    }

    private static void run(List<String> input) {
        var ran = new HashSet<Integer>();

        int index = 0;
        int acc = 0;
        while (index < input.size()) {
            if (!ran.add(index)) {
                System.out.println(acc);
                break;
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
    }


}
