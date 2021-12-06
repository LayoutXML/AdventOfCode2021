package days;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.*;
import java.util.stream.Collectors;

public class Day6 implements Day {
    @Override
    public void run0() {
        try {
            List<String> lines = Files.readAllLines(Paths.get("inputs/day6.txt"));
            String[] inputs = lines.get(0).split(",");
            List<Integer> fishes = Arrays.stream(inputs).map(Integer::parseInt).collect(Collectors.toList());
            List<Integer> newFishes = new ArrayList<>();
            for (int i = 0; i < 80; i++) {
                for (Integer fish : fishes) {
                    fish--;
                    if (fish < 0) {
                        fish = 6;
                        newFishes.add(8);
                    }
                    newFishes.add(fish);
                }
                fishes.clear();
                fishes.addAll(newFishes);
                newFishes.clear();
            }
            System.out.println(fishes.size());
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void run1() {
        try {
            List<String> lines = Files.readAllLines(Paths.get("inputs/day6.txt"));
            String[] inputs = lines.get(0).split(",");
            Map<Integer, Long> fishes = new HashMap<>();
            Map<Integer, Long> newFishes = new HashMap<>();
            for (String input : inputs) {
                Integer val = Integer.parseInt(input);
                if (fishes.containsKey(val)) {
                    fishes.put(val, fishes.get(val) + 1);
                } else {
                    fishes.put(val, 1L);
                }
            }
            for (int i = 0; i < 256; i++) {
                for (Integer val : fishes.keySet()) {
                    if (val == 0) {
                        newFishes.put(8, fishes.get(val));
                        newFishes.put(6, fishes.get(val));
                    } else {
                        if (newFishes.containsKey(val - 1)) {
                            newFishes.put(val - 1, fishes.get(val) + newFishes.get(val - 1));
                        } else {
                            newFishes.put(val - 1, fishes.get(val));
                        }
                    }
                }
                fishes = newFishes;
                newFishes = new HashMap<>();
            }
            System.out.println(fishes.values().stream().mapToLong(x -> x).sum());
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
