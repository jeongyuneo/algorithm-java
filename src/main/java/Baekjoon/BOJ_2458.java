package Baekjoon;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class BOJ_2458 {

    private static final int MAX_VALUE = 1000001;

    public static void main(String[] args) throws IOException {
        BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer stringTokenizer = new StringTokenizer(bufferedReader.readLine());
        int n = Integer.parseInt(stringTokenizer.nextToken());
        int m = Integer.parseInt(stringTokenizer.nextToken());
        int[][] comparable = new int[n][n];
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                comparable[i][j] = MAX_VALUE;
            }
        }
        for (int i = 0; i < m; i++) {
            stringTokenizer = new StringTokenizer(bufferedReader.readLine());
            int a = Integer.parseInt(stringTokenizer.nextToken()) - 1;
            int b = Integer.parseInt(stringTokenizer.nextToken()) - 1;
            comparable[a][b] = 1;
        }
        for (int pass = 0; pass < n; pass++) {
            for (int start = 0; start < n; start++) {
                if (pass == start) {
                    continue;
                }
                for (int end = 0; end < n; end++) {
                    if (pass == end || start == end) {
                        continue;
                    }
                    comparable[start][end] = Math.min(comparable[start][end], comparable[start][pass] + comparable[pass][end]);
                }
            }
        }

        int comparableStudents = 0;
        for (int i = 0; i < n; i++) {
            int comparingCount = 0;
            for (int j = 0; j < n; j++) {
                if (comparable[i][j] != MAX_VALUE || comparable[j][i] != MAX_VALUE) {
                    comparingCount++;
                }
            }
            if (comparingCount == n - 1) {
                comparableStudents++;
            }
        }
        System.out.println(comparableStudents);
    }
}
