package Baekjoon;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.Deque;
import java.util.StringTokenizer;

public class BOJ_1261 {

    private static final int[][] DELTAS = {{0, 1}, {1, 0}, {0, -1}, {-1, 0}};
    private static final int WALL = 1;
    private static final int BREAKING_COUNT = 2;

    public static void main(String[] args) throws IOException {
        BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer stringTokenizer = new StringTokenizer(bufferedReader.readLine());
        int m = Integer.parseInt(stringTokenizer.nextToken());
        int n = Integer.parseInt(stringTokenizer.nextToken());
        int[][] map = new int[n][m];
        boolean[][] isVisited = new boolean[n][m];
        for (int i = 0; i < n; i++) {
            String readLine = bufferedReader.readLine();
            for (int j = 0; j < m; j++) {
                map[i][j] = readLine.charAt(j) - 48;
            }
        }
        Deque<int[]> deque = new ArrayDeque<>();
        deque.offer(new int[]{0, 0, 0});
        isVisited[0][0] = true;
        while (!deque.isEmpty()) {
            int[] current = deque.poll();
            int x = current[0];
            int y = current[1];
            int count = current[BREAKING_COUNT];
            if (x == n - 1 && y == m - 1) {
                System.out.println(count);
                break;
            }
            for (int[] delta : DELTAS) {
                int dx = x + delta[0];
                int dy = y + delta[1];
                if (dx >= 0 && dx < n && dy >= 0 && dy < m && !isVisited[dx][dy]) {
                    if (map[dx][dy] == WALL) {
                        deque.offer(new int[]{dx, dy, count + 1});
                    } else {
                        deque.offerFirst(new int[]{dx, dy, count});
                    }
                    isVisited[dx][dy] = true;
                }
            }
        }
    }
}
