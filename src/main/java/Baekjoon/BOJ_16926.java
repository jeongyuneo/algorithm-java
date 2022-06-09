package Baekjoon;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class BOJ_16926 {

    private static final int[][] DELTAS = {{0, 1}, {1, 0}, {0, -1}, {-1, 0}};

    public static void main(String[] args) throws IOException {
        BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer stringTokenizer = new StringTokenizer(bufferedReader.readLine());
        int n = Integer.parseInt(stringTokenizer.nextToken());
        int m = Integer.parseInt(stringTokenizer.nextToken());
        int r = Integer.parseInt(stringTokenizer.nextToken());
        int[][] map = new int[n][m];
        for (int i = 0; i < n; i++) {
            stringTokenizer = new StringTokenizer(bufferedReader.readLine());
            for (int j = 0; j < m; j++) {
                map[i][j] = Integer.parseInt(stringTokenizer.nextToken());
            }
        }
        for (int rotate = 0; rotate < r; rotate++) {
            map = rotate(n, m, map);
        }
        print(map);
    }

    private static void print(int[][] map) {
        StringBuilder answer = new StringBuilder();
        for (int[] line : map) {
            for (int field : line) {
                answer.append(field)
                        .append(" ");
            }
            answer.append("\n");
        }
        System.out.println(answer);
    }

    private static int[][] rotate(int n, int m, int[][] map) {
        int rotationEnd = Math.min(n, m) / 2;
        int initX = 0;
        int initY = 0;
        int minX = 0;
        int maxX = n;
        int minY = 0;
        int maxY = m;
        while (rotationEnd-- > 0) {
            int init = map[initX][initY];
            int x = initX;
            int y = initY;
            int direction = 0;
            int dx = initX + DELTAS[direction][0];
            int dy = initY + DELTAS[direction][1];
            while (true) {
                if (dx < minX || dx >= maxX || dy < minY || dy >= maxY) {
                    dx -= DELTAS[direction][0];
                    dy -= DELTAS[direction][1];
                    direction++;
                }
                if (direction == 4) {
                    map[initX + 1][initY] = init;
                    break;
                }
                map[x][y] = map[dx][dy];
                x = dx;
                y = dy;
                dx += DELTAS[direction][0];
                dy += DELTAS[direction][1];

            }
            initX++;
            initY++;
            minX++;
            maxX--;
            minY++;
            maxY--;
        }
        return map;
    }
}
