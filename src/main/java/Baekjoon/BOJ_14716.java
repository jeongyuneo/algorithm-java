package Baekjoon;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.Queue;
import java.util.StringTokenizer;

public class BOJ_14716 {

    private static final int[][] DELTAS = {{0, 1}, {1, 1}, {1, 0}, {1, -1}, {0, -1}, {-1, -1}, {-1, 0}, {-1, 1}};
    private static final int BANNER = 1;

    public static void main(String[] args) throws IOException {
        BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer stringTokenizer = new StringTokenizer(bufferedReader.readLine());
        int m = Integer.parseInt(stringTokenizer.nextToken());
        int n = Integer.parseInt(stringTokenizer.nextToken());
        int[][] map = new int[m][n];
        for (int i = 0; i < m; i++) {
            stringTokenizer = new StringTokenizer(bufferedReader.readLine());
            for (int j = 0; j < n; j++) {
                map[i][j] = Integer.parseInt(stringTokenizer.nextToken());
            }
        }
        int totalLetter = 0;
        Queue<int[]> letter = new ArrayDeque<>();
        boolean[][] isVisited = new boolean[m][n];
        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                if (map[i][j] == BANNER && !isVisited[i][j]) {
                    totalLetter++;
                    letter.offer(new int[]{i, j});
                    isVisited[i][j] = true;
                    while (!letter.isEmpty()) {
                        int[] current = letter.poll();
                        int x = current[0];
                        int y = current[1];
                        for (int[] delta : DELTAS) {
                            int dx = x + delta[0];
                            int dy = y + delta[1];
                            if (dx >= 0 && dx < m && dy >= 0 && dy < n && map[dx][dy] == BANNER && !isVisited[dx][dy]) {
                                isVisited[dx][dy] = true;
                                letter.offer(new int[]{dx, dy});
                            }
                        }
                    }
                }
            }
        }
        System.out.println(totalLetter);
    }
}
