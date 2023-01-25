package Baekjoon;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class BOJ_2579 {

    private static final int ONE_STEP = 0;
    private static final int TWO_STEP = 1;

    public static void main(String[] args) throws IOException {
        BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(System.in));
        int n = Integer.parseInt(bufferedReader.readLine());
        int[] stairs = new int[n + 1];
        for (int i = 1; i <= n; i++) {
            stairs[i] = Integer.parseInt(bufferedReader.readLine());
        }
        int[][] scores = new int[n + 1][2];
        scores[1][ONE_STEP] = stairs[1];
        scores[1][TWO_STEP] = stairs[1];
        for (int i = 2; i <= n; i++) {
            scores[i][ONE_STEP] = scores[i - 1][TWO_STEP] + stairs[i];
            scores[i][TWO_STEP] = Math.max(scores[i - 2][ONE_STEP], scores[i - 2][TWO_STEP]) + stairs[i];
        }
        System.out.println(Math.max(scores[n][ONE_STEP], scores[n][TWO_STEP]));
    }
}
