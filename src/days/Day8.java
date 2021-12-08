package days;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.*;

public class Day8 implements Day {
    @Override
    public void run0() {
        try {
            List<String> lines = Files.readAllLines(Paths.get("inputs/day8.txt"));
            int count = 0;
            for (String line : lines) {
                String[] output = line.split(" \\| ")[1].split(" ");
                for (String symbol : output) {
                    Set<Character> characters = new HashSet<>();
                    for (int i = 0; i < symbol.length(); i++) {
                        characters.add(symbol.charAt(i));
                    }
                    if (characters.size() == 2 || characters.size() == 4 || characters.size() == 3 || characters.size() == 7) {
                        count++;
                    }
                }
            }
            System.out.println(count);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private final Map<Integer, Set<Character>> defaultMappings = new HashMap<>();

    {
        defaultMappings.put(0, new HashSet<>(Arrays.asList('a', 'b', 'c', 'e', 'f', 'g')));
        defaultMappings.put(1, new HashSet<>(Arrays.asList('c', 'f')));
        defaultMappings.put(2, new HashSet<>(Arrays.asList('a', 'c', 'd', 'e', 'g')));
        defaultMappings.put(3, new HashSet<>(Arrays.asList('a', 'c', 'd', 'f', 'g')));
        defaultMappings.put(4, new HashSet<>(Arrays.asList('b', 'c', 'd', 'f')));
        defaultMappings.put(5, new HashSet<>(Arrays.asList('a', 'b', 'd', 'f', 'g')));
        defaultMappings.put(6, new HashSet<>(Arrays.asList('a', 'b', 'd', 'e', 'f', 'g')));
        defaultMappings.put(7, new HashSet<>(Arrays.asList('a', 'c', 'f')));
        defaultMappings.put(8, new HashSet<>(Arrays.asList('a', 'b', 'c', 'd', 'e', 'f', 'g')));
        defaultMappings.put(9, new HashSet<>(Arrays.asList('a', 'b', 'c', 'd', 'f', 'g')));
    }

    @Override
    public void run1() {
        try {
            List<String> lines = Files.readAllLines(Paths.get("inputs/day8.txt"));
            int count = 0;
            for (String line : lines) {

                String[] input = line.split(" \\| ")[0].split(" ");
                String[] output = line.split(" \\| ")[1].split(" ");
                Map<Character, List<Character>> mapping = new HashMap<>();
                for (String symbol : input) {
                    Set<Character> characters = new HashSet<>();
                    for (int i = 0; i < symbol.length(); i++) {
                        characters.add(symbol.charAt(i));
                    }
                    if (characters.size() == 2) {
                        Set<Character> defaultList = defaultMappings.get(1);
                        for (Character character : characters) {
                            List<Character> allowed = mapping.get(character);
                            if (allowed == null) {
                                allowed = new ArrayList<>(defaultList);
                            } else {
                                allowed.removeIf(key -> !defaultList.contains(key));
                            }
                            mapping.put(character, allowed);
                        }
                    }
                    if (characters.size() == 4) {
                        Set<Character> defaultList = defaultMappings.get(4);
                        for (Character character : characters) {
                            List<Character> allowed = mapping.get(character);
                            if (allowed == null) {
                                allowed = new ArrayList<>(defaultList);
                            } else {
                                allowed.removeIf(key -> !defaultList.contains(key));
                            }
                            mapping.put(character, allowed);
                        }
                    }
                    if (characters.size() == 3) {
                        Set<Character> defaultList = defaultMappings.get(7);
                        for (Character character : characters) {
                            List<Character> allowed = mapping.get(character);
                            if (allowed == null) {
                                allowed = new ArrayList<>(defaultList);
                            } else {
                                allowed.removeIf(key -> !defaultList.contains(key));
                            }
                            mapping.put(character, allowed);
                        }
                    }
                    if (characters.size() == 7) {
                        Set<Character> defaultList = defaultMappings.get(8);
                        for (Character character : characters) {
                            List<Character> allowed = mapping.get(character);
                            if (allowed == null) {
                                allowed = new ArrayList<>(defaultList);
                            } else {
                                allowed.removeIf(key -> !defaultList.contains(key));
                            }
                            mapping.put(character, allowed);
                        }
                    }
                }
                reduce(mapping);

                // At this point we have reduced the number of possible combinations considerably
                // might as well brute force the rest
                boolean totalFound = false;
                for (Character aMapping : mapping.get('a')) {
                    for (Character bMapping : mapping.get('b')) {
                        for (Character cMapping : mapping.get('c')) {
                            for (Character dMapping : mapping.get('d')) {
                                for (Character eMapping : mapping.get('e')) {
                                    for (Character fMapping : mapping.get('f')) {
                                        for (Character gMapping : mapping.get('g')) {
                                            Set<Character> mappingCharacters = new HashSet<>();
                                            mappingCharacters.add(aMapping);
                                            mappingCharacters.add(bMapping);
                                            mappingCharacters.add(cMapping);
                                            mappingCharacters.add(dMapping);
                                            mappingCharacters.add(eMapping);
                                            mappingCharacters.add(fMapping);
                                            mappingCharacters.add(gMapping);
                                            if (mappingCharacters.size() != 7) {
                                                continue;
                                            }

                                            int interimCounter = 0;
                                            boolean interimFound = true;
                                            for (String symbol : output) {
                                                StringBuilder replaced = new StringBuilder();
                                                for (int i = 0; i < symbol.length(); i++) {
                                                    if (symbol.charAt(i) == 'a') {
                                                        replaced.append(aMapping);
                                                    }
                                                    if (symbol.charAt(i) == 'b') {
                                                        replaced.append(bMapping);
                                                    }
                                                    if (symbol.charAt(i) == 'c') {
                                                        replaced.append(cMapping);
                                                    }
                                                    if (symbol.charAt(i) == 'd') {
                                                        replaced.append(dMapping);
                                                    }
                                                    if (symbol.charAt(i) == 'e') {
                                                        replaced.append(eMapping);
                                                    }
                                                    if (symbol.charAt(i) == 'f') {
                                                        replaced.append(fMapping);
                                                    }
                                                    if (symbol.charAt(i) == 'g') {
                                                        replaced.append(gMapping);
                                                    }
                                                }

                                                Set<Character> symbolMappedSet = new HashSet<>();
                                                for (int i = 0; i < replaced.length(); i++) {
                                                    symbolMappedSet.add(replaced.charAt(i));
                                                }

                                                boolean found = false;
                                                for (Integer defaultMappingKey : defaultMappings.keySet()) {
                                                    Set<Character> defaultMapping = defaultMappings.get(defaultMappingKey);
                                                    if (defaultMapping.equals(symbolMappedSet)) {
                                                        interimCounter = interimCounter * 10 + defaultMappingKey;
                                                        found = true;
                                                        break;
                                                    }
                                                }
                                                if (!found) {
                                                    interimFound = false;
                                                    break;
                                                }
                                            }
                                            if (!interimFound) {
                                                continue;
                                            }

                                            count += interimCounter;
                                            totalFound = true;
                                            break;
                                        }
                                        if (totalFound) {
                                            break;
                                        }
                                    }
                                    if (totalFound) {
                                        break;
                                    }
                                }
                                if (totalFound) {
                                    break;
                                }
                            }
                            if (totalFound) {
                                break;
                            }
                        }
                        if (totalFound) {
                            break;
                        }
                    }
                    if (totalFound) {
                        break;
                    }
                }
            }

            System.out.println(count);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private void reduce(Map<Character, List<Character>> mapping) {
        boolean change = true;
        while (change) {
            change = false;
            // remove when pairs
            for (Character key : mapping.keySet()) {
                for (Character key1 : mapping.keySet()) {
                    if (key != key1 && mapping.get(key).size() == 2 && mapping.get(key).equals(mapping.get(key1))) {
                        for (Character key2 : mapping.keySet()) {
                            if (key != key2 && key2 != key1) {
                                List<Character> values = mapping.get(key2);
                                int size = values.size();
                                values.removeAll(mapping.get(key));
                                if (values.size() != size) {
                                    change = true;
                                }
                                mapping.put(key2, values);
                            }
                        }
                    }
                }
            }
            // remove when one
            for (Character key : mapping.keySet()) {
                if (mapping.get(key).size() == 1) {
                    for (Character key1 : mapping.keySet()) {
                        if (key != key1) {
                            List<Character> values = mapping.get(key1);
                            int size = values.size();
                            values.removeAll(mapping.get(key));
                            if (values.size() != size) {
                                change = true;
                            }
                            mapping.put(key1, values);
                        }
                    }
                }
            }
        }
    }
}
