package Baekjoon;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;

public class BOJ_10026 {

    private static final int[][] DELTAS = {{-1, 0}, {1, 0}, {0, -1}, {0, 1}};   // 상하좌우
    private static final int X = 0;
    private static final int Y = 1;
    private static final int RED_AND_GREEN = 153;

    private static boolean[][] visited;
    private static int n;
    private static int generalDistrict;
    private static int redGreenBlindDistrict;

    public static void main(String[] args) throws IOException {
        BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(System.in));
        n = Integer.parseInt(bufferedReader.readLine());
        char[][] grid = new char[n][n];
        for (int i = 0; i < n; i++) {
            String input = bufferedReader.readLine();
            for (int j = 0; j < n; j++) {
                grid[i][j] = input.charAt(j);
            }
        }

        visited = new boolean[n][n];
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                if (!visited[i][j]) {
                    searchGeneralDistrict(grid, i, j);
                }
            }
        }

        visited = new boolean[n][n];
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                if (!visited[i][j]) {
                    searchRedGreenBlindDistrict(grid, i, j);
                }
            }
        }
        System.out.println(generalDistrict + " " + redGreenBlindDistrict);
    }

    private static void searchRedGreenBlindDistrict(char[][] grid, int x, int y) {
        redGreenBlindDistrict++;
        Queue<int[]> queue = new LinkedList<>();
        queue.offer(new int[]{x, y});
        visited[x][y] = true;
        char color = grid[x][y];
        while (!queue.isEmpty()) {
            int[] current = queue.poll();
            for (int[] delta : DELTAS) {
                int dx = current[X] + delta[X];
                int dy = current[Y] + delta[Y];
                if (dx >= 0 && dx < n && dy >= 0 && dy < n && !visited[dx][dy] && (grid[dx][dy] == color || grid[dx][dy] + color == RED_AND_GREEN)) {
                    queue.offer(new int[]{dx, dy});
                    visited[dx][dy] = true;
                }
            }
        }
    }

    private static void searchGeneralDistrict(char[][] grid, int x, int y) {
        generalDistrict++;
        Queue<int[]> queue = new LinkedList<>();
        queue.offer(new int[]{x, y});
        visited[x][y] = true;
        char color = grid[x][y];
        while (!queue.isEmpty()) {
            int[] current = queue.poll();
            for (int[] delta : DELTAS) {
                int dx = current[X] + delta[X];
                int dy = current[Y] + delta[Y];
                if (dx >= 0 && dx < n && dy >= 0 && dy < n && !visited[dx][dy] && grid[dx][dy] == color) {
                    queue.offer(new int[]{dx, dy});
                    visited[dx][dy] = true;
                }
            }
        }
    }
}
