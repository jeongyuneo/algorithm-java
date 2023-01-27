package Baekjoon;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class BOJ_2458 {

    public static void main(String[] args) throws IOException {
        BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer stringTokenizer = new StringTokenizer(bufferedReader.readLine());
        int n = Integer.parseInt(stringTokenizer.nextToken());
        int m = Integer.parseInt(stringTokenizer.nextToken());
        boolean[][] isComparable = new boolean[n][n];
        for (int i = 0; i < m; i++) {
            stringTokenizer = new StringTokenizer(bufferedReader.readLine());
            isComparable[Integer.parseInt(stringTokenizer.nextToken()) - 1][Integer.parseInt(stringTokenizer.nextToken()) - 1] = true;
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
                    if (isComparable[start][pass] && isComparable[pass][end]) {
                        isComparable[start][end] = true;
                    }
                }
            }
        }

        int comparableStudents = 0;
        for (int i = 0; i < n; i++) {
            boolean canComparable = true;
            for (int j = 0; j < n; j++) {
                if (i != j && !isComparable[i][j] && !isComparable[j][i]) {
                    canComparable = false;
                    break;
                }
            }
            if (canComparable) {
                comparableStudents++;
            }
        }
        System.out.println(comparableStudents);
    }
}
