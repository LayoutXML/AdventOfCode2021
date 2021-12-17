package days;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.List;

public class Day17 implements Day {
    @Override
    public void run0() {
        try {
            List<String> lines = Files.readAllLines(Paths.get("inputs/day17.txt"));
            int targetX0 = Integer.parseInt(lines.get(0));
            int targetX1 = Integer.parseInt(lines.get(1));
            int targetY0 = Integer.parseInt(lines.get(2));
            int targetY1 = Integer.parseInt(lines.get(3));
            int sY = -1;
            for (int i = 0; i < 10000; i++) {
                for (int j = -10000; j < 10000; j++) {
                    boolean hits = checkIfHits(0, 0, i, j, targetX0, targetX1, targetY0, targetY1);
                    if (hits) {
                        int highestY = getHighestY(0, 0, i, j, targetX0, targetX1, targetY0, targetY1);
                        if (highestY > sY) {
                            sY = highestY;
                        }
                    }
                }
            }
            System.out.println(sY);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void run1() {
        try {
            List<String> lines = Files.readAllLines(Paths.get("inputs/day17.txt"));
            int targetX0 = Integer.parseInt(lines.get(0));
            int targetX1 = Integer.parseInt(lines.get(1));
            int targetY0 = Integer.parseInt(lines.get(2));
            int targetY1 = Integer.parseInt(lines.get(3));
            int count = 0;
            for (int i = 0; i < 10000; i++) {
                for (int j = -10000; j < 10000; j++) {
                    boolean hits = checkIfHits(0, 0, i, j, targetX0, targetX1, targetY0, targetY1);
                    if (hits) {
                        count++;
                    }
                }
            }
            System.out.println(count);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private boolean checkIfHits(int x, int y, int vX, int vY, int tX0, int tX1, int tY0, int tY1) {
        if (x >= tX0 && x <= tX1 && y >= tY0 && y <= tY1) {
            return true;
        }
        x += vX;
        y += vY;
        if (vX != 0) {
            vX += vX > 0 ? -1 : 1;
        }
        vY--;

        if (x > tX1 || y < tY0) {
            return false;
        }

        return checkIfHits(x, y, vX, vY, tX0, tX1, tY0, tY1);
    }

    private int getHighestY(int x, int y, int vX, int vY, int tX0, int tX1, int tY0, int tY1) {
        int oldY = y;

        x += vX;
        y += vY;
        if (vX != 0) {
            vX += vX > 0 ? -1 : 1;
        }
        vY--;

        if (oldY > y) {
            return oldY;
        }

        if (x >= tX0 && x <= tX1 && y >= tY0 && y <= tY1) {
            return y;
        }

        return getHighestY(x, y, vX, vY, tX0, tX1, tY0, tY1);
    }
}
