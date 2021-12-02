package days;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.List;

public class Day2 implements Day {
    @Override
    public void run0() {
        try {
            List<String> lines = Files.readAllLines(Paths.get("inputs/day2.txt"));
            // at this point I have decided to do this inefficiently by reading all lines to memory as opposed to
            // reading files line by line as I did in Day 1. This is because code efficiency is not rewarded but time
            // to complete the task is, and yesterday's second task would have been much easier and quicker to complete
            // by having it all in memory
            int x = 0, z = 0;
            for (String line : lines) {
                String[] tokens = line.split(" ");
                if ("forward".equals(tokens[0])) {
                    x += Integer.parseInt(tokens[1]);
                } else if ("up".equals(tokens[0])) {
                    z -= Integer.parseInt(tokens[1]);
                } else if ("down".equals(tokens[0])) {
                    z += Integer.parseInt(tokens[1]);
                }
            }
            System.out.println(x * z);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void run1() {
        try {
            List<String> lines = Files.readAllLines(Paths.get("inputs/day2.txt"));
            int x = 0, aim = 0, depth = 0;
            for (String line : lines) {
                String[] tokens = line.split(" ");
                if ("forward".equals(tokens[0])) {
                    x += Integer.parseInt(tokens[1]);
                    depth += aim * Integer.parseInt(tokens[1]);
                } else if ("up".equals(tokens[0])) {
                    aim -= Integer.parseInt(tokens[1]);
                } else if ("down".equals(tokens[0])) {
                    aim += Integer.parseInt(tokens[1]);
                }
            }
            System.out.println(x * depth);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
