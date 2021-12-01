import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;

public class Day1 {
    public void run0() {
        try (BufferedReader br = new BufferedReader(new FileReader(new File("inputs/day1.txt")))) {
            String item;
            int count = 0;
            int previous = Integer.MAX_VALUE;
            while ((item = br.readLine()) != null) {
                if (Integer.parseInt(item) > previous) {
                    count++;
                }
                previous = Integer.parseInt(item);
            }
            System.out.println(count);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void run1() {
        try (BufferedReader br = new BufferedReader(new FileReader(new File("inputs/day1.txt")))) {
            String item;
            int count = 0;
            int answer = 0;
            int[] sums = new int[4];
            boolean firstLoop = true;
            while ((item = br.readLine()) != null) {
                int value = Integer.parseInt(item);
                if (count == 0) {
                    sums[0] = value;
                    sums[2] += value;
                    sums[3] += value;
                    if (!firstLoop && sums[2] > sums[1]) {
                        answer++;
                    }
                } else if (count == 1) {
                    sums[0] += value;
                    sums[1] = value;
                    sums[3] += value;
                    if (!firstLoop && sums[3] > sums[2]) {
                        answer++;
                    }
                } else if (count == 2) {
                    sums[0] += value;
                    sums[1] += value;
                    sums[2] = value;
                    if (!firstLoop && sums[0] > sums[3]) {
                        answer++;
                    }
                } else if (count == 3) {
                    sums[1] += value;
                    sums[2] += value;
                    sums[3] = value;
                    if (sums[1] > sums[0]) {
                        answer++;
                    }
                }
                count++;
                if (count > 3) {
                    firstLoop = false;
                    count = 0;
                }
            }
            System.out.println(answer);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
