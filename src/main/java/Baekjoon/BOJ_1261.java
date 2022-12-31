package Baekjoon;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.Queue;
import java.util.StringTokenizer;

public class BOJ_1261 {

    private static final int[][] DELTAS = {{0, 1}, {1, 0}, {0, -1}, {-1, 0}};
    private static final int WALL = 1;

    public static void main(String[] args) throws IOException {
        BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer stringTokenizer = new StringTokenizer(bufferedReader.readLine());
        int m = Integer.parseInt(stringTokenizer.nextToken());
        int n = Integer.parseInt(stringTokenizer.nextToken());
        int[][] map = new int[n][m];
        int[][] breakingCounts = new int[n][m];
        for (int i = 0; i < n; i++) {
            String readLine = bufferedReader.readLine();
            for (int j = 0; j < m; j++) {
                map[i][j] = readLine.charAt(j) - 48;
                breakingCounts[i][j] = Integer.MAX_VALUE;
            }
        }
        breakingCounts[0][0] = 0;
        Queue<int[]> queue = new ArrayDeque<>();
        queue.offer(new int[]{0, 0});
        while (!queue.isEmpty()) {
            int[] current = queue.poll();
            int x = current[0];
            int y = current[1];
            for (int[] delta : DELTAS) {
                int dx = x + delta[0];
                int dy = y + delta[1];
                if (dx >= 0 && dx < n && dy >= 0 && dy < m) {
                    if (map[dx][dy] == WALL) {
                        if (breakingCounts[dx][dy] > breakingCounts[x][y] + 1) {
                            breakingCounts[dx][dy] = breakingCounts[x][y] + 1;
                            queue.offer(new int[]{dx, dy});
                        }
                    } else {
                        if (breakingCounts[dx][dy] > breakingCounts[x][y]) {
                            breakingCounts[dx][dy] = breakingCounts[x][y];
                            queue.offer(new int[]{dx, dy});
                        }
                    }
                }
            }
        }
        System.out.println(breakingCounts[n - 1][m - 1]);
    }
}
