package day5;

import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.stream.Stream;

public class Day5Part1 {

    public static void main(String[] args) throws Exception {
        Files.lines(Paths.get(Day5Part1.class.getResource("/day5/input").toURI()))
//        Stream.of("BFFFBBFRRR", "FFFBBBFRRR", "BBFFBBFRLL")
                .mapToInt(line -> {
                    var row = bin(line.substring(0, 7), 1, 128);
                    var col = bin(line.substring(7, 10), 1, 8);
                    var seat = row * 8 + col;
                    System.out.println(line + ": row " + row + ", column " + col + ", seat ID " + seat);
                    return seat;
                })
                .max()
                .ifPresent(System.out::println);
    }

    private static int bin(String str, int low, int high) {
        if (str.isEmpty()) {
            return low;
        }
        var mid = (low + high) / 2;
        var c = str.charAt(0);
        return c == 'F' || c == 'L' ? bin(str.substring(1), low, mid) : bin(str.substring(1), mid, high);
    }

}
