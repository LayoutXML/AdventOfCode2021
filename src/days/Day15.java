package days;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.List;

public class Day15 implements Day {
    @Override
    public void run0() {
        try {
            List<String> lines = Files.readAllLines(Paths.get("inputs/day15.txt"));
            Node[][] array = new Node[lines.size()][lines.get(0).length()];
            for (int i = 0; i < lines.size(); i++) {
                for (int j = 0; j < lines.get(i).length(); j++) {
                    array[i][j] = new Node();
                    array[i][j].risk = Integer.parseInt(String.valueOf(lines.get(i).charAt(j)));
                }
            }
            calculateDistances(array);
            System.out.println(array[array.length - 1][array[0].length - 1].distanceToStart);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void run1() {
        try {
            List<String> lines = Files.readAllLines(Paths.get("inputs/day15.txt"));
            Node[][] array = new Node[lines.size() * 5][lines.get(0).length() * 5];
            for (int i = 0; i < lines.size(); i++) {
                for (int j = 0; j < lines.get(i).length(); j++) {
                    int initialRisk = Integer.parseInt(String.valueOf(lines.get(i).charAt(j)));
                    for (int k = 0; k < 5; k++) {
                        for (int l = 0; l < 5; l++) {
                            array[i + lines.size() * k][j + lines.get(0).length() * l] = new Node();
                            int risk = (initialRisk + k + l) % 9;
                            if (risk == 0) {
                                risk = 9;
                            }
                            array[i + lines.size() * k][j + lines.get(0).length() * l].risk = risk;
                        }
                    }
                }
            }
            calculateDistances(array);
            System.out.println(array[array.length - 1][array[0].length - 1].distanceToStart);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    // My own take on Dijkstra's algorithm, optimized for this problem alone
    private void calculateDistances(Node[][] array) {
        array[0][0].distanceToStart = 0;
        array[0][0].visited = true;
        calculateDistancesForCell(array, 0, 0);

        int lowestDistance = -1;
        int lowestDistanceX = -1;
        int lowestDistanceY = -1;
        do {
            lowestDistance = -1;
            for (int y = 0; y < array.length; y++) {
                for (int x = 0; x < array[0].length; x++) {
                    if (!array[y][x].visited && array[y][x].distanceToStart != -1 && (lowestDistance == -1 || lowestDistance > array[y][x].distanceToStart)) {
                        lowestDistance = array[y][x].distanceToStart;
                        lowestDistanceX = x;
                        lowestDistanceY = y;
                    }
                }
            }
            if (lowestDistance != -1) {
                calculateDistancesForCell(array, lowestDistanceX, lowestDistanceY);
            }
        } while (lowestDistance != -1);
    }

    private void calculateDistancesForCell(Node[][] array, int x, int y) {
        array[y][x].visited = true;
        calculateDistanceForNeighbourCell(array, x, y, x - 1, y);
        calculateDistanceForNeighbourCell(array, x, y, x + 1, y);
        calculateDistanceForNeighbourCell(array, x, y, x, y - 1);
        calculateDistanceForNeighbourCell(array, x, y, x, y + 1);
    }

    private void calculateDistanceForNeighbourCell(Node[][] array, int x, int y, int neighbourX, int neighbourY) {
        if (neighbourX < 0 || neighbourY < 0 || neighbourX >= array[0].length || neighbourY >= array.length) {
            return;
        }

        int distanceToStart = array[y][x].distanceToStart + array[neighbourY][neighbourX].risk;
        if (array[neighbourY][neighbourX].distanceToStart == -1 || array[neighbourY][neighbourX].distanceToStart > distanceToStart) {
            array[neighbourY][neighbourX].distanceToStart = distanceToStart;
        }
    }

    private static class Node {
        private int distanceToStart = -1;
        private boolean visited;
        private int risk;
    }
}

