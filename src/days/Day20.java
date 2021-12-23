package days;

import java.io.IOException;
import java.math.BigInteger;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.List;

public class Day20 implements Day {
    @Override
    public void run0() {
        try {
            List<String> lines = Files.readAllLines(Paths.get("inputs/day20.txt"));
            String algorithm = lines.get(0);
            boolean[][] image = new boolean[lines.size() + 2][lines.get(3).length() + 4];
            for (int i = 2; i < lines.size(); i++) {
                for (int j = 0; j < lines.get(i).length(); j++) {
                    image[i][j + 2] = lines.get(i).charAt(j) == '#';
                }
            }
            image = enhance(algorithm, image);
            image = enhance(algorithm, image);
            int count = 0;
            for (int i = 0; i < image.length; i++) {
                for (int j = 0; j < image[i].length; j++) {
                    if (image[i][j]) {
                        count++;
                    }
                }
            }
            System.out.println(count);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private boolean[][] enhance(String algorithm, boolean[][] image) {
        boolean[][] newImage = new boolean[image.length + 2][image[0].length + 2];
        for (int i = 1; i < image.length - 1; i++) {
            for (int j = 1; j < image[i].length - 1; j++) {
                String binary = "";
                binary += image[i - 1][j - 1] ? "1" : "0";
                binary += image[i - 1][j] ? "1" : "0";
                binary += image[i - 1][j + 1] ? "1" : "0";
                binary += image[i][j - 1] ? "1" : "0";
                binary += image[i][j] ? "1" : "0";
                binary += image[i][j + 1] ? "1" : "0";
                binary += image[i + 1][j - 1] ? "1" : "0";
                binary += image[i + 1][j] ? "1" : "0";
                binary += image[i + 1][j + 1] ? "1" : "0";
                int decimal = binToDec(binary);
                char ad = algorithm.charAt(decimal);
                newImage[i + 1][j + 1] = ad == '#';
            }
        }
        boolean ep = newImage[2][2];
        for (int i = 0; i < newImage.length; i++) {
            newImage[i][0] = ep; // first column
            newImage[i][1] = ep; // second column
            newImage[i][newImage[i].length - 2] = ep; // next to final column
            newImage[i][newImage[i].length - 1] = ep; // final column
        }
        for (int i = 1; i < newImage[0].length - 1; i++) {
            newImage[0][i] = ep; // first row
            newImage[1][i] = ep; // second row
            newImage[newImage.length - 2][i] = ep; // next to final row
            newImage[newImage.length - 1][i] = ep; // final row
        }
        return newImage;
    }

    @Override
    public void run1() {
        try {
            List<String> lines = Files.readAllLines(Paths.get("inputs/day20.txt"));
            String algorithm = lines.get(0);
            boolean[][] image = new boolean[lines.size() + 2][lines.get(3).length() + 4];
            for (int i = 2; i < lines.size(); i++) {
                for (int j = 0; j < lines.get(i).length(); j++) {
                    image[i][j + 2] = lines.get(i).charAt(j) == '#';
                }
            }
            for (int i = 0; i < 50; i++) {
                image = enhance(algorithm, image);
            }
            int count = 0;
            for (int i = 0; i < image.length; i++) {
                for (int j = 0; j < image[i].length; j++) {
                    if (image[i][j]) {
                        count++;
                    }
                }
            }
            System.out.println(count);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private int binToDec(String s) {
        return Integer.parseInt(new BigInteger(s, 2).toString(10));
    }
}

