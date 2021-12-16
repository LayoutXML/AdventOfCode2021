package days;

import java.io.IOException;
import java.math.BigInteger;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.List;

public class Day16 implements Day {
    @Override
    public void run0() {
        try {
            List<String> lines = Files.readAllLines(Paths.get("inputs/day16.txt"));
            long sum = getVersionSum(hexToBin(lines.get(0)));
            System.out.println(sum);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private long getVersionSum(String binaryIncoming) {
        String binary = binaryIncoming;

        long versionSum = binToDec(binary.substring(0, 3));
        binary = binary.substring(3);

        long type = binToDec(binary.substring(0, 3));
        binary = binary.substring(3);

        if (type == 4) {
            return versionSum;
        }

        long lengthType = binToDec(binary.substring(0, 1));
        binary = binary.substring(1);

        if (lengthType == 0) {
            long subpacketsLength = binToDec(binary.substring(0, 15));
            binary = binary.substring(15);

            long subpacketLengthSum = 0;
            while (subpacketLengthSum < subpacketsLength) {
                int subpacketLength = getFirstSubpacketLength(binary);
                subpacketLengthSum += subpacketLength;
                String subpacketsBinary = binary.substring(0, subpacketLength);
                binary = binary.substring(subpacketLength);
                versionSum += getVersionSum(subpacketsBinary);
            }

            return versionSum;
        }

        long numberOfSubpackets = binToDec(binary.substring(0, 11));
        binary = binary.substring(11);
        for (long i = 0; i < numberOfSubpackets; i++) {
            int subpacketLength = getFirstSubpacketLength(binary);
            String subpacketsBinary = binary.substring(0, subpacketLength);
            binary = binary.substring(subpacketLength);
            versionSum += getVersionSum(subpacketsBinary);
        }

        return versionSum;
    }

    private int getFirstSubpacketLength(String binaryIncoming) {
        String binary = binaryIncoming;
        int length = 0;

        long versionSum = binToDec(binary.substring(0, 3));
        binary = binary.substring(3);
        length += 3;

        long type = binToDec(binary.substring(0, 3));
        binary = binary.substring(3);
        length += 3;

        if (type == 4) {
            String substring;
            do {
                substring = binary.substring(0, 5);
                binary = binary.substring(5);
                length += 5;
            } while (substring.charAt(0) == '1');

            return length;
        }

        long lengthType = binToDec(binary.substring(0, 1));
        binary = binary.substring(1);
        length += 1;

        if (lengthType == 0) {
            long subpacketsLength = binToDec(binary.substring(0, 15));
            length += 15;
            length += subpacketsLength;
            return length;
        }

        long numberOfSubpackets = binToDec(binary.substring(0, 11));
        binary = binary.substring(11);
        length += 11;

        for (long i = 0; i < numberOfSubpackets; i++) {
            int subpacketLength = getFirstSubpacketLength(binary);
            length += subpacketLength;
            binary = binary.substring(subpacketLength);
        }

        return length;
    }

    @Override
    public void run1() {
        try {
            List<String> lines = Files.readAllLines(Paths.get("inputs/day16.txt"));
            long sum = getSum(hexToBin(lines.get(0)));
            System.out.println(sum);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private long getSum(String binaryIncoming) {
        String binary = binaryIncoming;

        long version = binToDec(binary.substring(0, 3));
        binary = binary.substring(3);

        long type = binToDec(binary.substring(0, 3));
        binary = binary.substring(3);

        if (type == 4) {
            String substring;
            StringBuilder binaryValue = new StringBuilder();
            do {
                substring = binary.substring(0, 5);
                binaryValue.append(substring.substring(1));
                binary = binary.substring(5);
            } while (substring.charAt(0) == '1');

            return binToDec(binaryValue.toString());
        }

        long lengthType = binToDec(binary.substring(0, 1));
        binary = binary.substring(1);

        long result = -1;
        if (lengthType == 0) {
            long subpacketsLength = binToDec(binary.substring(0, 15));
            binary = binary.substring(15);

            long subpacketLengthSum = 0;
            while (subpacketLengthSum < subpacketsLength) {
                int subpacketLength = getFirstSubpacketLength(binary);
                subpacketLengthSum += subpacketLength;
                String subpacketsBinary = binary.substring(0, subpacketLength);
                binary = binary.substring(subpacketLength);
                long value = getSum(subpacketsBinary);
                result = processOperation(type, result, value);
            }

            return result;
        }

        long numberOfSubpackets = binToDec(binary.substring(0, 11));
        binary = binary.substring(11);
        for (long i = 0; i < numberOfSubpackets; i++) {
            int subpacketLength = getFirstSubpacketLength(binary);
            String subpacketsBinary = binary.substring(0, subpacketLength);
            binary = binary.substring(subpacketLength);
            long value = getSum(subpacketsBinary);
            result = processOperation(type, result, value);
        }

        return result;
    }

    private long processOperation(long type, long result, long value) {
        if (result == -1) {
            return value;
        }
        if (type == 0) {
            return result + value;
        }
        if (type == 1) {
            return result * value;
        }
        if (type == 2) {
            return Math.min(result, value);
        }
        if (type == 3) {
            return Math.max(result, value);
        }
        if (type == 5) {
            return result > value ? 1 : 0;
        }
        if (type == 6) {
            return result < value ? 1 : 0;
        }
        if (type == 7) {
            return result == value ? 1 : 0;
        }
        return -1;
    }

    private String hexToBin(String s) {
        return new BigInteger(s, 16).toString(2);
    }

    private long binToDec(String s) {
        return Long.parseLong(new BigInteger(s, 2).toString(10));
    }
}
