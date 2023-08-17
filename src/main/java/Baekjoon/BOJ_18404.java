package Baekjoon;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.Queue;
import java.util.StringTokenizer;

public class BOJ_18404 {

    private static final int[][] DELTAS = {{-2, 1}, {-1, 2}, {1, 2}, {2, 1}, {2, -1}, {1, -2}, {-2, -1}, {-1, -2}};
    private static final int EMPTY = 0;

    public static void main(String[] args) throws IOException {
        BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer stringTokenizer = new StringTokenizer(bufferedReader.readLine());
        int n = Integer.parseInt(stringTokenizer.nextToken());
        int m = Integer.parseInt(stringTokenizer.nextToken());
        int[][] board = new int[n][n];
        stringTokenizer = new StringTokenizer(bufferedReader.readLine());
        int knightX = Integer.parseInt(stringTokenizer.nextToken()) - 1;
        int knightY = Integer.parseInt(stringTokenizer.nextToken()) - 1;
        int[] enemies = new int[m + 1];
        StringBuilder result = new StringBuilder();
        for (int enemy = 1; enemy <= m; enemy++) {
            stringTokenizer = new StringTokenizer(bufferedReader.readLine());
            int enemyX = Integer.parseInt(stringTokenizer.nextToken()) - 1;
            int enemyY = Integer.parseInt(stringTokenizer.nextToken()) - 1;
            board[enemyX][enemyY] = enemy;
        }
        move(board, enemies, n, knightX, knightY);
        for (int i = 1; i <= m; i++) {
            result.append(enemies[i]).append(" ");
        }
        System.out.println(result);
    }

    private static void move(int[][] board, int[] enemies, int n, int knightX, int knightY) {
        Queue<int[]> moves = new ArrayDeque<>();
        boolean[][] isVisited = new boolean[n][n];
        moves.offer(new int[]{knightX, knightY});
        isVisited[knightX][knightY] = true;
        int move = 0;
        while (!moves.isEmpty()) {
            int size = moves.size();
            while (size-- > 0) {
                int[] current = moves.poll();
                int x = current[0];
                int y = current[1];
                if (board[x][y] > EMPTY) {
                    enemies[board[x][y]] = move;
                }
                for (int[] delta : DELTAS) {
                    int dx = x + delta[0];
                    int dy = y + delta[1];
                    if (dx >= 0 && dx < n && dy >= 0 && dy < n && !isVisited[dx][dy]) {
                        isVisited[dx][dy] = true;
                        moves.offer(new int[]{dx, dy});
                    }
                }
            }
            move++;
        }
    }
}
