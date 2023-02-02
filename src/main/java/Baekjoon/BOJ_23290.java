package Baekjoon;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.StringTokenizer;

public class BOJ_23290 {

    static class Fish {

        int x;
        int y;
        int direction;

        public Fish(int x, int y) {
            this.x = x;
            this.y = y;
            direction = -1;
        }

        public Fish(int x, int y, int direction) {
            this.x = x;
            this.y = y;
            this.direction = direction;
        }

        public Fish copy(int dx, int dy, int direction) {
            return new Fish(dx, dy, direction);
        }
    }

    static class Shark extends Fish {

        public Shark(int x, int y) {
            super(x, y);
        }
    }

    private static final int[][] FISH_DELTAS = {{0, -1}, {-1, -1}, {-1, 0}, {-1, 1}, {0, 1}, {1, 1}, {1, 0}, {1, -1}};
    private static final int[][] SHARK_DELTAS = {{-1, 0}, {0, -1}, {1, 0}, {0, 1}};
    private static final int SIZE = 4;
    private static final List<Fish>[][] MAP = new List[SIZE][SIZE];
    private static final List<Fish>[][] COPIED_MAP = new List[SIZE][SIZE];
    private static final int[][] SMELLS = new int[SIZE][SIZE];
    private static final boolean[][] IS_VISITED = new boolean[SIZE][SIZE];
    private static final int SHARK_MOVE = 3;
    private static final int[] SHARK_MOVES = new int[SHARK_MOVE];
    private static final int[] DIRECTIONS = new int[SHARK_MOVE];

    private static int time;
    private static int maxCatchableFish;
    private static Shark SHARK;

    public static void main(String[] args) throws IOException {
        BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer stringTokenizer = new StringTokenizer(bufferedReader.readLine());
        int m = Integer.parseInt(stringTokenizer.nextToken());
        int s = Integer.parseInt(stringTokenizer.nextToken());
        for (int i = 0; i < SIZE; i++) {
            for (int j = 0; j < SIZE; j++) {
                MAP[i][j] = new ArrayList<>();
                COPIED_MAP[i][j] = new ArrayList<>();
            }
        }
        for (int i = 1; i <= m; i++) {
            stringTokenizer = new StringTokenizer(bufferedReader.readLine());
            int x = Integer.parseInt(stringTokenizer.nextToken()) - 1;
            int y = Integer.parseInt(stringTokenizer.nextToken()) - 1;
            int direction = Integer.parseInt(stringTokenizer.nextToken()) - 1;
            MAP[x][y].add(new Fish(x, y, direction));
        }
        stringTokenizer = new StringTokenizer(bufferedReader.readLine());
        int x = Integer.parseInt(stringTokenizer.nextToken()) - 1;
        int y = Integer.parseInt(stringTokenizer.nextToken()) - 1;
        SHARK = new Shark(x, y);

        for (time = 0; time < s; time++) {
            initialize();
            moveFish();
            maxCatchableFish = -1;
            findSharkMove(0);
            moveShark();
            removeFishSmell();
            copyFish();
        }
        System.out.println(getFish());
    }

    private static void initialize() {
        for (int i = 0; i < SIZE; i++) {
            for (int j = 0; j < SIZE; j++) {
                COPIED_MAP[i][j].clear();
            }
        }
    }

    private static void moveFish() {
        for (int x = 0; x < SIZE; x++) {
            for (int y = 0; y < SIZE; y++) {
                for (Fish fish : MAP[x][y]) {
                    boolean canMove = false;
                    for (int i = 0; i < 8; i++) {
                        int direction = (fish.direction - i + 8) % 8;
                        int dx = x + FISH_DELTAS[direction][0];
                        int dy = y + FISH_DELTAS[direction][1];
                        if (dx >= 0 && dx < SIZE && dy >= 0 && dy < SIZE && SMELLS[dx][dy] == 0 && (SHARK.x != dx || SHARK.y != dy)) {
                            COPIED_MAP[dx][dy].add(fish.copy(dx, dy, direction));
                            canMove = true;
                            break;
                        }
                    }
                    if (!canMove) {
                        COPIED_MAP[x][y].add(fish);
                    }
                }
            }
        }
    }

    private static void findSharkMove(int cnt) {
        if (cnt == SHARK_MOVE) {
            int catchableFish = getCatchableFish();
            if (maxCatchableFish < catchableFish) {
                maxCatchableFish = catchableFish;
                for (int i = 0; i < SHARK_MOVE; i++) {
                    SHARK_MOVES[i] = DIRECTIONS[i];
                }
            }
            return;
        }

        for (int i = 0; i < 4; i++) {
            DIRECTIONS[cnt] = i;
            findSharkMove(cnt + 1);
        }
    }

    private static int getCatchableFish() {
        for (int i = 0; i < SIZE; i++) {
            Arrays.fill(IS_VISITED[i], false);
        }
        int catchableFish = 0;
        int x = SHARK.x;
        int y = SHARK.y;
        for (int direction : DIRECTIONS) {
            x += SHARK_DELTAS[direction][0];
            y += SHARK_DELTAS[direction][1];
            if (x < 0 || x >= SIZE || y < 0 || y >= SIZE) {
                return -1;
            }
            if (!IS_VISITED[x][y]) {
                catchableFish += COPIED_MAP[x][y].size();
                IS_VISITED[x][y] = true;
            }
        }
        return catchableFish;
    }

    private static void moveShark() {
        for (int direction : SHARK_MOVES) {
            SHARK.x += SHARK_DELTAS[direction][0];
            SHARK.y += SHARK_DELTAS[direction][1];
            if (!COPIED_MAP[SHARK.x][SHARK.y].isEmpty()) {
                COPIED_MAP[SHARK.x][SHARK.y].clear();
                SMELLS[SHARK.x][SHARK.y] = time + 2;
            }
        }
    }

    private static void removeFishSmell() {
        for (int x = 0; x < SIZE; x++) {
            for (int y = 0; y < SIZE; y++) {
                if (SMELLS[x][y] == time) {
                    SMELLS[x][y] = 0;
                }
            }
        }
    }

    private static void copyFish() {
        for (int x = 0; x < SIZE; x++) {
            for (int y = 0; y < SIZE; y++) {
                MAP[x][y].addAll(COPIED_MAP[x][y]);
            }
        }
    }

    private static int getFish() {
        int fishCount = 0;
        for (int x = 0; x < SIZE; x++) {
            for (int y = 0; y < SIZE; y++) {
                fishCount += MAP[x][y].size();
            }
        }
        return fishCount;
    }
}
