package Baekjoon;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class BOJ_11660 {

    public static void main(String[] args) throws IOException {
        BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer stringTokenizer = new StringTokenizer(bufferedReader.readLine());
        int n = Integer.parseInt(stringTokenizer.nextToken());
        int m = Integer.parseInt(stringTokenizer.nextToken());
        int[][] sums = new int[n + 1][n + 1];
        for (int i = 1; i <= n; i++) {
            stringTokenizer = new StringTokenizer(bufferedReader.readLine());
            for (int j = 1; j <= n; j++) {
                sums[i][j] = Integer.parseInt(stringTokenizer.nextToken()) + sums[i - 1][j] + sums[i][j - 1] - sums[i - 1][j - 1];
            }
        }
        StringBuilder answer = new StringBuilder();
        for (int i = 0; i < m; i++) {
            stringTokenizer = new StringTokenizer(bufferedReader.readLine());
            int x1 = Integer.parseInt(stringTokenizer.nextToken()) - 1;
            int y1 = Integer.parseInt(stringTokenizer.nextToken()) - 1;
            int x2 = Integer.parseInt(stringTokenizer.nextToken());
            int y2 = Integer.parseInt(stringTokenizer.nextToken());
            answer.append(sums[x2][y2] - sums[x2][y1] - sums[x1][y2] + sums[x1][y1]).append("\n");
        }
        System.out.println(answer);
    }
}
