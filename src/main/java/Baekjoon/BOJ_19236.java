package Baekjoon;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class BOJ_19236 {

    static class Fish {

        int x;
        int y;
        int number;
        int direction;
        boolean isCaught;

        public Fish(int x, int y, int number, int direction) {
            this.x = x;
            this.y = y;
            this.number = number;
            this.direction = direction;
            isCaught = false;
        }

        public Fish(int x, int y, int number, int direction, boolean isCaught) {
            this.x = x;
            this.y = y;
            this.number = number;
            this.direction = direction;
            this.isCaught = isCaught;
        }

        public void move(int x, int y, int direction) {
            this.x = x;
            this.y = y;
            this.direction = direction;
        }

        public Fish copy() {
            return new Fish(x, y, number, direction, isCaught);
        }
    }

    static class Shark {

        int x;
        int y;
        int direction;
        int feed;

        public Shark(int x, int y, int direction, int feed) {
            this.x = x;
            this.y = y;
            this.direction = direction;
            this.feed = feed;
        }
    }

    private static final int[][] DELTAS = {{-1, 0}, {-1, -1}, {0, -1}, {1, -1}, {1, 0}, {1, 1}, {0, 1}, {-1, 1}};
    private static final int SIZE = 4;
    private static final int EMPTY = 0;
    private static final int SHARK = 100;

    private static int[][] map;
    private static Fish[] fish;
    private static int maxFeed;

    public static void main(String[] args) throws IOException {
        BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(System.in));
        map = new int[SIZE][SIZE];
        fish = new Fish[SIZE * SIZE + 1];
        for (int i = 0; i < SIZE; i++) {
            StringTokenizer stringTokenizer = new StringTokenizer(bufferedReader.readLine());
            for (int j = 0; j < SIZE; j++) {
                int number = Integer.parseInt(stringTokenizer.nextToken());
                int direction = Integer.parseInt(stringTokenizer.nextToken()) - 1;
                map[i][j] = number;
                fish[number] = new Fish(i, j, number, direction);
            }
        }

        Fish currentFish = fish[map[0][0]];
        currentFish.isCaught = true;
        map[0][0] = SHARK;
        moveShark(new Shark(0, 0, currentFish.direction, currentFish.number));
        System.out.println(maxFeed);
    }

    private static void moveShark(Shark shark) {
        int[][] initMap = copyMap(map);
        Fish[] initFish = copyFishs(fish);

        moveFishs();

        map[shark.x][shark.y] = EMPTY;
        boolean canGo = false;
        for (int i = 1; i <= 3; i++) {
            int dx = shark.x + DELTAS[shark.direction][0] * i;
            int dy = shark.y + DELTAS[shark.direction][1] * i;
            if (dx >= 0 && dx < SIZE && dy >= 0 && dy < SIZE && map[dx][dy] != EMPTY) {
                canGo = true;
                Fish currentFish = fish[map[dx][dy]];

                fish[currentFish.number].isCaught = true;
                map[dx][dy] = SHARK;

                moveShark(new Shark(dx, dy, currentFish.direction, shark.feed + currentFish.number));

                fish[currentFish.number].isCaught = false;
                map[dx][dy] = currentFish.number;
            }
        }

        if (!canGo) {
            maxFeed = Math.max(maxFeed, shark.feed);
        }

        map = copyMap(initMap);
        fish = copyFishs(initFish);
    }

    private static void moveFishs() {
        for (int number = 1; number <= 16; number++) {
            Fish currentFish = fish[number];
            if (currentFish.isCaught) {
                continue;
            }

            for (int direction = 0; direction < 9; direction++) {
                int nextDirection = (currentFish.direction + direction) % 8;
                int dx = currentFish.x + DELTAS[nextDirection][0];
                int dy = currentFish.y + DELTAS[nextDirection][1];
                if (dx >= 0 && dx < SIZE && dy >= 0 && dy < SIZE && map[dx][dy] != SHARK) {
                    if (map[dx][dy] == EMPTY) {
                        map[dx][dy] = number;
                        map[currentFish.x][currentFish.y] = EMPTY;
                        currentFish.move(dx, dy, nextDirection);
                    } else {
                        int toFish = fish[map[dx][dy]].number;
                        fish[toFish].move(currentFish.x, currentFish.y, fish[toFish].direction);
                        map[currentFish.x][currentFish.y] = toFish;

                        currentFish.move(dx, dy, nextDirection);
                        map[dx][dy] = number;
                    }
                    break;
                }
            }
        }
    }

    private static Fish[] copyFishs(Fish[] fishs) {
        Fish[] copiedFish = new Fish[SIZE * SIZE + 1];
        for (int i = 1; i <= 16; i++) {
            copiedFish[i] = fishs[i].copy();
        }
        return copiedFish;
    }

    private static int[][] copyMap(int[][] map) {
        int[][] copiedMap = new int[SIZE][SIZE];
        for (int i = 0; i < SIZE; i++) {
            for (int j = 0; j < SIZE; j++) {
                copiedMap[i][j] = map[i][j];
            }
        }
        return copiedMap;
    }
}
