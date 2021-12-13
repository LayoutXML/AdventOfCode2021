package days;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;

public class Day13 implements Day {
    @Override
    public void run0() {
        try {
            List<String> lines = Files.readAllLines(Paths.get("inputs/day13.txt"));
            int maxX = 0;
            int maxY = 0;
            for (String line : lines) {
                if (line.length() < 3) {
                    break;
                }
                String[] tokens = line.split(",");
                int x = Integer.parseInt(tokens[0]);
                if (x > maxX) {
                    maxX = x;
                }
                int y = Integer.parseInt(tokens[1]);
                if (y > maxY) {
                    maxY = y;
                }
            }
            boolean[][] dots = new boolean[maxX + 1][maxY + 1];
            String firstInstruction = null;
            for (String line : lines) {
                if (line.length() < 3) {
                    continue;
                }
                if (line.startsWith("fold along")) {
                    firstInstruction = line;
                    break;
                }
                String[] tokens = line.split(",");
                int x = Integer.parseInt(tokens[0]);
                int y = Integer.parseInt(tokens[1]);
                dots[x][y] = true;
            }
            int xFold = Integer.parseInt(firstInstruction.substring(13));
            for (int i = xFold + 1; i < maxX + 1; i++) {
                for (int j = 0; j < maxY + 1; j++) {
                    if (dots[i][j]) {
                        dots[-(i - xFold) + xFold][j] = true;
                    }
                }
            }
            maxX = xFold - 1;
            int count = 0;
            for (int i = 0; i < maxX + 1; i++) {
                for (int j = 0; j < maxY + 1; j++) {
                    if (dots[i][j]) {
                        count++;
                    }
                }
            }
            System.out.println(count);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void run1() {
        try {
            List<String> lines = Files.readAllLines(Paths.get("inputs/day13.txt"));
            int maxX = 0;
            int maxY = 0;
            for (String line : lines) {
                if (line.length() < 3) {
                    break;
                }
                String[] tokens = line.split(",");
                int x = Integer.parseInt(tokens[0]);
                if (x > maxX) {
                    maxX = x;
                }
                int y = Integer.parseInt(tokens[1]);
                if (y > maxY) {
                    maxY = y;
                }
            }
            boolean[][] dots = new boolean[maxX + 1][maxY + 1];
            List<String> instructions = new ArrayList<>();
            for (String line : lines) {
                if (line.length() < 3) {
                    continue;
                }
                if (line.startsWith("fold along")) {
                    instructions.add(line);
                    continue;
                }
                String[] tokens = line.split(",");
                int x = Integer.parseInt(tokens[0]);
                int y = Integer.parseInt(tokens[1]);
                dots[x][y] = true;
            }
            for (String instruction : instructions) {
                if (instruction.startsWith("fold along x=")) {
                    int xFold = Integer.parseInt(instruction.substring(13));
                    for (int i = xFold + 1; i < maxX + 1; i++) {
                        for (int j = 0; j < maxY + 1; j++) {
                            if (dots[i][j]) {
                                dots[-(i - xFold) + xFold][j] = true;
                            }
                        }
                    }
                    maxX = xFold - 1;
                } else {
                    int yFold = Integer.parseInt(instruction.substring(13));
                    for (int i = 0; i < maxX + 1; i++) {
                        for (int j = yFold + 1; j < maxY + 1; j++) {
                            if (dots[i][j]) {
                                dots[i][-(j - yFold) + yFold] = true;
                            }
                        }
                    }
                    maxY = yFold - 1;
                }
            }

            for (int i = 0; i < maxY + 1; i++) {
                for (int j = 0; j < maxX + 1; j++) {
                    if (dots[j][i]) {
                        System.out.print("#");
                    } else {
                        System.out.print(" ");
                    }
                }
                System.out.println(".");
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}

