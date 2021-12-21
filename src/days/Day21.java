package days;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.List;

public class Day21 implements Day {
    public void run0() {
        try {
            List<String> lines = Files.readAllLines(Paths.get("inputs/day21.txt"));
            int position0 = Integer.parseInt(lines.get(0));
            int position1 = Integer.parseInt(lines.get(1));
            int score0 = 0;
            int score1 = 0;
            int dieCounter = 0;
            boolean firstTurn = true;
            do {
                int die = (dieCounter + 2) * 3;
                dieCounter += 3;
                if (firstTurn) {
                    position0 += die;
                    position0 %= 10;
                    if (position0 == 0) {
                        position0 = 10;
                    }
                    score0 += position0;
                } else {
                    position1 += die;
                    position1 %= 10;
                    if (position1 == 0) {
                        position1 = 10;
                    }
                    score1 += position1;
                }
                firstTurn = !firstTurn;
            } while (score0 < 1000 && score1 < 1000);
            System.out.println(Math.min(score0, score1) * dieCounter);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void run1() {
        try {
            List<String> lines = Files.readAllLines(Paths.get("inputs/day21.txt"));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
