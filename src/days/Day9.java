package days;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Arrays;
import java.util.List;

public class Day9 implements Day {
    @Override
    public void run0() {
        try {
            List<String> lines = Files.readAllLines(Paths.get("inputs/day9.txt"));
            String[] array = lines.toArray(new String[0]);
            int sum = 0;
            for (int i = 0; i < array.length; i++) {
                String line = array[i];
                for (int j = 0; j < line.length(); j++) {
                    boolean lower = true;
                    // left
                    if (j > 0 && line.charAt(j - 1) <= line.charAt(j)) {
                        lower = false;
                    }
                    // right
                    if (j < line.length() - 1 && line.charAt(j + 1) <= line.charAt(j)) {
                        lower = false;
                    }
                    // top
                    if (i > 0 && array[i - 1].charAt(j) <= line.charAt(j)) {
                        lower = false;
                    }
                    // bottom
                    if (i < array.length - 1 && array[i + 1].charAt(j) <= line.charAt(j)) {
                        lower = false;
                    }
                    if (lower) {
                        sum += 1 + Integer.parseInt(String.valueOf(line.charAt(j)));
                    }
                }
            }
            System.out.println(sum);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void run1() {
        try {
            List<String> lines = Files.readAllLines(Paths.get("inputs/day9.txt"));
            int[][] array = new int[lines.size()][lines.get(0).length()];
            for (int i = 0; i < lines.size(); i++) {
                for (int j = 0; j < lines.get(i).length(); j++) {
                    array[i][j] = Integer.parseInt(String.valueOf(lines.get(i).charAt(j)));
                }
            }
            int[] largest = new int[3]; // inc order
            for (int i = 0; i < array.length; i++) {
                for (int j = 0; j < array[i].length; j++) {
                    int basinSize = recursiveSearch(array, new boolean[array.length][array[0].length], i, j);
                    if (basinSize > largest[0]) {
                        largest[0] = basinSize;
                    }
                    Arrays.sort(largest);
                }
            }
            System.out.println(largest[0] * largest[1] * largest[2]);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private int recursiveSearch(int[][] map, boolean[][] found, int i, int j) {
        if (map[i][j] == 9 || found[i][j]) {
            return 0;
        }
        int sum = 1;
        found[i][j] = true;
        // left
        if (j > 0 && map[i][j - 1] > map[i][j]) {
            sum += recursiveSearch(map, found, i, j - 1);
        }
        // right
        if (j < map[0].length - 1 && map[i][j + 1] > map[i][j]) {
            sum += recursiveSearch(map, found, i, j + 1);
        }
        // top
        if (i > 0 && map[i - 1][j] > map[i][j]) {
            sum += recursiveSearch(map, found, i - 1, j);
        }
        // bottom
        if (i < map.length - 1 && map[i + 1][j] > map[i][j]) {
            sum += recursiveSearch(map, found, i + 1, j);
        }
        return sum;
    }
}
