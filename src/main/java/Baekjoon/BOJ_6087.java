package Baekjoon;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Comparator;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

public class BOJ_6087 {

    private static final int[][] DELTAS = {{0, 1}, {1, 0}, {0, -1}, {-1, 0}};
    private static final char C = 'C';
    private static final char WALL = '*';

    static class Laser {

        int x;
        int y;
        int direction;
        int mirrorCount;

        public Laser(int x, int y, int direction, int mirrorCount) {
            this.x = x;
            this.y = y;
            this.direction = direction;
            this.mirrorCount = mirrorCount;
        }
    }

    public static void main(String[] args) throws IOException {
        BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer stringTokenizer = new StringTokenizer(bufferedReader.readLine());
        int width = Integer.parseInt(stringTokenizer.nextToken());
        int height = Integer.parseInt(stringTokenizer.nextToken());
        char[][] map = new char[height][width];
        Laser start = null;
        int[][] minimumMirrorCounts = new int[height][width];
        for (int x = 0; x < height; x++) {
            String inputLine = bufferedReader.readLine();
            for (int y = 0; y < width; y++) {
                minimumMirrorCounts[x][y] = Integer.MAX_VALUE;
                char input = inputLine.charAt(y);
                map[x][y] = input;
                if (input == C && start == null) {
                    minimumMirrorCounts[x][y] = 0;
                    start = new Laser(x, y, 0, 0);
                }
            }
        }

        PriorityQueue<Laser> laser = new PriorityQueue<>(Comparator.comparing(tempLaser -> tempLaser.mirrorCount));
        for (int direction = 0, deltasLength = DELTAS.length; direction < deltasLength; direction++) {
            int dx = start.x + DELTAS[direction][0];
            int dy = start.y + DELTAS[direction][1];
            if (isInBoundary(width, height, dx, dy) && map[dx][dy] != WALL) {
                laser.offer(new Laser(dx, dy, direction, 0));
                minimumMirrorCounts[dx][dy] = 0;
            }
        }

        int minimumMirrorCount = Integer.MAX_VALUE;
        while (!laser.isEmpty()) {
            Laser current = laser.poll();
            int x = current.x;
            int y = current.y;
            int direction = current.direction;
            int mirrorCount = current.mirrorCount;

            if (map[x][y] == C) {
                minimumMirrorCount = mirrorCount;
                break;
            }

            for (int i = -1; i <= 1; i++) {
                int nextDirection = (direction + i + DELTAS.length) % DELTAS.length;
                int dx = x + DELTAS[nextDirection][0];
                int dy = y + DELTAS[nextDirection][1];
                if (isInBoundary(width, height, dx, dy) && map[dx][dy] != WALL) {
                    if (direction == nextDirection && minimumMirrorCounts[dx][dy] >= mirrorCount) {
                        laser.offer(new Laser(dx, dy, nextDirection, mirrorCount));
                        minimumMirrorCounts[dx][dy] = mirrorCount;
                    } else if (minimumMirrorCounts[dx][dy] > mirrorCount) {
                        laser.offer(new Laser(dx, dy, nextDirection, mirrorCount + 1));
                        minimumMirrorCounts[dx][dy] = mirrorCount + 1;
                    }
                }
            }
        }
        System.out.println(minimumMirrorCount);
    }

    private static boolean isInBoundary(int width, int height, int dx, int dy) {
        return dx >= 0 && dx < height && dy >= 0 && dy < width;
    }
}
