package Baekjoon;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class BOJ_14938 {

    private static final int MAX_VALUE = 15000;

    public static void main(String[] args) throws IOException {
        BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer stringTokenizer = new StringTokenizer(bufferedReader.readLine());
        int n = Integer.parseInt(stringTokenizer.nextToken());
        int m = Integer.parseInt(stringTokenizer.nextToken());
        int r = Integer.parseInt(stringTokenizer.nextToken());
        int[] items = new int[n];
        stringTokenizer = new StringTokenizer(bufferedReader.readLine());
        for (int i = 0; i < n; i++) {
            items[i] = Integer.parseInt(stringTokenizer.nextToken());
        }
        int[][] map = new int[n][n];
        for (int i = 0; i < n; i++) {
            Arrays.fill(map[i], MAX_VALUE);
            map[i][i] = 0;
        }
        while (r-- > 0) {
            stringTokenizer = new StringTokenizer(bufferedReader.readLine());
            int from = Integer.parseInt(stringTokenizer.nextToken()) - 1;
            int to = Integer.parseInt(stringTokenizer.nextToken()) - 1;
            int distance = Integer.parseInt(stringTokenizer.nextToken());
            map[from][to] = distance;
            map[to][from] = distance;
        }
        for (int pass = 0; pass < n; pass++) {
            for (int start = 0; start < n; start++) {
                for (int end = 0; end < n; end++) {
                    map[start][end] = Math.min(map[start][end], map[start][pass] + map[pass][end]);
                }
            }
        }
        int maxItem = 0;
        for (int i = 0; i < n; i++) {
            int item = 0;
            for (int j = 0; j < n; j++) {
                if (map[i][j] <= m) {
                    item += items[j];
                }
            }
            maxItem = Math.max(maxItem, item);
        }
        System.out.println(maxItem);
    }
}
