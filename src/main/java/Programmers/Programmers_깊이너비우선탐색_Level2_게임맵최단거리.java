package Programmers;

import java.util.Comparator;
import java.util.PriorityQueue;

public class Programmers_깊이너비우선탐색_Level2_게임맵최단거리 {

    public static void main(String[] args) {
        System.out.println(solution(new int[][]{{1, 0, 1, 1, 1}, {1, 0, 1, 0, 1}, {1, 0, 1, 1, 1,}, {1, 1, 1, 0, 1}, {0, 0, 0, 0, 1}}));
        System.out.println(solution(new int[][]{{1, 0, 1, 1, 1}, {1, 0, 1, 0, 1}, {1, 0, 1, 1, 1,}, {1, 1, 1, 0, 0}, {0, 0, 0, 0, 1}}));
    }

    private static final int[][] DELTAS = {{0, 1}, {1, 0}, {0, -1}, {-1, 0}};
    private static final int WALL = 0;
    private static final int MOVE = 2;
    private static final PriorityQueue<int[]> MOVES = new PriorityQueue<>(Comparator.comparing(info -> info[MOVE]));

    public static int solution(int[][] maps) {
        int n = maps.length;
        int m = maps[0].length;
        MOVES.clear();
        boolean[][] isVisited = new boolean[n][m];
        MOVES.offer(new int[]{0, 0, 1});
        while (!MOVES.isEmpty()) {
            int[] current = MOVES.poll();
            int x = current[0];
            int y = current[1];
            int move = current[MOVE];
            if (x == n - 1 && y == m - 1) {
                return move;
            }
            if (isVisited[x][y]) {
                continue;
            }
            isVisited[x][y] = true;
            for (int[] delta : DELTAS) {
                int dx = x + delta[0];
                int dy = y + delta[1];
                if (dx >= 0 && dx < n && dy >= 0 && dy < m && !isVisited[dx][dy] && maps[dx][dy] != WALL) {
                    MOVES.offer(new int[]{dx, dy, move + 1});
                }
            }
        }
        return -1;
    }
}
