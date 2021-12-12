package days;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.*;

public class Day12 implements Day {
    @Override
    public void run0() {
        try {
            List<String> lines = Files.readAllLines(Paths.get("inputs/day12.txt"));
            Map<String, Set<String>> connections = new HashMap<>();
            for (String line : lines) {
                String[] tokens = line.split("-");
                if (connections.containsKey(tokens[0])) {
                    connections.get(tokens[0]).add(tokens[1]);
                } else {
                    Set<String> values = new HashSet<>();
                    values.add(tokens[1]);
                    connections.put(tokens[0], values);
                }
                if (connections.containsKey(tokens[1])) {
                    connections.get(tokens[1]).add(tokens[0]);
                } else {
                    Set<String> values = new HashSet<>();
                    values.add(tokens[0]);
                    connections.put(tokens[1], values);
                }
            }
            Set<String> visited = new HashSet<>();
            visited.add("start");
            int count = 0;
            for (String startConnection : connections.get("start")) {
                count += traverse(connections, visited, startConnection);
            }
            System.out.println(count);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private int traverse(Map<String, Set<String>> connections, Set<String> visited, String current) {
        Set<String> newVisited = new HashSet<>(visited);
        if (current.equals(current.toLowerCase()) && newVisited.contains(current)) {
            return 0;
        }
        if (current.equals("start")) {
            return 0;
        }
        if (current.equals("end")) {
            return 1;
        }
        if (current.equals(current.toLowerCase())) {
            newVisited.add(current);
        }
        int count = 0;
        for (String connection : connections.get(current)) {
            count += traverse(connections, newVisited, connection);
        }
        return count;
    }

    @Override
    public void run1() {
        try {
            List<String> lines = Files.readAllLines(Paths.get("inputs/day12.txt"));
            Map<String, Set<String>> connections = new HashMap<>();
            for (String line : lines) {
                String[] tokens = line.split("-");
                if (connections.containsKey(tokens[0])) {
                    connections.get(tokens[0]).add(tokens[1]);
                } else {
                    Set<String> values = new HashSet<>();
                    values.add(tokens[1]);
                    connections.put(tokens[0], values);
                }
                if (connections.containsKey(tokens[1])) {
                    connections.get(tokens[1]).add(tokens[0]);
                } else {
                    Set<String> values = new HashSet<>();
                    values.add(tokens[0]);
                    connections.put(tokens[1], values);
                }
            }
            Map<String, Integer> visited = new HashMap<>();
            visited.put("start", 1);
            Set<String> paths = new HashSet<>();
            for (String startConnection : connections.get("start")) {
                paths.addAll(traverse1(connections, visited, "start", startConnection, ""));
            }
            for (String currentDouble : connections.keySet()) {
                if (!currentDouble.equals(currentDouble.toLowerCase()) || currentDouble.equals("start") || currentDouble.equals("end")) {
                    continue;
                }
                for (String startConnection : connections.get("start")) {
                    paths.addAll(traverse1(connections, visited, "start", startConnection, currentDouble));
                }
            }
            System.out.println(paths.size());
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private Set<String> traverse1(Map<String, Set<String>> connections, Map<String, Integer> visited, String currentPath, String current, String currentDouble) {
        Map<String, Integer> newVisited = new HashMap<>(visited);
        String newPath = currentPath + "," + current;
        if (current.equals(current.toLowerCase()) && newVisited.containsKey(current)
                && ((currentDouble.equals(current) && newVisited.get(current) == 2)
                || (!currentDouble.equals(current) && newVisited.get(current) == 1))) {
            return Collections.emptySet();
        }
        if (current.equals("start")) {
            return Collections.emptySet();
        }
        if (current.equals("end")) {
            return Set.of(newPath);
        }
        if (current.equals(current.toLowerCase())) {
            if (newVisited.containsKey(current)) {
                newVisited.put(current, newVisited.get(current) + 1);
            } else {
                newVisited.put(current, 1);
            }
        }
        Set<String> paths = new HashSet<>();
        for (String connection : connections.get(current)) {
            paths.addAll(traverse1(connections, newVisited, newPath, connection, currentDouble));
        }
        return paths;
    }
}
