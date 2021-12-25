package days;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;

public class Day25 implements Day {
    @Override
    public void run0() {
        try {
            List<String> lines = Files.readAllLines(Paths.get("inputs/day25.txt"));
            boolean changed = true;
            int count = 0;
            while (changed) {
                changed = false;
                count++;
                if (moveEast(lines)) {
                    changed = true;
                }
                if (moveSouth(lines)) {
                    changed = true;
                }
            }
            System.out.println(count);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private boolean moveEast(List<String> lines) {
        boolean changed = false;
        for (int i = 0; i < lines.size(); i++) {
            String oldLine = lines.get(i);
            StringBuilder line = new StringBuilder(oldLine);
            for (int j = 0; j < line.length(); j++) {
                if (oldLine.charAt(j) != '>') {
                    continue;
                }

                int nextLocation = j + 1;
                if (nextLocation == line.length()) {
                    nextLocation = 0;
                }

                if (oldLine.charAt(nextLocation) == '.') {
                    changed = true;
                    line.setCharAt(nextLocation, '>');
                    line.setCharAt(j, '.');
                }
            }
            lines.set(i, line.toString());
        }
        return changed;
    }

    private boolean moveSouth(List<String> lines) {
        List<String> oldLines = new ArrayList<>(lines);

        boolean changed = false;
        for (int i = 0; i < lines.get(0).length(); i++) {
            for (int j = 0; j < lines.size(); j++) {
                if (oldLines.get(j).charAt(i) != 'v') {
                    continue;
                }

                int nextLocation = j + 1;
                if (nextLocation == lines.size()) {
                    nextLocation = 0;
                }

                if (oldLines.get(nextLocation).charAt(i) == '.') {
                    changed = true;
                    StringBuilder replaceable0 = new StringBuilder(lines.get(nextLocation));
                    replaceable0.setCharAt(i, 'v');
                    StringBuilder replaceable1 = new StringBuilder(lines.get(j));
                    replaceable1.setCharAt(i, '.');
                    lines.set(nextLocation, replaceable0.toString());
                    lines.set(j, replaceable1.toString());
                }
            }
        }
        return changed;
    }

    @Override
    public void run1() {
        // no second problem this day
    }
}
