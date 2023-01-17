package Baekjoon;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class BOJ_1484 {

    public static void main(String[] args) throws IOException {
        BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(System.in));
        int g = Integer.parseInt(bufferedReader.readLine());
        StringBuilder answer = new StringBuilder();
        for (int currentWeight = 1; currentWeight <= 100000; currentWeight++) {
            int start = 1;
            int end = currentWeight;
            long squareNumberOfCurrentWeight = (long) Math.pow(currentWeight, 2);
            while (start <= end) {
                int rememberWeight = (start + end) / 2;
                long gap = squareNumberOfCurrentWeight - (long) Math.pow(rememberWeight, 2);
                if (gap < g) {
                    end = rememberWeight - 1;
                } else if (gap > g) {
                    start = rememberWeight + 1;
                } else {
                    answer.append(currentWeight).append("\n");
                    break;
                }
            }
        }
        if (answer.length() == 0) {
            System.out.println(-1);
        } else {
            System.out.println(answer);
        }
    }
}
