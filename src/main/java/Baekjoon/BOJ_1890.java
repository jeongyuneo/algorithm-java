package Baekjoon;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class BOJ_1890 {

    private static final int[][] DELTAS = {{0, 1}, {1, 0}};

    private static int[][] map;
    private static long[][] path;
    private static int n;

    public static void main(String[] args) throws IOException {
        BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(System.in));
        n = Integer.parseInt(bufferedReader.readLine());
        map = new int[n][n];
        path = new long[n][n];
        for (int i = 0; i < n; i++) {
            StringTokenizer stringTokenizer = new StringTokenizer(bufferedReader.readLine());
            for (int j = 0; j < n; j++) {
                map[i][j] = Integer.parseInt(stringTokenizer.nextToken());
                path[i][j] = -1;
            }
        }
        long route = findRoute(0, 0);
        System.out.println(route);
    }

    private static long findRoute(int x, int y) {
        // 방문했던 곳이면 현재 경로 반환
        if (path[x][y] != -1) {
            return path[x][y];
        }

        // 가장 오른쪽 아래 칸에 도착하면 하나의 경로가 완성되었으므로 1 반환
        if (x == n - 1 && y == n - 1) {
            return 1;
        }

        // 방문하지 않았고, 도착지가 아니면 오른쪽과 아래로 이동
        path[x][y] = 0; // 현재 위치 방문 처리
        for (int[] delta : DELTAS) {
            int dx = x + (map[x][y] * delta[0]);
            int dy = y + (map[x][y] * delta[1]);
            if (dx < n && dy < n) {
                path[x][y] += findRoute(dx, dy);
            }
        }
        return path[x][y];
    }
}
