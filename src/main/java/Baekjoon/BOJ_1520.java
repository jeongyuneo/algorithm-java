package Baekjoon;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class BOJ_1520 {

    private static final int[][] DELTAS = {{0, 1}, {1, 0}, {0, -1}, {-1, 0}};

    private static int[][] map;
    private static int[][] path;
    private static int m;
    private static int n;

    public static void main(String[] args) throws IOException {
        BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer stringTokenizer = new StringTokenizer(bufferedReader.readLine());
        m = Integer.parseInt(stringTokenizer.nextToken());
        n = Integer.parseInt(stringTokenizer.nextToken());
        map = new int[m][n];
        path = new int[m][n];
        for (int i = 0; i < m; i++) {
            stringTokenizer = new StringTokenizer(bufferedReader.readLine());
            for (int j = 0; j < n; j++) {
                map[i][j] = Integer.parseInt(stringTokenizer.nextToken());
                path[i][j] = -1;
            }
        }

        int route = findRoute(0, 0);

        System.out.println(route);
    }

    private static int findRoute(int x, int y) {
        if (path[x][y] != -1) {
            return path[x][y];
        }

        if (x == m - 1 && y == n - 1) {
            return 1;
        }

        path[x][y] = 0;
        for (int[] delta : DELTAS) {
            int dx = x + delta[0];
            int dy = y + delta[1];
            if (dx >= 0 && dx < m && dy >= 0 && dy < n && map[x][y] > map[dx][dy]) {
                path[x][y] += findRoute(dx, dy);
            }
        }
        return path[x][y];
    }
}
