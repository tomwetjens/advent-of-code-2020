package day3;

import java.nio.file.Files;
import java.nio.file.Paths;

public class Day3Part2 {

    public static void main(String[] args) throws Exception {
        var rows = Files.lines(Paths.get(Day3Part2.class.getResource("input").toURI()))
                .map(String::toCharArray)
                .toArray(char[][]::new);

        System.out.println(solve(rows, 1, 1)
                * solve(rows, 3, 1)
                * solve(rows, 5, 1)
                * solve(rows, 7, 1)
                * solve(rows, 1, 2));
    }

    private static long solve(char[][] rows, int right, int down) {
        int y = 0;
        int x = 0;

        long trees = 0;
        while (y < rows.length - 1) {
            x += right;
            y += down;

            if (rows[y][x % rows[0].length] == '#') {
                trees++;
            }
        }

        return trees;
    }


}
