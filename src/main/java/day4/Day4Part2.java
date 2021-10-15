package day4;

import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.regex.Pattern;

public class Day4Part2 {

    public static final Pattern PATTERN = Pattern.compile("^((" +
            "(byr:(19((2[0-9])|([3-9][0-9]))|(200[0-2])))" +
            "|(iyr:20((1[0-9])|(20)))" +
            "|(eyr:20((2[0-9])|(30)))" +
            "|(hgt:(((1(([5-8][0-9])|(9[0-3])))cm)|(((59)|(6[0-9])|(7[0-6]))in)))" +
            "|(hcl:#[0-9a-f]{6})" +
            "|(ecl:(amb|blu|brn|gry|grn|hzl|oth))" +
            "|(pid:[0-9]{9})" +
            "|(cid:[0-9a-f]+)" +
            ") ?)*$");

    public static void main(String[] args) throws Exception {
        try (var reader = Files.newBufferedReader(Paths.get(Day4Part2.class.getResource("/day4/input").toURI()))) {
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
                    if (PATTERN.matcher(line).matches()) {
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
                }
            } while (line != null);

            System.out.println(valid);
        }
    }


}
