package Baekjoon;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.Queue;
import java.util.StringTokenizer;

public class BOJ_1600 {

    private static final int[][] DELTAS_HORSE = {{-1, -2}, {-2, -1}, {1, -2}, {2, -1}, {2, 1}, {1, 2}, {-1, 2}, {-2, 1}};
    private static final int[][] DELTAS_MONKEY = {{0, 1}, {1, 0}, {0, -1}, {-1, 0}};
    private static final int LAND = 0;
    private static final int HEIGHT = 0;
    private static final int WIDTH = 1;
    private static final int MOVE = 2;
    private static final int JUMP = 3;

    public static void main(String[] args) throws IOException {
        BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(System.in));
        int k = Integer.parseInt(bufferedReader.readLine());
        StringTokenizer stringTokenizer = new StringTokenizer(bufferedReader.readLine());
        int width = Integer.parseInt(stringTokenizer.nextToken());
        int height = Integer.parseInt(stringTokenizer.nextToken());
        int[][] map = new int[height][width];
        for (int i = 0; i < height; i++) {
            stringTokenizer = new StringTokenizer(bufferedReader.readLine());
            for (int j = 0; j < width; j++) {
                map[i][j] = Integer.parseInt(stringTokenizer.nextToken());
            }
        }
        System.out.println(getMinMove(map, height, width, k));
    }

    private static int getMinMove(int[][] map, int height, int width, int k) {
        Queue<int[]> queue = new ArrayDeque<>();
        queue.offer(new int[]{0, 0, 0, 0});
        boolean[][][] isVisited = new boolean[k + 1][height][width];
        isVisited[0][0][0] = true;
        while (!queue.isEmpty()) {
            int[] currnet = queue.poll();
            int x = currnet[HEIGHT];
            int y = currnet[WIDTH];
            int move = currnet[MOVE];
            if (x == height - 1 && y == width - 1) {
                return move;
            }
            int jump = currnet[JUMP];
            for (int[] delta : DELTAS_HORSE) {
                int dx = x + delta[HEIGHT];
                int dy = y + delta[WIDTH];
                if (dx >= 0 && dx < height && dy >= 0 && dy < width && map[dx][dy] == LAND && jump < k && !isVisited[jump + 1][dx][dy]) {
                    isVisited[jump + 1][dx][dy] = true;
                    queue.offer(new int[]{dx, dy, move + 1, jump + 1});
                }
            }
            for (int[] delta : DELTAS_MONKEY) {
                int dx = x + delta[HEIGHT];
                int dy = y + delta[WIDTH];
                if (dx >= 0 && dx < height && dy >= 0 && dy < width && map[dx][dy] == LAND && jump <= k && !isVisited[jump][dx][dy]) {
                    isVisited[jump][dx][dy] = true;
                    queue.offer(new int[]{dx, dy, move + 1, jump});
                }
            }
        }
        return -1;
    }
}
