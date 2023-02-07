package Baekjoon;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.Comparator;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

public class BOJ_6087 {

    private static final int[][] DELTAS = {{0, 1}, {1, 0}, {0, -1}, {-1, 0}};
    private static final PriorityQueue<int[]> LASER = new PriorityQueue<>(Comparator.comparing(info -> info[3]));
    private static final char C = 'C';
    private static final char WALL = '*';

    private static int width;
    private static int height;

    public static void main(String[] args) throws IOException {
        BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer stringTokenizer = new StringTokenizer(bufferedReader.readLine());
        width = Integer.parseInt(stringTokenizer.nextToken());
        height = Integer.parseInt(stringTokenizer.nextToken());
        char[][] map = new char[height][width];
        int[][] mirrors = new int[height][width];
        int[] initC = new int[]{-1, -1};
        for (int i = 0; i < height; i++) {
            Arrays.fill(mirrors[i], Integer.MAX_VALUE);
            String line = bufferedReader.readLine();
            for (int j = 0; j < width; j++) {
                char input = line.charAt(j);
                map[i][j] = input;
                if (input == C && initC[0] == -1) {
                    initC[0] = i;
                    initC[1] = j;
                    mirrors[i][j] = 0;
                }
            }
        }
        for (int direction = 0; direction < DELTAS.length; direction++) {
            int[] delta = DELTAS[direction];
            int dx = initC[0] + delta[0];
            int dy = initC[1] + delta[1];
            if (isInBoundary(dx, dy) && map[dx][dy] != WALL) {
                LASER.offer(new int[]{dx, dy, direction, 0});
                mirrors[dx][dy] = 0;
            }
        }
        System.out.println(getMinMirrorCount(map, mirrors));
    }

    private static int getMinMirrorCount(char[][] map, int[][] mirrors) {
        while (!LASER.isEmpty()) {
            int[] current = LASER.poll();
            int x = current[0];
            int y = current[1];
            int mirror = current[3];
            if (map[x][y] == C) {
                return mirror;
            }
            int direction = current[2];
            for (int nextDirection = 0; nextDirection < DELTAS.length; nextDirection++) {
                int dx = x + DELTAS[nextDirection][0];
                int dy = y + DELTAS[nextDirection][1];
                if (isInBoundary(dx, dy) && map[dx][dy] != WALL) {
                    if (direction == nextDirection) {
                        mirrors[dx][dy] = mirror;
                        LASER.offer(new int[]{dx, dy, nextDirection, mirror});
                    } else {
                        if (mirrors[dx][dy] > mirror + 1) {
                            mirrors[dx][dy] = mirror + 1;
                            LASER.offer(new int[]{dx, dy, nextDirection, mirror + 1});
                        }
                    }
                }
            }
        }
        return 0;
    }

    private static boolean isInBoundary(int x, int y) {
        return x >= 0 && x < height && y >= 0 && y < width;
    }
}
