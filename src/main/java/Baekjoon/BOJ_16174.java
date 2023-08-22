package Baekjoon;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.Queue;
import java.util.StringTokenizer;

public class BOJ_16174 {

    private static final int[][] DELTAS = {{0, 1}, {1, 0}};
    private static final String ARRIVE = "HaruHaru";
    private static final String CANNOT_ARRIVE = "Hing";

    public static void main(String[] args) throws IOException {
        BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(System.in));
        int n = Integer.parseInt(bufferedReader.readLine());
        int[][] map = new int[n][n];
        for (int i = 0; i < n; i++) {
            StringTokenizer stringTokenizer = new StringTokenizer(bufferedReader.readLine());
            for (int j = 0; j < n; j++) {
                map[i][j] = Integer.parseInt(stringTokenizer.nextToken());
            }
        }
        System.out.println(getResult(n, map));
    }

    private static String getResult(int n, int[][] map) {
        Queue<int[]> jjelly = new ArrayDeque<>();
        jjelly.offer(new int[]{0, 0});
        boolean[][] isVisited = new boolean[n][n];
        isVisited[0][0] = true;
        while (!jjelly.isEmpty()) {
            int[] current = jjelly.poll();
            int x = current[0];
            int y = current[1];
            if (map[x][y] == -1) {
                return ARRIVE;
            }
            for (int[] delta : DELTAS) {
                int dx = x + delta[0] * map[x][y];
                int dy = y + delta[1] * map[x][y];
                if (dx >= 0 && dx < n && dy >= 0 && dy < n && !isVisited[dx][dy]) {
                    isVisited[dx][dy] = true;
                    jjelly.offer(new int[]{dx, dy});
                }
            }
        }
        return CANNOT_ARRIVE;
    }
}
