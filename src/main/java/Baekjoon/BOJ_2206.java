package Baekjoon;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.Queue;
import java.util.StringTokenizer;

public class BOJ_2206 {

    private static final int[][] DELTAS = {{0, 1}, {1, 0}, {0, -1}, {-1, 0}};
    private static final int EMPTY = 0;
    private static final int HAS_BROKEN = 1;

    public static void main(String[] args) throws IOException {
        BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer stringTokenizer = new StringTokenizer(bufferedReader.readLine());
        int n = Integer.parseInt(stringTokenizer.nextToken());
        int m = Integer.parseInt(stringTokenizer.nextToken());
        int[][] map = new int[n][m];
        for (int i = 0; i < n; i++) {
            String readLine = bufferedReader.readLine();
            for (int j = 0; j < m; j++) {
                map[i][j] = readLine.charAt(j) - 48;
            }
        }
        System.out.println(getPath(n, m, map));
    }

    private static int getPath(int n, int m, int[][] map) {
        boolean[][][] isVisited = new boolean[2][n][m];
        Queue<int[]> queue = new ArrayDeque<>();
        queue.offer(new int[]{0, 0, 0});
        isVisited[0][0][0] = true;
        int path = 0;
        while (!queue.isEmpty()) {
            path++;
            int size = queue.size();
            while (size-- > 0) {
                int[] current = queue.poll();
                int x = current[0];
                int y = current[1];
                int hasBroken = current[2];
                if (x == n - 1 && y == m - 1) {
                    return path;
                }
                for (int[] delta : DELTAS) {
                    int dx = x + delta[0];
                    int dy = y + delta[1];
                    if (dx >= 0 && dx < n && dy >= 0 && dy < m) {
                        if (map[dx][dy] == EMPTY) {
                            if (!isVisited[hasBroken][dx][dy]) {
                                isVisited[hasBroken][dx][dy] = true;
                                queue.offer(new int[]{dx, dy, hasBroken});
                            }
                        } else {
                            if (hasBroken != HAS_BROKEN && !isVisited[HAS_BROKEN][dx][dy]) {
                                isVisited[HAS_BROKEN][dx][dy] = true;
                                queue.offer(new int[]{dx, dy, HAS_BROKEN});
                            }
                        }
                    }
                }
            }
        }
        return -1;
    }
}
