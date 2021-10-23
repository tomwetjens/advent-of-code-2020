package day9;

import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class Day9Part2 {

    static final int PREAMBLE_SIZE = 25;

    public static void main(String[] args) throws Exception {
        var input = Files.lines(Paths.get(Day9Part1.class.getResource("/day9/input").toURI()))
                .map(Long::parseLong)
                .collect(Collectors.toList());

        var invalid = findInvalid(input);
        System.out.println(invalid);

        var contiguous = findContiguousSet(input, invalid);
        System.out.println(contiguous);

        var stats = contiguous.stream().mapToLong(Long::longValue).summaryStatistics();
        System.out.println(stats.getMin() + stats.getMax());
    }

    static List<Long> findContiguousSet(List<Long> input, long invalid) {
        for (var i = 0; i < input.size(); i++) {
            var a = input.get(i);

            var contiguous = new ArrayList<Long>();
            contiguous.add(a);

            var sum = a;

            for (var j = i + 1; sum <= invalid && j < input.size(); j++) {
                var b = input.get(j);
                contiguous.add(b);

                sum += b;

                if (sum == invalid) {
                    return contiguous;
                }
            }
        }

        return null;
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
