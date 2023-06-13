package Baekjoon;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class BOJ_1010 {

    private static final int[][] TABLE = new int[30][30];

    public static void main(String[] args) throws IOException {
        BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(System.in));
        int testCase = Integer.parseInt(bufferedReader.readLine());
        StringBuilder answer = new StringBuilder();
        while (testCase-- > 0) {
            StringTokenizer stringTokenizer = new StringTokenizer(bufferedReader.readLine());
            int n = Integer.parseInt(stringTokenizer.nextToken());
            int m = Integer.parseInt(stringTokenizer.nextToken());
            answer.append(combination(m, n)).append("\n");
        }
        System.out.println(answer);
    }

    private static int combination(int n, int r) {
        if (TABLE[n][r] > 0) {
            return TABLE[n][r];
        }
        if (n == r || r == 0) {
            return TABLE[n][r] = 1;
        }
        return TABLE[n][r] = combination(n - 1, r - 1) + combination(n - 1, r);
    }
}
