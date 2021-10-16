package day6;

import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Arrays;

public class Day6Part2 {

    public static void main(String[] args) throws Exception {
        try (var reader = Files.newBufferedReader(Paths.get(Day6Part2.class.getResource("/day6/input").toURI()))) {
            int groupSize = 0;
            int[] qyes = new int['z' + 1];
            int sum = 0;

            String line;
            do {
                line = reader.readLine();

                if (line == null || line.isEmpty()) {
                    for (var n : qyes) {
                        if (n == groupSize) {
                            sum++;
                        }
                    }

                    Arrays.fill(qyes, 0);
                    groupSize = 0;
                } else {
                    groupSize++;
                    for (var q : line.toCharArray()) {
                        qyes[q]++;
                    }
                }
            } while (line != null);

            System.out.println(sum);
        }
    }

}
