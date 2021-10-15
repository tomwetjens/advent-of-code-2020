package day5;

import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

public class Day5Part2 {

    public static void main(String[] args) throws Exception {
        var lines = Files.lines(Paths.get(Day5Part2.class.getResource("/day5/input").toURI())).collect(Collectors.toList());

        var colStats = lines.stream()
                .mapToInt(Day5Part2::col)
                .summaryStatistics();

        var rowStats = lines.stream()
                .mapToInt(Day5Part2::row)
                .summaryStatistics();

        System.out.println(rowStats);

        var filledSeats = lines.stream()
                .mapToInt(line -> {
                    var row = row(line);
                    var col = col(line);
                    var seat = seat(row, col);
                    System.out.println(line + ": row " + row + ", column " + col + ", seat ID " + seat);
                    return seat;
                })
                .boxed()
                .collect(Collectors.toSet());

        var possibleSeats = IntStream.rangeClosed(rowStats.getMin(), rowStats.getMax())
                .flatMap(row -> IntStream.rangeClosed(colStats.getMin(), colStats.getMax())
                        .map(col -> seat(row, col)))
                .filter(seat -> !filledSeats.contains(seat)
                        && filledSeats.contains(seat - 1) && filledSeats.contains(seat + 1))
                .boxed()
                .collect(Collectors.toSet());

        System.out.println(possibleSeats);
    }

    private static int bin(String str, int low, int high) {
        if (str.isEmpty()) {
            return low;
        }
        var mid = (low + high) / 2;
        var c = str.charAt(0);
        return c == 'F' || c == 'L' ? bin(str.substring(1), low, mid) : bin(str.substring(1), mid, high);
    }

    private static int col(String line) {
        return bin(line.substring(7, 10), 1, 8);
    }

    private static int row(String line) {
        return bin(line.substring(0, 7), 1, 128);
    }

    private static int seat(int row, int col) {
        return row * 8 + col;
    }

}
