package Baekjoon;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Comparator;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

public class BOJ_1261 {

    private static final int[][] DELTAS = {{0, 1}, {1, 0}, {0, -1}, {-1, 0}};
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
        PriorityQueue<int[]> priorityQueue = new PriorityQueue<>(Comparator.comparing(location -> location[BREAKING_COUNT]));
        priorityQueue.offer(new int[]{0, 0, 0});
        isVisited[0][0] = true;
        while (!priorityQueue.isEmpty()) {
            int[] current = priorityQueue.poll();
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
                    isVisited[dx][dy] = true;
                    priorityQueue.offer(new int[]{dx, dy, map[dx][dy] + count});
                }
            }
        }
    }
}
