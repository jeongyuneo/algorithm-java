package Baekjoon;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.Queue;
import java.util.StringTokenizer;

public class BOJ_14442 {

    private static final int[][] DELTAS = {{0, 1}, {1, 0}, {0, -1}, {-1, 0}};
    private static final int HEIGHT = 0;
    private static final int WIDTH = 1;
    private static final int DISTANCE = 2;
    private static final int BROKEN_WALL_COUNT = 3;
    private static final int WALL = 1;

    public static void main(String[] args) throws IOException {
        BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer stringTokenizer = new StringTokenizer(bufferedReader.readLine());
        int n = Integer.parseInt(stringTokenizer.nextToken());
        int m = Integer.parseInt(stringTokenizer.nextToken());
        int k = Integer.parseInt(stringTokenizer.nextToken());
        int[][] map = new int[n][m];
        for (int i = 0; i < n; i++) {
            String line = bufferedReader.readLine();
            for (int j = 0; j < m; j++) {
                map[i][j] = line.charAt(j) - 48;
            }
        }
        System.out.println(getMinDistance(map, n, m, k));
    }

    private static int getMinDistance(int[][] map, int n, int m, int k) {
        Queue<int[]> queue = new ArrayDeque<>();
        queue.offer(new int[]{0, 0, 1, 0});
        boolean[][][] isVisited = new boolean[k + 1][n][m];
        isVisited[0][0][0] = true;
        while (!queue.isEmpty()) {
            int[] current = queue.poll();
            int x = current[HEIGHT];
            int y = current[WIDTH];
            int distance = current[DISTANCE];
            if (x == n - 1 && y == m - 1) {
                return distance;
            }
            int brokenWallCount = current[BROKEN_WALL_COUNT];
            for (int[] delta : DELTAS) {
                int dx = x + delta[HEIGHT];
                int dy = y + delta[WIDTH];
                if (dx >= 0 && dx < n && dy >= 0 && dy < m) {
                    if (map[dx][dy] == WALL) {
                        if (brokenWallCount < k && !isVisited[brokenWallCount + 1][dx][dy]) {
                            isVisited[brokenWallCount + 1][dx][dy] = true;
                            queue.offer(new int[]{dx, dy, distance + 1, brokenWallCount + 1});
                        }
                    } else {
                        if (brokenWallCount <= k && !isVisited[brokenWallCount][dx][dy]) {
                            isVisited[brokenWallCount][dx][dy] = true;
                            queue.offer(new int[]{dx, dy, distance + 1, brokenWallCount});
                        }
                    }
                }
            }
        }
        return -1;
    }
}
