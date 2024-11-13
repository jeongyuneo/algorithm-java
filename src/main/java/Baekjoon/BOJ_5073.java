package Baekjoon;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.Comparator;
import java.util.StringTokenizer;

public class BOJ_5073 {

    private static final String DELIMITER = " ";
    private static final String EQUILATERAL = "Equilateral";
    private static final String ISOSCELES = "Isosceles";
    private static final String SCALENE = "Scalene";
    private static final String INVALID = "Invalid";
    private static final int SIDE_COUNT = 3;
    private static final int END_CONDITION = 0;

    public static void main(String[] args) throws IOException {
        BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(System.in));
        final StringBuilder result = new StringBuilder();
        while (true) {
            final Integer[] inputs = readInput(bufferedReader);
            if (inputs[0] == END_CONDITION) {
                break;
            }
            result.append(findTriangleType(inputs)).append("\n");
        }
        System.out.println(result);
    }

    private static Integer[] readInput(BufferedReader bufferedReader) throws IOException {
        Integer[] inputs = new Integer[SIDE_COUNT];
        final StringTokenizer stringTokenizer = new StringTokenizer(bufferedReader.readLine());
        for (int i = 0; i < SIDE_COUNT; i++) {
            inputs[i] = Integer.valueOf(stringTokenizer.nextToken());
        }
        return inputs;
    }

    private static String findTriangleType(Integer[] inputs) throws IOException {
        Arrays.sort(inputs, Comparator.reverseOrder());
        Integer first = inputs[0];
        Integer second = inputs[1];
        Integer third = inputs[2];
        if (first.equals(second) && second.equals(third)) {
            return EQUILATERAL;
        }
        if (first + second <= third || second + third <= first || third + first <= second) {
            return INVALID;
        }
        if (!first.equals(second) && !second.equals(third) && !third.equals(first)) {
            return SCALENE;
        }
        return ISOSCELES;
    }
}
