package days;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;

public class Day3 implements Day {
    @Override
    public void run0() {
        try {
            List<String> lines = Files.readAllLines(Paths.get("inputs/day3.txt"));
            StringBuilder gamma = new StringBuilder();
            StringBuilder epsilon = new StringBuilder();
            for (int i = 0; i < lines.get(0).length(); i++) {
                int s0 = 0, s1 = 0;
                for (String line : lines) {
                    if (line.charAt(i) == '0') {
                        s0++;
                    } else {
                        s1++;
                    }
                }
                if (s0 >= s1) {
                    gamma.append("0");
                    epsilon.append("1");
                } else {
                    gamma.append("1");
                    epsilon.append("0");
                }
            }

            System.out.println(Integer.parseInt(gamma.toString(), 2) * Integer.parseInt(epsilon.toString(), 2));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void run1() {
        try {
            List<String> lines = Files.readAllLines(Paths.get("inputs/day3.txt"));
            String ox = "";
            String co = "";
            List<String> previousFilteredValues = new ArrayList<>(lines);
            List<String> filteredValues = new ArrayList<>();
            for (int i = 0; i < lines.get(0).length(); i++) {
                StringBuilder gamma = new StringBuilder();
                for (int j = 0; j < previousFilteredValues.get(0).length(); j++) {
                    int s0 = 0, s1 = 0;
                    for (String line : previousFilteredValues) {
                        if (line.charAt(j) == '0') {
                            s0++;
                        } else {
                            s1++;
                        }
                    }
                    if (s0 > s1) {
                        gamma.append("0");
                    } else {
                        gamma.append("1");
                    }
                }
                for (String line : previousFilteredValues) {
                    if (line.charAt(i) == gamma.charAt(i)) {
                        filteredValues.add(line);
                    }
                }
                if (filteredValues.size() == 1) {
                    ox = filteredValues.get(0);
                    break;
                }
                previousFilteredValues = filteredValues;
                filteredValues = new ArrayList<>();
            }
            previousFilteredValues = new ArrayList<>(lines);
            filteredValues = new ArrayList<>();
            for (int i = 0; i < lines.get(0).length(); i++) {
                StringBuilder epsilon = new StringBuilder();
                for (int j = 0; j < previousFilteredValues.get(0).length(); j++) {
                    int s0 = 0, s1 = 0;
                    for (String line : previousFilteredValues) {
                        if (line.charAt(j) == '0') {
                            s0++;
                        } else {
                            s1++;
                        }
                    }
                    if (s0 > s1) {
                        epsilon.append("1");
                    } else {
                        epsilon.append("0");
                    }
                }
                for (String line : previousFilteredValues) {
                    if (line.charAt(i) == epsilon.charAt(i)) {
                        filteredValues.add(line);
                    }
                }
                if (filteredValues.size() == 1) {
                    co = filteredValues.get(0);
                    break;
                }
                previousFilteredValues = filteredValues;
                filteredValues = new ArrayList<>();
            }

            System.out.println(Integer.parseInt(ox, 2) * Integer.parseInt(co, 2));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
