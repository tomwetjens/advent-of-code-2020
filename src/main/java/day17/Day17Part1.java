package day17;

import java.io.IOException;
import java.net.URISyntaxException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.HashSet;
import java.util.Set;
import java.util.stream.IntStream;
import java.util.stream.Stream;

public class Day17Part1 {

    public static void main(String[] args) throws Exception {
        var initialState = State.load("input");

        System.out.println("Before any cycles:\n");
        initialState.print();

        var state = initialState;
        for (var i = 1; i <= 6; i++) {
            state = state.cycle();

            System.out.println("After " + i + " cycles:\n");
            state.print();
        }

        System.out.println("Active count: " + state.active().size());
    }

    record Coordinate(int x, int y, int z) {
    }

    record Bounds(Coordinate min, Coordinate max) {
        static final Bounds MIN = new Bounds(new Coordinate(0, 0, 0), new Coordinate(0, 0, 0));

        Bounds combine(Bounds b) {
            return new Bounds(
                    new Coordinate(Math.min(min.x, b.min.x), Math.min(min.y, b.min.y), Math.min(min.z, b.min.z)),
                    new Coordinate(Math.max(max.x, b.max.x), Math.max(max.y, b.max.y), Math.max(max.z, b.max.z)));
        }

        Bounds extend(Coordinate coordinate) {
            return new Bounds(
                    new Coordinate(Math.min(min.x, coordinate.x), Math.min(min.y, coordinate.y), Math.min(min.z, coordinate.z)),
                    new Coordinate(Math.max(max.x, coordinate.x), Math.max(max.y, coordinate.y), Math.max(max.z, coordinate.z)));
        }
    }

    record State(Set<Coordinate> active) {

        void print() {
            var extents = bounds();

            for (var z = extents.min.z; z <= extents.max.z; z++) {
                System.out.println("z=" + z);
                for (var y = extents.min.y; y <= extents.max.y; y++) {
                    for (var x = extents.min.x; x <= extents.max.x; x++) {
                        System.out.print(active.contains(new Coordinate(x, y, z)) ? '#' : '.');
                    }
                    System.out.println();
                }
                System.out.println();
            }
        }

        private Bounds bounds() {
            return active.stream().reduce(Bounds.MIN, Bounds::extend, Bounds::combine);
        }

        State cycle() {
            var extents = bounds();

            var newActive = new HashSet<Coordinate>();

            for (int x = extents.min.x - 1; x <= extents.max.x + 1; x++) {
                for (int y = extents.min.y - 1; y <= extents.max.y + 1; y++) {
                    for (int z = extents.min.z - 1; z <= extents.max.z + 1; z++) {
                        var current = new Coordinate(x, y, z);

                        var neighboursActive = neighbours(current)
                                .filter(active::contains)
                                .limit(4) // Early exit if count will exceed 3
                                .count();

                        if (active.contains(current)) {
                            // If a cube is active and exactly 2 or 3 of its neighbors are also active, the cube remains active.
                            // Otherwise, the cube becomes inactive.
                            if (neighboursActive == 2 || neighboursActive == 3) {
                                newActive.add(current);
                            }
                        } else {
                            // If a cube is inactive but exactly 3 of its neighbors are active, the cube becomes active.
                            // Otherwise, the cube remains inactive.
                            if (neighboursActive == 3) {
                                newActive.add(current);
                            }
                        }
                    }
                }
            }

            return new State(newActive);
        }

        static State load(String file) throws IOException, URISyntaxException {
            var lines = Files.readAllLines(Paths.get(Day17Part1.class.getResource("/day17/" + file).toURI()));

            var active = new HashSet<Coordinate>();

            for (var y = 0; y < lines.size(); y++) {
                var chars = lines.get(y).toCharArray();
                for (var x = 0; x < chars.length; x++) {
                    if (chars[x] == '#') {
                        active.add(new Coordinate(x, y, 0));
                    }
                }
            }

            return new State(active);
        }

        private Stream<Coordinate> neighbours(Coordinate coordinate) {
            return IntStream.rangeClosed(coordinate.x - 1, coordinate.x + 1)
                    .boxed()
                    .flatMap(xn -> IntStream.rangeClosed(coordinate.y - 1, coordinate.y + 1)
                            .boxed()
                            .flatMap(yn -> IntStream.rangeClosed(coordinate.z - 1, coordinate.z + 1)
                                    .mapToObj(zn -> new Coordinate(xn, yn, zn))
                                    .filter(other -> !other.equals(coordinate)))); // Not self
        }
    }

}
