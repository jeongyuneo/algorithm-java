package Baekjoon;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.Comparator;
import java.util.PriorityQueue;

public class BOJ_2151 {

    private static final int[][] DELTAS = {{0, 1}, {1, 0}, {0, -1}, {-1, 0}};
    private static final char WALL = '*';
    private static final char DOOR = '#';
    private static final char PLACE_TO_SET_MIRROR = '!';
    private static final int DIRECTION = 2;
    private static final int MIRROR = 3;
    private static final PriorityQueue<int[]> MOVES = new PriorityQueue<>(Comparator.comparing(move -> move[MIRROR]));

    public static void main(String[] args) throws IOException {
        BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(System.in));
        int n = Integer.parseInt(bufferedReader.readLine());
        char[][] house = new char[n][n];
        int[][][] mirrors = new int[n][n][4];
        for (int i = 0; i < n; i++) {
            String line = bufferedReader.readLine();
            for (int j = 0; j < n; j++) {
                Arrays.fill(mirrors[i][j], Integer.MAX_VALUE);
                char input = line.charAt(j);
                house[i][j] = input;
                if (input == DOOR && MOVES.isEmpty()) {
                    for (int direction = 0; direction < 4; direction++) {
                        MOVES.offer(new int[]{i, j, direction, 0});
                        mirrors[i][j][direction] = 0;
                    }
                    house[i][j] = '^';
                }
            }
        }
        System.out.println(getMirror(house, mirrors, n));
    }

    private static int getMirror(char[][] house, int[][][] mirrors, int n) {
        int min = Integer.MAX_VALUE;
        while (!MOVES.isEmpty()) {
            int[] current = MOVES.poll();
            int x = current[0];
            int y = current[1];
            int direction = current[DIRECTION];
            int mirror = current[MIRROR];
            if (mirrors[x][y][direction] < mirror) {
                continue;
            }
            mirrors[x][y][direction] = mirror;
            if (house[x][y] == DOOR) {
                min = Math.min(min, mirror);
                continue;
            }
            if (house[x][y] == PLACE_TO_SET_MIRROR) {
                MOVES.offer(new int[]{x, y, (direction + 1) % 4, mirror + 1});
                MOVES.offer(new int[]{x, y, (direction + 3) % 4, mirror + 1});
            }
            int[] delta = DELTAS[direction];
            int dx = x + delta[0];
            int dy = y + delta[1];
            if (dx >= 0 && dx < n && dy >= 0 && dy < n && house[dx][dy] != WALL) {
                MOVES.offer(new int[]{dx, dy, direction, mirror});
            }
        }
        return min;
    }
}
