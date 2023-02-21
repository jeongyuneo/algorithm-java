package Baekjoon;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;

public class BOJ_11057 {

    public static void main(String[] args) throws IOException {
        BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(System.in));
        int n = Integer.parseInt(bufferedReader.readLine());
        int[][] sums = new int[n + 1][10];
        Arrays.fill(sums[0], 1);
        for (int i = 1; i <= n; i++) {
            sums[i][0] = sums[i - 1][0];
            for (int j = 1; j < 10; j++) {
                sums[i][j] = (sums[i - 1][j] + sums[i][j - 1]) % 10007;
            }
        }
        System.out.println(sums[n][9]);
    }
}
