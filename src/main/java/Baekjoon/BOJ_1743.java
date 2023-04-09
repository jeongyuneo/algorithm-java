package Baekjoon;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.Queue;
import java.util.StringTokenizer;

public class BOJ_1743 {

    private static final int[][] DELTAS = {{0, 1}, {1, 0}, {0, -1}, {-1, 0}};
    private static final int FOOD = 1;

    public static void main(String[] args) throws IOException {
        BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer stringTokenizer = new StringTokenizer(bufferedReader.readLine());
        int n = Integer.parseInt(stringTokenizer.nextToken());
        int m = Integer.parseInt(stringTokenizer.nextToken());
        int k = Integer.parseInt(stringTokenizer.nextToken());
        int[][] way = new int[n][m];
        for (int i = 0; i < k; i++) {
            stringTokenizer = new StringTokenizer(bufferedReader.readLine());
            way[Integer.parseInt(stringTokenizer.nextToken()) - 1][Integer.parseInt(stringTokenizer.nextToken()) - 1] = FOOD;
        }
        int biggestSize = 0;
        Queue<int[]> foods = new ArrayDeque<>();
        boolean[][] isVisited = new boolean[n][m];
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < m; j++) {
                if (way[i][j] == FOOD && !isVisited[i][j]) {
                    foods.offer(new int[]{i, j});
                    isVisited[i][j] = true;
                    int size = 1;
                    while (!foods.isEmpty()) {
                        int[] current = foods.poll();
                        int x = current[0];
                        int y = current[1];
                        for (int[] delta : DELTAS) {
                            int dx = x + delta[0];
                            int dy = y + delta[1];
                            if (dx >= 0 && dx < n && dy >= 0 && dy < m && !isVisited[dx][dy] && way[dx][dy] == FOOD) {
                                foods.offer(new int[]{dx, dy});
                                isVisited[dx][dy] = true;
                                size++;
                            }
                        }
                    }
                    biggestSize = Math.max(biggestSize, size);
                }
            }
        }
        System.out.println(biggestSize);
    }
}
