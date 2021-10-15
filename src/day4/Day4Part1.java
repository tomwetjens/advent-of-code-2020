package day4;

import java.nio.file.Files;
import java.nio.file.Paths;

public class Day4Part1 {

    public static void main(String[] args) throws Exception {
        try (var reader = Files.newBufferedReader(Paths.get(Day4Part1.class.getResource("input").toURI()))) {
            int valid = 0;

            boolean byr = false;
            boolean iyr = false;
            boolean eyr = false;
            boolean hgt = false;
            boolean hcl = false;
            boolean ecl = false;
            boolean pid = false;
            boolean cid = false;

            String line;
            do {
                line = reader.readLine();

                if (line == null || line.isEmpty()) {
                    if (byr && iyr && eyr && hgt && hcl && ecl && pid) {
                        valid++;
                    }

                    byr = false;
                    iyr = false;
                    eyr = false;
                    hgt = false;
                    hcl = false;
                    ecl = false;
                    pid = false;
                    cid = false;
                } else {
                    var pairs = line.split(" ");
                    for (var pair : pairs) {
                        var key = pair.split(":", 2)[0];

                        byr |= "byr".equals(key);
                        iyr |= "iyr".equals(key);
                        eyr |= "eyr".equals(key);
                        hgt |= "hgt".equals(key);
                        hcl |= "hcl".equals(key);
                        ecl |= "ecl".equals(key);
                        pid |= "pid".equals(key);
                        cid |= "cid".equals(key);
                    }
                }
            } while (line != null);

            System.out.println(valid);
        }
    }


}
