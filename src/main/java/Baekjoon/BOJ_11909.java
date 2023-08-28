package Baekjoon;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.Comparator;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

public class BOJ_11909 {

    private static final int[][] DELTAS = {{0, 1}, {1, 0}};
    private static final int COST = 2;

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
        PriorityQueue<int[]> moves = new PriorityQueue<>(Comparator.comparing(move -> move[COST]));
        boolean[][] isVisited = new boolean[n][n];
        int[][] costs = new int[n][n];
        for (int i = 0; i < n; i++) {
            Arrays.fill(costs[i], Integer.MAX_VALUE);
        }
        moves.offer(new int[]{0, 0, 0});
        costs[0][0] = 0;
        while (!moves.isEmpty()) {
            int[] current = moves.poll();
            int x = current[0];
            int y = current[1];
            int cost = current[COST];
            if (x == n - 1 && y == n - 1) {
                break;
            }
            if (isVisited[x][y]) {
                continue;
            }
            isVisited[x][y] = true;
            for (int[] delta : DELTAS) {
                int dx = x + delta[0];
                int dy = y + delta[1];
                if (dx >= 0 && dx < n && dy >= 0 && dy < n) {
                    if (map[x][y] > map[dx][dy]) {
                        if (costs[dx][dy] > cost) {
                            costs[dx][dy] = cost;
                            moves.offer(new int[]{dx, dy, cost});
                        }
                    } else {
                        if (costs[dx][dy] > cost + map[dx][dy] - map[x][y] + 1) {
                            costs[dx][dy] = cost + map[dx][dy] - map[x][y] + 1;
                            moves.offer(new int[]{dx, dy, cost + map[dx][dy] - map[x][y] + 1});
                        }
                    }
                }
            }
        }
        System.out.println(costs[n - 1][n - 1]);
    }
}
