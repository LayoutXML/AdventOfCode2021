package days;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.List;

public class Day4 implements Day {
    @Override
    public void run0() {
        try {
            List<String> lines = Files.readAllLines(Paths.get("inputs/day4.txt"));
            String first = lines.get(0);
            String[][][][] grid = new String[(lines.size() - 1) / 6][5][5][2];
            int index = 2;
            for (int i = 0; i < (lines.size() - 1) / 6; i++) {
                for (int j = 0; j < 5; j++) {
                    String[] tokens = lines.get(index).trim().split("\\s+");
                    for (int k = 0; k < 5; k++) {
                        grid[i][j][k][0] = tokens[k];
                        grid[i][j][k][1] = "";
                    }
                    index++;
                }
                index++;
            }
            for (String seq : first.split(",")) {
                for (int i = 1; i < (lines.size() - 1) / 6; i++) {
                    for (int j = 0; j < 5; j++) {
                        for (int k = 0; k < 5; k++) {
                            if (seq.equals(grid[i][j][k][0])) {
                                grid[i][j][k][1] = "A";
                            }
                        }
                    }

                    boolean win = false;
                    for (int j = 0; j < 5; j++) {
                        boolean row = true;
                        for (int k = 0; k < 5; k++) {
                            if (grid[i][j][k][1].isEmpty()) {
                                row = false;
                                break;
                            }
                        }
                        if (row) {
                            win = true;
                        }
                    }

                    for (int j = 0; j < 5; j++) {
                        boolean col = true;
                        for (int k = 0; k < 5; k++) {
                            if (grid[i][k][j][1].isEmpty()) {
                                col = false;
                                break;
                            }
                        }
                        if (col) {
                            win = true;
                        }
                    }

                    if (win) {
                        int sum = 0;
                        for (int j = 0; j < 5; j++) {
                            for (int k = 0; k < 5; k++) {
                                if (grid[i][j][k][1].isEmpty()) {
                                    sum += Integer.parseInt(grid[i][j][k][0]);
                                }
                            }
                        }
                        sum *= Integer.parseInt(seq);
                        System.out.println(sum);
                        return;
                    }
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void run1() {
        try {
            List<String> lines = Files.readAllLines(Paths.get("inputs/day4.txt"));
            String first = lines.get(0);
            String[][][][] grid = new String[(lines.size() - 1) / 6][5][5][2];
            boolean[] wins = new boolean[(lines.size() - 1) / 6];
            int index = 2;
            int finalScore = 0;
            for (int i = 0; i < (lines.size() - 1) / 6; i++) {
                for (int j = 0; j < 5; j++) {
                    String[] tokens = lines.get(index).trim().split("\\s+");
                    for (int k = 0; k < 5; k++) {
                        grid[i][j][k][0] = tokens[k];
                        grid[i][j][k][1] = "";
                    }
                    index++;
                }
                index++;
            }
            for (String seq : first.split(",")) {
                for (int i = 1; i < (lines.size() - 1) / 6; i++) {
                    for (int j = 0; j < 5; j++) {
                        for (int k = 0; k < 5; k++) {
                            if (seq.equals(grid[i][j][k][0])) {
                                grid[i][j][k][1] = "A";
                            }
                        }
                    }

                    boolean win = false;
                    for (int j = 0; j < 5; j++) {
                        boolean row = true;
                        for (int k = 0; k < 5; k++) {
                            if (grid[i][j][k][1].isEmpty()) {
                                row = false;
                                break;
                            }
                        }
                        if (row) {
                            win = true;
                        }
                    }

                    for (int j = 0; j < 5; j++) {
                        boolean col = true;
                        for (int k = 0; k < 5; k++) {
                            if (grid[i][k][j][1].isEmpty()) {
                                col = false;
                                break;
                            }
                        }
                        if (col) {
                            win = true;
                        }
                    }

                    if (win && !wins[i]) {
                        int sum = 0;
                        for (int j = 0; j < 5; j++) {
                            for (int k = 0; k < 5; k++) {
                                if (grid[i][j][k][1].isEmpty()) {
                                    sum += Integer.parseInt(grid[i][j][k][0]);
                                }
                            }
                        }
                        sum *= Integer.parseInt(seq);
                        wins[i] = true;
                        if (sum != 0) {
                            finalScore = sum;
                        }
                    }
                }
            }
            System.out.println(finalScore);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
