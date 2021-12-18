package days;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.List;

public class Day18 implements Day {
    @Override
    public void run0() {
        try {
            List<String> lines = Files.readAllLines(Paths.get("inputs/day18.txt"));
            String original = null;
            for (String line : lines) {
                if (original == null) {
                    original = line;
                    continue;
                }
                original = add(original, line);
            }
            System.out.println(magnitude(original));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void run1() {
        try {
            List<String> lines = Files.readAllLines(Paths.get("inputs/day18.txt"));
            int largest = 0;
            for (String line0 : lines) {
                for (String line1 : lines) {
                    if (line0.equals(line1)) {
                        continue;
                    }

                    String sum = add(line0, line1);
                    int magnitude = magnitude(sum);
                    if (magnitude > largest) {
                        largest = magnitude;
                    }
                }
            }
            System.out.println(largest);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private String add(String a, String b) {
        String original = "[" + a + "," + b + "]";

        boolean change;
        do {
            change = false;
            String changed = explode(original);
            if (changed.length() != original.length()) {
                change = true;
            }
            if (!change) {
                changed = split(original);
                if (changed.length() != original.length()) {
                    change = true;
                }
            }
            original = changed;
        } while (change);

        return original;
    }

    private String explode(String a) {
        int pairCount = 0;
        int previousPairCount = 0;
        int location = -1;
        for (int i = 0; i < a.length(); i++) {
            previousPairCount = pairCount;

            if (a.charAt(i) == '[') {
                pairCount++;
            } else if (a.charAt(i) == ']') {
                pairCount--;
            }

            if (previousPairCount >= 5 && pairCount < previousPairCount) {
                break;
            }

            if (pairCount >= 5 && pairCount > previousPairCount) {
                location = i;
            }
        }

        if (location < 0) {
            return a;
        }

        int leftDigit = (a.length() > location + 2 && Character.isDigit(a.charAt(location + 2)))
                ? Integer.parseInt(a.substring(location + 1, location + 3))
                : Integer.parseInt(a.substring(location + 1, location + 2));

        int leftDigitLength = leftDigit > 9 ? 2 : 1;
        int rightDigit = (a.length() > location + 3 + leftDigitLength && Character.isDigit(a.charAt(location + 3 + leftDigitLength)))
                ? Integer.parseInt(a.substring(location + 2 + leftDigitLength, location + 4 + leftDigitLength))
                : Integer.parseInt(a.substring(location + 2 + leftDigitLength, location + 3 + leftDigitLength));

        int leftLocation = -1;
        int leftLength = -1;
        for (int i = location - 2; i >= 0; i--) {
            if (Character.isDigit(a.charAt(i))) {
                if (i > 0 && Character.isDigit(a.charAt(i - 1))) {
                    leftLocation = i - 1;
                    leftLength = 2;
                } else {
                    leftLocation = i;
                    leftLength = 1;
                }
                break;
            }
        }
        int leftNumber = leftLocation > 0 ? Integer.parseInt(a.substring(leftLocation, leftLocation + leftLength)) + leftDigit : -1;

        int rightLocation = -1;
        int rightLength = -1;
        int rightDigitLength = (rightDigit > 9 ? 2 : 1);
        for (int i = location + 3 + leftDigitLength + rightDigitLength; i < a.length(); i++) {
            if (Character.isDigit(a.charAt(i))) {
                rightLocation = i;
                if (i < a.length() - 1 && Character.isDigit(a.charAt(i + 1))) {
                    rightLength = 2;
                } else {
                    rightLength = 1;
                }
                break;
            }
        }
        int rightNumber = rightLocation > 0 ? Integer.parseInt(a.substring(rightLocation, rightLocation + rightLength)) + rightDigit : -1;

        String b;
        if (leftLocation > 0 && rightLocation > 0) {
            b = a.substring(0, leftLocation) + leftNumber + a.substring(leftLocation + leftLength, location) + 0
                    + a.substring(location + 3 + leftDigitLength + rightDigitLength, rightLocation) + rightNumber + a.substring(rightLocation + rightLength);
        } else if (leftLocation > 0) {
            b = a.substring(0, leftLocation) + leftNumber + a.substring(leftLocation + leftLength, location) + 0
                    + a.substring(location + 3 + leftDigitLength + rightDigitLength);
        } else {
            b = a.substring(0, location) + 0
                    + a.substring(location + 3 + leftDigitLength + rightDigitLength, rightLocation) + rightNumber + a.substring(rightLocation + rightLength);
        }

        return b;
    }

    private String split(String a) {
        int location = -1;
        for (int i = 0; i < a.length() - 1; i++) {
            if (Character.isDigit(a.charAt(i)) && Character.isDigit(a.charAt(i + 1))) {
                location = i;
                break;
            }
        }

        if (location < 0) {
            return a;
        }

        int digit = Integer.parseInt(a.substring(location, location + 2));

        return a.substring(0, location) + "[" + (int) Math.floor(digit / 2.0) + "," + (int) Math.ceil(digit / 2.0) + "]" + a.substring(location + 2);
    }

    private int magnitude(String a) {
        try {
            return Integer.parseInt(a);
        } catch (NumberFormatException e) {
            // ignore
        }

        int separatorLocation = -1;
        int level = 0;
        for (int i = 0; i < a.length(); i++) {
            if (a.charAt(i) == '[') {
                level++;
            } else if (a.charAt(i) == ']') {
                level--;
            } else if (a.charAt(i) == ',' && level == 1) {
                separatorLocation = i;
                break;
            }
        }

        int magnitudeLeft = 3 * magnitude(a.substring(1, separatorLocation));
        int magnitudeRight = 2 * magnitude(a.substring(separatorLocation + 1, a.length() - 1));

        return magnitudeLeft + magnitudeRight;
    }
}
