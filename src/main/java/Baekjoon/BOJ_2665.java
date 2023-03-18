package Baekjoon;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Comparator;
import java.util.PriorityQueue;

public class BOJ_2665 {

    private static final int[][] DELTAS = {{0, 1}, {1, 0}, {0, -1}, {-1, 0}};
    private static final int CHANGE_COUNT = 2;

    public static void main(String[] args) throws IOException {
        BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(System.in));
        int n = Integer.parseInt(bufferedReader.readLine());
        int[][] rooms = new int[n][n];
        for (int i = 0; i < n; i++) {
            String line = bufferedReader.readLine();
            for (int j = 0; j < n; j++) {
                rooms[i][j] = line.charAt(j) - '0';
            }
        }
        System.out.println(getMinimumChangeCount(rooms, n));
    }

    private static int getMinimumChangeCount(int[][] rooms, int n) {
        PriorityQueue<int[]> moves = new PriorityQueue<>(Comparator.comparing(move -> move[CHANGE_COUNT]));
        moves.offer(new int[]{0, 0, 0});
        boolean[][] isVisited = new boolean[n][n];
        isVisited[0][0] = true;
        while (!moves.isEmpty()) {
            int[] current = moves.poll();
            int x = current[0];
            int y = current[1];
            int changeCount = current[CHANGE_COUNT];
            if (x == n - 1 && y == n - 1) {
                return changeCount;
            }
            for (int[] delta : DELTAS) {
                int dx = x + delta[0];
                int dy = y + delta[1];
                if (dx >= 0 && dx < n && dy >= 0 && dy < n && !isVisited[dx][dy]) {
                    isVisited[dx][dy] = true;
                    moves.offer(new int[]{dx, dy, changeCount + (rooms[dx][dy] + 1) % 2});
                }
            }
        }
        return 0;
    }
}
