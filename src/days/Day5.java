package days;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.List;

public class Day5 implements Day {
    @Override
    public void run0() {
        try {
            List<String> lines = Files.readAllLines(Paths.get("inputs/day5.txt"));
            int[][] array = new int[1000][1000];
            for (String line : lines) {
                String[] tokens = line.split(" -> ");
                int x0 = Integer.parseInt(tokens[0].split(",")[0]);
                int y0 = Integer.parseInt(tokens[0].split(",")[1]);
                int x1 = Integer.parseInt(tokens[1].split(",")[0]);
                int y1 = Integer.parseInt(tokens[1].split(",")[1]);
                if (y0 == y1) {
                    int j = y0;
                    double deltaY = 0;
                    for (int i = x0; i != x1; i += (x0 > x1 ? -1 : 1)) {
                        array[i][j] = array[i][j] + 1;
                        j += deltaY;
                    }
                    array[x1][j] = array[x1][j] + 1;
                } else if (x0 == x1) {
                    int j = x0;
                    double deltaX = 0;
                    for (int i = y0; i != y1; i += (y0 > y1 ? -1 : 1)) {
                        array[j][i] = array[j][i] + 1;
                        j += deltaX;
                    }
                    array[j][y1] = array[j][y1] + 1;
                }
            };
            int count = 0;
            for (int i = 0; i < 1000; i++) {
                for (int j = 0; j < 1000; j++) {
                    if (array[i][j]>1) {
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
            List<String> lines = Files.readAllLines(Paths.get("inputs/day5.txt"));
            int[][] array = new int[2000][2000];
            for (String line : lines) {
                String[] tokens = line.split(" -> ");
                int x0 = Integer.parseInt(tokens[0].split(",")[0]);
                int y0 = Integer.parseInt(tokens[0].split(",")[1]);
                int x1 = Integer.parseInt(tokens[1].split(",")[0]);
                int y1 = Integer.parseInt(tokens[1].split(",")[1]);
                if (y0 == y1) {
                    int j = y0;
                    double deltaY = 0;
                    for (int i = x0; i != x1; i += (x0 > x1 ? -1 : 1)) {
                        array[i][j] = array[i][j] + 1;
                        j += deltaY;
                    }
                    array[x1][j] = array[x1][j] + 1;
                } else if (x0 == x1) {
                    int j = x0;
                    double deltaX = 0;
                    for (int i = y0; i != y1; i += (y0 > y1 ? -1 : 1)) {
                        array[j][i] = array[j][i] + 1;
                        j += deltaX;
                    }
                    array[j][y1] = array[j][y1] + 1;
                } else {
                    int j = y0;
                    double deltaY = y1 > y0 ? 1 : -1;
                    for (int i = x0; i != x1; i += (x0 > x1 ? -1 : 1)) {
                        array[i][j] = array[i][j] + 1;
                        j += deltaY;
                    }
                    array[x1][j] = array[x1][j] + 1;
                }
            };
            int count = 0;
            for (int i = 0; i < 2000; i++) {
                for (int j = 0; j < 2000; j++) {
                    if (array[i][j]>1) {
                        count++;
                    }
                }
            }
            System.out.println(count);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
