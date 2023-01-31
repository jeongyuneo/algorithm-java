package Baekjoon;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.Arrays;
import java.util.Queue;
import java.util.StringTokenizer;

public class BOJ_2589 {

    private static final int[][] DELTAS = {{0, 1}, {1, 0}, {0, -1}, {-1, 0}};
    private static final char LAND = 'L';

    public static void main(String[] args) throws IOException {
        BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer stringTokenizer = new StringTokenizer(bufferedReader.readLine());
        int height = Integer.parseInt(stringTokenizer.nextToken());
        int width = Integer.parseInt(stringTokenizer.nextToken());
        char[][] map = new char[height][width];
        for (int i = 0; i < height; i++) {
            String line = bufferedReader.readLine();
            for (int j = 0; j < width; j++) {
                map[i][j] = line.charAt(j);
            }
        }
        boolean[][] isVisited = new boolean[height][width];
        Queue<int[]> queue = new ArrayDeque<>();
        int answer = 0;
        for (int i = 0; i < height; i++) {
            for (int j = 0; j < width; j++) {
                if (map[i][j] == LAND) {
                    int distance = -1;
                    for (int k = 0; k < height; k++) {
                        Arrays.fill(isVisited[k], false);
                    }
                    isVisited[i][j] = true;
                    queue.offer(new int[]{i, j});
                    while (!queue.isEmpty()) {
                        distance++;
                        int size = queue.size();
                        while (size-- > 0) {
                            int[] current = queue.poll();
                            int x = current[0];
                            int y = current[1];
                            for (int[] delta : DELTAS) {
                                int dx = x + delta[0];
                                int dy = y + delta[1];
                                if (dx >= 0 && dx < height && dy >= 0 && dy < width && !isVisited[dx][dy] && map[dx][dy] == LAND) {
                                    isVisited[dx][dy] = true;
                                    queue.offer(new int[]{dx, dy});
                                }
                            }
                        }
                    }
                    answer = Math.max(answer, distance);
                }
            }
        }
        System.out.println(answer);
    }
}
