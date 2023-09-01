package Baekjoon;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class BOJ_17123 {

    public static void main(String[] args) throws IOException {
        BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(System.in));
        int t = Integer.parseInt(bufferedReader.readLine());
        StringBuilder result = new StringBuilder();
        while (t-- > 0) {
            StringTokenizer stringTokenizer = new StringTokenizer(bufferedReader.readLine());
            int n = Integer.parseInt(stringTokenizer.nextToken());
            int m = Integer.parseInt(stringTokenizer.nextToken());
            int[][] map = new int[n][n];
            for (int i = 0; i < n; i++) {
                stringTokenizer = new StringTokenizer(bufferedReader.readLine());
                for (int j = 0; j < n; j++) {
                    map[i][j] = Integer.parseInt(stringTokenizer.nextToken());
                }
            }
            int[] sums = new int[n * 2];
            for (int i = 0; i < n; i++) {
                for (int j = 0; j < n; j++) {
                    sums[i] += map[i][j];
                    sums[i + n] += map[j][i];
                }
            }
            while (m-- > 0) {
                stringTokenizer = new StringTokenizer(bufferedReader.readLine());
                int r1 = Integer.parseInt(stringTokenizer.nextToken()) - 1;
                int c1 = Integer.parseInt(stringTokenizer.nextToken()) - 1;
                int r2 = Integer.parseInt(stringTokenizer.nextToken()) - 1;
                int c2 = Integer.parseInt(stringTokenizer.nextToken()) - 1;
                int v = Integer.parseInt(stringTokenizer.nextToken());
                for (int i = r1; i <= r2; i++) {
                    sums[i] += v * (c2 - c1 + 1);
                }
                for (int i = c1; i <= c2; i++) {
                    sums[n + i] += v * (r2 - r1 + 1);
                }
            }
            for (int i = 0; i < sums.length; i++) {
                if (i == n) {
                    result.append("\n");
                }
                result.append(sums[i]).append(" ");
            }
            result.append("\n");
        }
        System.out.println(result);
    }
}
