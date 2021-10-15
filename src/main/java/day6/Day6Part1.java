package day6;

import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Arrays;

public class Day6Part1 {

    public static void main(String[] args) throws Exception {
        try (var reader = Files.newBufferedReader(Paths.get(Day6Part1.class.getResource("/day6/input").toURI()))) {
            boolean[] qyes = new boolean['z' + 1];
            int yess = 0;

            String line;
            do {
                line = reader.readLine();

                if (line == null || line.isEmpty()) {
                    Arrays.fill(qyes, false);
                } else {
                    for (var q : line.toCharArray()) {
                        if (!qyes[q]) {
                            qyes[q] = true;
                            yess++;
                        }
                    }
                }
            } while (line != null);

            System.out.println(yess);
        }
    }

}
