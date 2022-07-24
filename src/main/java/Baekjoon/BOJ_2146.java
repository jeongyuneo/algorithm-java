package Baekjoon;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class BOJ_2146 {

    private static final int[][] DELTAS = {{0, 1}, {1, 0}, {0, -1}, {-1, 0}};
    private static final String LAND = "1";
    private static final int EMPTY = 0;

    private static int minBridge;

    public static void main(String[] args) throws IOException {
        BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(System.in));
        int n = Integer.parseInt(bufferedReader.readLine());
        boolean[][] isLand = new boolean[n][n];
        for (int i = 0; i < n; i++) {
            StringTokenizer stringTokenizer = new StringTokenizer(bufferedReader.readLine());
            for (int j = 0; j < n; j++) {
                if (stringTokenizer.nextToken().equals(LAND)) {
                    isLand[i][j] = true;
                }
            }
        }
        Queue<int[]> queue = new LinkedList<>();
        int[][] land = getLand(isLand, queue, n);
        findMinBridge(land, queue, n);
        System.out.println(minBridge);
    }

    private static void findMinBridge(int[][] land, Queue<int[]> queue, int n) {
        minBridge = n * n;
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                if (land[i][j] != EMPTY) {
                    buildBridge(land, queue, n, i, j);
                }
            }
        }
    }

    private static void buildBridge(int[][] land, Queue<int[]> queue, int n, int i, int j) {
        boolean[][] isSelected = new boolean[n][n];
        isSelected[i][j] = true;
        int fromLand = land[i][j];
        int bridge = 0;
        queue.offer(new int[]{i, j});
        while (!queue.isEmpty()) {
            int size = queue.size();
            while (size-- > 0) {
                int[] current = queue.poll();
                int x = current[0];
                int y = current[1];
                for (int[] delta : DELTAS) {
                    int dx = x + delta[0];
                    int dy = y + delta[1];
                    if (dx >= 0 && dx < n && dy >= 0 && dy < n && !isSelected[dx][dy] && land[dx][dy] != fromLand) {
                        isSelected[dx][dy] = true;
                        if (land[dx][dy] == EMPTY) {
                            queue.offer(new int[]{dx, dy});
                        } else if (land[dx][dy] != fromLand) {
                            queue.clear();
                            minBridge = Math.min(minBridge, bridge);
                            return;
                        }
                    }
                }
            }
            bridge++;
        }
    }

    private static int[][] getLand(boolean[][] isLand, Queue<int[]> queue, int n) {
        int landNumber = 1;
        int[][] land = new int[n][n];
        boolean[][] isVisited = new boolean[n][n];
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                if (isLand[i][j] && !isVisited[i][j]) {
                    isVisited[i][j] = true;
                    land[i][j] = landNumber;
                    queue.offer(new int[]{i, j});
                    while (!queue.isEmpty()) {
                        int[] current = queue.poll();
                        int x = current[0];
                        int y = current[1];
                        for (int[] delta : DELTAS) {
                            int dx = x + delta[0];
                            int dy = y + delta[1];
                            while (dx >= 0 && dx < n && dy >= 0 && dy < n && !isVisited[dx][dy] && isLand[dx][dy]) {
                                isVisited[dx][dy] = true;
                                land[dx][dy] = landNumber;
                                queue.offer(new int[]{dx, dy});
                            }
                        }
                    }
                    landNumber++;
                }
            }
        }
        return land;
    }
}
