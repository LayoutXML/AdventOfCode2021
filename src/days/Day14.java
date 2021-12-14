package days;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Day14 implements Day {
    @Override
    public void run0() {
        try {
            List<String> lines = Files.readAllLines(Paths.get("inputs/day14.txt"));
            String input = lines.get(0);
            Map<String, String> rules = new HashMap<>();
            for (int i = 2; i < lines.size(); i++) {
                String[] tokens = lines.get(i).split(" -> ");
                rules.put(tokens[0], tokens[1]);
            }
            for (int i = 0; i < 10; i++) {
                StringBuilder newInput = new StringBuilder();
                for (int j = 0; j < input.length() - 1; j++) {
                    newInput.append(input.charAt(j));
                    String insert = rules.get(input.substring(j, j + 2));
                    if (insert != null) {
                        newInput.append(insert);
                    }
                }
                newInput.append(input.charAt(input.length() - 1));
                input = newInput.toString();
            }
            Map<Character, Integer> quantities = new HashMap<>();
            for (int i = 0; i < input.length(); i++) {
                Character character = input.charAt(i);
                if (quantities.containsKey(character)) {
                    quantities.put(character, quantities.get(character) + 1);
                } else {
                    quantities.put(character, 1);
                }
            }
            int smallest = quantities.get(quantities.keySet().stream().findAny().orElse(null));
            int largest = smallest;
            for (Character character : quantities.keySet()) {
                if (quantities.get(character) < smallest) {
                    smallest = quantities.get(character);
                }
                if (quantities.get(character) > largest) {
                    largest = quantities.get(character);
                }
            }
            System.out.println(largest - smallest);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void run1() {
        try {
            List<String> lines = Files.readAllLines(Paths.get("inputs/day14.txt"));
            String line = lines.get(0);
            Map<String, Long> input = new HashMap<>();
            for (int i = 0; i < line.length() - 1; i++) {
                if (input.containsKey(line.substring(i, i + 2))) {
                    input.put(line.substring(i, i+ 2), input.get(line.substring(i, i+ 2)) + 1);
                } else{
                    input.put(line.substring(i, i+ 2), 1L);
                }
            }
            Map<String, String> rules = new HashMap<>();
            for (int i = 2; i < lines.size(); i++) {
                String[] tokens = lines.get(i).split(" -> ");
                rules.put(tokens[0], tokens[1]);
            }
            for (int i = 0; i < 40; i++) {
                Map<String, Long> newInput = new HashMap<>();
                for (String pair : input.keySet()) {
                    String insert = rules.get(pair);
                    if (insert != null) {
                        if (newInput.containsKey(pair.charAt(0) + insert)) {
                            newInput.put(pair.charAt(0) + insert, input.get(pair) + newInput.get(pair.charAt(0) + insert));
                        } else {
                            newInput.put(pair.charAt(0) + insert, input.get(pair));
                        }
                        if (newInput.containsKey(insert + pair.charAt(1))) {
                            newInput.put(insert + pair.charAt(1), input.get(pair) + newInput.get(insert + pair.charAt(1)));
                        } else {
                            newInput.put(insert + pair.charAt(1), input.get(pair));
                        }
                    } else {
                        if (newInput.containsKey(pair)) {
                            newInput.put(pair, input.get(pair) + newInput.get(pair));
                        } else {
                            newInput.put(pair, input.get(pair));
                        }
                    }
                }
                input = newInput;
            }
            Map<Character, Long> quantities = new HashMap<>();
            for (String pair : input.keySet()) {
                if (quantities.containsKey(pair.charAt(0))) {
                    quantities.put(pair.charAt(0), quantities.get(pair.charAt(0)) + input.get(pair));
                } else {
                    quantities.put(pair.charAt(0), input.get(pair));
                }
                if (quantities.containsKey(pair.charAt(1))) {
                    quantities.put(pair.charAt(1), quantities.get(pair.charAt(1)) + input.get(pair));
                } else {
                    quantities.put(pair.charAt(1), input.get(pair));
                }
            }
            long smallest = quantities.get(quantities.keySet().stream().findAny().orElse(null));
            long largest = smallest;
            for (Character character : quantities.keySet()) {
                if (quantities.get(character) < smallest) {
                    smallest = quantities.get(character);
                }
                if (quantities.get(character) > largest) {
                    largest = quantities.get(character);
                }
            }
            // Because we store each value twice divide by 2
            // e.g. string ABC would be stored as AB and BC, so we would count B twice even though it's in string once
            System.out.println((long) Math.ceil(largest / 2.0) - (long) Math.ceil(smallest / 2.0));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
