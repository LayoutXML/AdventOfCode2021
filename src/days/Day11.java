package days;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.List;

public class Day11 implements Day {
    @Override
    public void run0() {
        try {
            List<String> lines = Files.readAllLines(Paths.get("inputs/day11.txt"));
            int[][] array = new int[10][10];
            boolean[][] flashes;
            int count = 0;
            for (int i = 0; i < lines.size(); i++) {
                String line = lines.get(i);
                for (int j = 0; j < line.length(); j++) {
                    array[i][j] = Integer.parseInt(String.valueOf(line.charAt(j)));
                }
            }

            for (int d = 0; d < 100; d++) {
                flashes = new boolean[10][10];
                for (int i = 0; i < 10; i++) {
                    for (int j = 0; j < 10; j++) {
                        array[i][j]++;
                    }
                }

                boolean flashed = true;
                while (flashed) {
                    flashed = false;
                    for (int i = 0; i < 10; i++) {
                        for (int j = 0; j < 10; j++) {
                            if (array[i][j] > 9 && !flashes[i][j]) {
                                count++;
                                flashed = true;
                                flashes[i][j] = true;

                                if (i > 0) {
                                    array[i - 1][j]++;
                                    if (j > 0)
                                        array[i - 1][j - 1]++;
                                    if (j < 9)
                                        array[i - 1][j + 1]++;
                                }
                                if (j > 0)
                                    array[i][j - 1]++;
                                if (j < 9)
                                    array[i][j + 1]++;
                                if (i < 9) {
                                    array[i + 1][j]++;
                                    if (j > 0)
                                        array[i + 1][j - 1]++;
                                    if (j < 9)
                                        array[i + 1][j + 1]++;
                                }
                            }
                        }
                    }
                }

                for (int i = 0; i < 10; i++) {
                    for (int j = 0; j < 10; j++) {
                        if (flashes[i][j]) {
                            array[i][j] = 0;
                        }
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
            List<String> lines = Files.readAllLines(Paths.get("inputs/day11.txt"));
            int[][] array = new int[10][10];
            boolean[][] flashes;
            for (int i = 0; i < lines.size(); i++) {
                String line = lines.get(i);
                for (int j = 0; j < line.length(); j++) {
                    array[i][j] = Integer.parseInt(String.valueOf(line.charAt(j)));
                }
            }


            boolean all = false;
            int d = 0;
            while (!all) {
                flashes = new boolean[10][10];
                for (int i = 0; i < 10; i++) {
                    for (int j = 0; j < 10; j++) {
                        array[i][j]++;
                    }
                }

                boolean flashed = true;
                while (flashed) {
                    flashed = false;
                    for (int i = 0; i < 10; i++) {
                        for (int j = 0; j < 10; j++) {
                            if (array[i][j] > 9 && !flashes[i][j]) {
                                flashed = true;
                                flashes[i][j] = true;

                                if (i > 0) {
                                    array[i - 1][j]++;
                                    if (j > 0)
                                        array[i - 1][j - 1]++;
                                    if (j < 9)
                                        array[i - 1][j + 1]++;
                                }
                                if (j > 0)
                                    array[i][j - 1]++;
                                if (j < 9)
                                    array[i][j + 1]++;
                                if (i < 9) {
                                    array[i + 1][j]++;
                                    if (j > 0)
                                        array[i + 1][j - 1]++;
                                    if (j < 9)
                                        array[i + 1][j + 1]++;
                                }
                            }
                        }
                    }
                }

                all = true;
                for (int i = 0; i < 10; i++) {
                    for (int j = 0; j < 10; j++) {
                        if (flashes[i][j]) {
                            array[i][j] = 0;
                        } else {
                            all = false;
                        }
                    }
                }

                if (all) {
                    System.out.println(d + 1);
                    break;
                }
                d++;
            }

        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
