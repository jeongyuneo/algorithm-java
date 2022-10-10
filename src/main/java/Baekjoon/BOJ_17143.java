package Baekjoon;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class BOJ_17143 {

    static class Shark {

        int x;
        int y;
        int speed;
        int direction;
        int size;

        public Shark(int x, int y, int speed, int direction, int size) {
            super();
            this.x = x;
            this.y = y;
            this.speed = speed;
            this.direction = direction;
            this.size = size;
        }
    }

    private static final int[][] DELTAS = {{0, 0}, {-1, 0}, {1, 0}, {0, 1}, {0, -1}};

    private static Shark[][] map;
    private static int r;
    private static int c;
    private static int sum;

    public static void main(String[] args) throws IOException {
        BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer stringTokenizer = new StringTokenizer(bufferedReader.readLine());
        r = Integer.parseInt(stringTokenizer.nextToken());
        c = Integer.parseInt(stringTokenizer.nextToken());
        int m = Integer.parseInt(stringTokenizer.nextToken());
        if (m != 0) {
            map = new Shark[r + 1][c + 1];
            for (int i = 0; i < m; i++) {
                stringTokenizer = new StringTokenizer(bufferedReader.readLine());
                int x = Integer.parseInt(stringTokenizer.nextToken());
                int y = Integer.parseInt(stringTokenizer.nextToken());
                int speed = Integer.parseInt(stringTokenizer.nextToken());
                int direction = Integer.parseInt(stringTokenizer.nextToken());
                int size = Integer.parseInt(stringTokenizer.nextToken());
                map[x][y] = new Shark(x, y, speed, direction, size);
            }

            for (int fisingKing = 1; fisingKing <= c; fisingKing++) {
                catchShark(fisingKing);
                moveSharks();
            }
        }
        System.out.println(sum);
    }

    private static void moveSharks() {
        Shark[][] movingSharks = new Shark[r + 1][c + 1];
        for (int i = 1; i <= r; i++) {
            for (int j = 1; j <= c; j++) {
                if (map[i][j] != null) {
                    Shark shark = map[i][j];
                    int x = shark.x;
                    int y = shark.y;
                    int direction = shark.direction;
                    int speed = shark.speed;
                    int size = shark.size;
                    while (speed-- > 0) {
                        int dx = x + DELTAS[direction][0];
                        int dy = y + DELTAS[direction][1];
                        if (dx < 1 || dx > r || dy < 1 || dy > c) {
                            ++direction;
                            if (direction % 2 == 1) {
                                direction -= 2;
                            }
                            ++speed;
                        } else {
                            x = dx;
                            y = dy;
                        }
                    }

                    if (movingSharks[x][y] == null) {
                        movingSharks[x][y] = new Shark(x, y, shark.speed, direction, size);
                    } else {
                        Shark previousShark = movingSharks[x][y];
                        if (previousShark.size < size) {
                            movingSharks[x][y] = new Shark(x, y, shark.speed, direction, size);
                        }
                    }
                }
            }
        }
        map = movingSharks;
    }

    private static void catchShark(int fishingKing) {
        for (int depth = 1; depth <= r; depth++) {
            if (map[depth][fishingKing] != null) {
                sum += map[depth][fishingKing].size;
                map[depth][fishingKing] = null;
                return;
            }
        }
    }
}
