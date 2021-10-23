package day9;

import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class Day9Part1 {

    static final int PREAMBLE_SIZE = 25;

    public static void main(String[] args) throws Exception {
        var input = Files.lines(Paths.get(Day9Part1.class.getResource("/day9/input").toURI()))
                .map(Long::parseLong)
                .collect(Collectors.toList());

        var invalid = findInvalid(input);
        System.out.println(invalid);
    }

    static long findInvalid(List<Long> input) {
        var previous = new ArrayList<>(input.subList(0, PREAMBLE_SIZE));

        for (var n : input.subList(PREAMBLE_SIZE, input.size())) {
            if (!isValid(n, previous)) {
                return n;
            } else {
                previous.remove(0);
            }
            previous.add(n);
        }

        return -1;
    }

    static boolean isValid(long n, List<Long> previous) {
        for (var i = 0; i < previous.size(); i++) {
            var a = previous.get(i);
            for (var j = i + 1; j < previous.size(); j++) {
                var b = previous.get(j);
                if (!a.equals(b) && a + b == n) {
                    return true;
                }
            }
        }
        return false;
    }


}
