package days;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.*;

public class Day10 implements Day {

    private Map<Character, Integer> scores = new HashMap<>();
    {
        scores.put(')', 3);
        scores.put(']', 57);
        scores.put('}', 1197);
        scores.put('>', 25137);
    }
    @Override
    public void run0() {
        try {
            List<String> lines = Files.readAllLines(Paths.get("inputs/day10.txt"));
            int sum = 0;
            for (String line : lines) {
                Stack<Character> chars = new Stack<>();
                for (int i = 0; i < line.length(); i++) {
                    if (Arrays.asList('(', '[', '{', '<').contains(line.charAt(i))) {
                        chars.push(line.charAt(i));
                    } else if (line.charAt(i) == ')') {
                        char open = chars.pop();
                        if (open != '(') {
                            sum += scores.get(')');
                            break;
                        }
                    } else if (line.charAt(i) == ']') {
                        char open = chars.pop();
                        if (open != '[') {
                            sum += scores.get(']');
                            break;
                        }
                    } else if (line.charAt(i) == '}') {
                        char open = chars.pop();
                        if (open != '{') {
                            sum += scores.get('}');
                            break;
                        }
                    } else if (line.charAt(i) == '>') {
                        char open = chars.pop();
                        if (open != '<') {
                            sum += scores.get('>');
                            break;
                        }
                    }
                }
            }
            System.out.println(sum);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private Map<Character, Integer> scores1 = new HashMap<>();
    {
        scores1.put('(', 1);
        scores1.put('[', 2);
        scores1.put('{', 3);
        scores1.put('<', 4);
    }
    @Override
    public void run1() {
        try {
            List<String> lines = Files.readAllLines(Paths.get("inputs/day10.txt"));
            List<Long> scores = new ArrayList<>();
            for (String line : lines) {
                Stack<Character> chars = new Stack<>();
                boolean invalid = false;
                for (int i = 0; i < line.length(); i++) {
                    if (Arrays.asList('(', '[', '{', '<').contains(line.charAt(i))) {
                        chars.push(line.charAt(i));
                    } else if (line.charAt(i) == ')') {
                        char open = chars.pop();
                        if (open != '(') {
                            invalid = true;
                            break;
                        }
                    } else if (line.charAt(i) == ']') {
                        char open = chars.pop();
                        if (open != '[') {
                            invalid = true;
                            break;
                        }
                    } else if (line.charAt(i) == '}') {
                        char open = chars.pop();
                        if (open != '{') {
                            invalid = true;
                            break;
                        }
                    } else if (line.charAt(i) == '>') {
                        char open = chars.pop();
                        if (open != '<') {
                            invalid = true;
                            break;
                        }
                    }
                }

                if (invalid) {
                    continue;
                }

                long sum = 0;
                while (!chars.empty()) {
                    char seq = chars.pop();
                    sum = sum * 5 + scores1.get(seq);
                }
                System.out.println(sum);
                scores.add(sum);
            }

            Collections.sort(scores);
            System.out.println(scores.get(scores.size() / 2));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}

