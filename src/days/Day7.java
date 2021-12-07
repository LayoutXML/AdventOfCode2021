package days;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Arrays;
import java.util.List;
import java.util.OptionalDouble;
import java.util.stream.Collectors;

public class Day7 implements Day {
    @Override
    public void run0() {
        try {
            List<String> lines = Files.readAllLines(Paths.get("inputs/day7.txt"));
            String[] tokens = lines.get(0).split(",");
            List<Integer> values = Arrays.stream(tokens)
                    .mapToInt(Integer::parseInt)
                    .boxed()
                    .sorted()
                    .collect(Collectors.toList());
            // median
            int position = values.size() % 2 == 0
                    ? (values.get(values.size() / 2) + values.get(values.size() / 2 - 1)) / 2
                    : values.get(values.size() / 2);
            System.out.println(values.stream()
                    .mapToInt(x -> x)
                    .map(x -> Math.abs(position - x))
                    .sum());
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void run1() {
        try {
            List<String> lines = Files.readAllLines(Paths.get("inputs/day7.txt"));
            String[] tokens = lines.get(0).split(",");
            List<Integer> values = Arrays.stream(tokens)
                    .mapToInt(Integer::parseInt)
                    .boxed()
                    .sorted()
                    .collect(Collectors.toList());
            // floored and ceiled average
            OptionalDouble average = values.stream()
                    .mapToInt(x -> x)
                    .average();
            int floor = (int) Math.floor(average.orElse(0));
            int floorSum = values.stream()
                    .mapToInt(x -> x)
                    .map(x -> Math.abs(floor - x))
                    .map(x -> {
                        int sum = 0;
                        for (int i = 1; i <= x; i++) {
                            sum += i;
                        }
                        return sum;
                    })
                    .sum();
            int ceil = (int) Math.ceil(average.orElse(0));
            int ceilSum = values.stream()
                    .mapToInt(x -> x)
                    .map(x -> Math.abs(ceil - x))
                    .map(x -> {
                        int sum = 0;
                        for (int i = 1; i <= x; i++) {
                            sum += i;
                        }
                        return sum;
                    })
                    .sum();
            System.out.println(Math.min(floorSum, ceilSum));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
