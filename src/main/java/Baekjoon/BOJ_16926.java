package Baekjoon;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class BOJ_16926 {

    public static void main(String[] args) throws IOException {
        BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer stringTokenizer = new StringTokenizer(bufferedReader.readLine());
        int n = Integer.parseInt(stringTokenizer.nextToken());
        int m = Integer.parseInt(stringTokenizer.nextToken());
        int r = Integer.parseInt(stringTokenizer.nextToken());
        int[][] map = new int[n][m];
        for (int i = 0; i < n; i++) {
            stringTokenizer = new StringTokenizer(bufferedReader.readLine());
            for (int j = 0; j < m; j++) {
                map[i][j] = Integer.parseInt(stringTokenizer.nextToken());
            }
        }

        for (int rotate = 0; rotate < r; rotate++) {
            rotate(n, m, map);
        }
        print(map);
    }

    private static void print(int[][] map) {
        StringBuilder answer = new StringBuilder();
        for (int[] line : map) {
            for (int field : line) {
                answer.append(field)
                        .append(" ");
            }
            answer.append("\n");
        }
        System.out.println(answer);
    }

    private static void rotate(int n, int m, int[][] map) {
        int rotationEnd = Math.min(n, m) / 2;
        int x = 0;
        int y = 0;
        while (rotationEnd-- > 0) {
            int init = map[x][y];
            for (int i = 1; i < m; i++) {
                map[x][y] = map[x][++y];
            }
            for (int i = 1; i < n; i++) {
                map[x][y] = map[++x][y];
            }
            for (int i = 1; i < m; i++) {
                map[x][y] = map[x][--y];
            }
            for (int i = 2; i < n; i++) {
                map[x][y] = map[--x][y];
            }
            map[x][y] = init;
            y++;
            n -= 2;
            m -= 2;
        }
    }
}
