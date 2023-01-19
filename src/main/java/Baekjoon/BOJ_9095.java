package Baekjoon;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class BOJ_9095 {

    private static final int[] SUMS = new int[11];

    public static void main(String[] args) throws IOException {
        BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(System.in));
        int n = Integer.parseInt(bufferedReader.readLine());
        for (int i = 1; i <= 10; i++) {
            sum(i, 0);
        }
        StringBuilder answer = new StringBuilder();
        while (n-- > 0) {
            int number = Integer.parseInt(bufferedReader.readLine());
            answer.append(SUMS[number]).append("\n");
        }
        System.out.println(answer);
    }

    private static void sum(int result, int sum) {
        if (sum == result) {
            SUMS[sum]++;
            return;
        }

        if (sum > result) {
            return;
        }

        for (int i = 1; i <= 3; i++) {
            sum(result, sum + i);
        }
    }
}
