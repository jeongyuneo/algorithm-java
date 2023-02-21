package Baekjoon;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class BOJ_1932 {

    public static void main(String[] args) throws IOException {
        BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(System.in));
        int n = Integer.parseInt(bufferedReader.readLine());
        int[][] triangle = new int[n][n];
        for (int i = 0; i < n; i++) {
            StringTokenizer stringTokenizer = new StringTokenizer(bufferedReader.readLine());
            for (int j = 0; j < i + 1; j++) {
                triangle[i][j] = Integer.parseInt(stringTokenizer.nextToken());
            }
        }
        for (int i = n - 1; i >= 1; i--) {
            for (int j = 1; j < i + 1; j++) {
                triangle[i - 1][j - 1] += Math.max(triangle[i][j - 1], triangle[i][j]);
            }
        }
        System.out.println(triangle[0][0]);
    }
}
