package Baekjoon;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class BOJ_1719 {

    private static final int MAX_VALUE = 100000000;

    public static void main(String[] args) throws IOException {
        BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer stringTokenizer = new StringTokenizer(bufferedReader.readLine());
        int n = Integer.parseInt(stringTokenizer.nextToken());
        int m = Integer.parseInt(stringTokenizer.nextToken());
        int[][] map = new int[n][n];
        int[][] paths = new int[n][n];
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                map[i][j] = MAX_VALUE;
                paths[i][j] = j;
            }
        }
        for (int i = 0; i < m; i++) {
            stringTokenizer = new StringTokenizer(bufferedReader.readLine());
            int from = Integer.parseInt(stringTokenizer.nextToken()) - 1;
            int to = Integer.parseInt(stringTokenizer.nextToken()) - 1;
            int time = Integer.parseInt(stringTokenizer.nextToken());
            map[from][to] = time;
            map[to][from] = time;
        }
        for (int pass = 0; pass < n; pass++) {
            for (int start = 0; start < n; start++) {
                for (int end = 0; end < n; end++) {
                    if (map[start][end] > map[start][pass] + map[pass][end]) {
                        map[start][end] = map[start][pass] + map[pass][end];
                        paths[start][end] = paths[start][pass];
                    }
                }
            }
        }

        StringBuilder answer = new StringBuilder();
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                if (i == j) {
                    answer.append("- ");
                    continue;
                }
                answer.append(paths[i][j] + 1).append(" ");
            }
            answer.append("\n");
        }
        System.out.println(answer);
    }
}
