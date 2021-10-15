package day3;

import java.nio.file.Files;
import java.nio.file.Paths;

public class Day3Part1 {

    public static void main(String[] args) throws Exception {
        var rows = Files.lines(Paths.get(Day3Part1.class.getResource("input").toURI()))
                .map(String::toCharArray)
                .toArray(char[][]::new);

        solve(rows);
    }

    private static void solve(char[][] rows) {
        int y = 0;
        int x = 0;

        int trees = 0;
        while (y < rows.length - 1) {
            x += 3;
            y += 1;

            if (rows[y][x % rows[0].length] == '#') {
                trees++;
            }
        }

        System.out.println(trees);
    }


}
