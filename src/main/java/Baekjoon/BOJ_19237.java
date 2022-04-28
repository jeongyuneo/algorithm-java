package Baekjoon;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class BOJ_19237 {

    static class Shark {

        int x;
        int y;
        int direction;
        int[][] priority;

        public Shark(int x, int y) {
            this.x = x;
            this.y = y;
            priority = new int[4][4];
        }
    }

    static class Smell {

        int shark;
        int leftTime;

        public Smell(int shark, int leftTime) {
            super();
            this.shark = shark;
            this.leftTime = leftTime;
        }
    }

    private static final int[][] DELTAS = {{-1, 0}, {1, 0}, {0, -1}, {0, 1}};
    private static final int EMPTY = 0;

    private static int[][] map;
    private static Smell[][] trace;
    private static Shark[] sharks;
    private static int n;
    private static int m;
    private static int k;
    private static int sharkNum;

    public static void main(String[] args) throws IOException {
        BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer stringTokenizer = new StringTokenizer(bufferedReader.readLine());
        n = Integer.parseInt(stringTokenizer.nextToken());
        m = Integer.parseInt(stringTokenizer.nextToken());
        k = Integer.parseInt(stringTokenizer.nextToken());
        map = new int[n][n];
        trace = new Smell[n][n];
        sharks = new Shark[m + 1];
        for (int i = 0; i < n; i++) {
            stringTokenizer = new StringTokenizer(bufferedReader.readLine());
            for (int j = 0; j < n; j++) {
                int input = Integer.parseInt(stringTokenizer.nextToken());
                map[i][j] = input;
                if (input != EMPTY) {
                    sharks[input] = new Shark(i, j);
                    trace[i][j] = new Smell(input, k);
                }
            }
        }
        stringTokenizer = new StringTokenizer(bufferedReader.readLine());
        for (int i = 1; i <= m; i++) {
            sharks[i].direction = Integer.parseInt(stringTokenizer.nextToken()) - 1;
        }
        for (int i = 1; i <= m; i++) {
            for (int j = 0; j < 4; j++) {
                stringTokenizer = new StringTokenizer(bufferedReader.readLine());
                for (int l = 0; l < 4; l++) {
                    sharks[i].priority[j][l] = Integer.parseInt(stringTokenizer.nextToken()) - 1;
                }
            }
        }

        sharkNum = m;
        int time = 0;
        while (sharkNum > 1) {
            if (++time > 1000) {
                time = -1;
                break;
            }
            int[][] previousMap = copyMap();
            Smell[][] previousTrace = copyTrace();
            moveSharks(previousMap, previousTrace);
            reduceTrace(previousTrace);
        }
        System.out.println(time);
    }

    private static void moveSharks(int[][] previousMap, Smell[][] previousTrace) {
        for (int num = 1; num <= m; num++) {
            if (sharks[num] != null) {
                Shark shark = sharks[num];
                // 냄새 없는 곳 탐색
                int[] candidates = new int[4];
                int idx = 0;
                for (int dir = 0; dir < 4; dir++) {
                    int dx = shark.x + DELTAS[dir][0];
                    int dy = shark.y + DELTAS[dir][1];
                    if (dx >= 0 && dx < n && dy >= 0 && dy < n && previousMap[dx][dy] == EMPTY) {
                        candidates[idx++] = dir;
                    }
                }

                int direction = -1;
                if (idx == 1) {
                    direction = candidates[0];
                } else if (idx > 1) {
                    direction = findDirection(shark, candidates, idx);
                }
                if (direction != -1) {
                    moveShark(num, shark, direction);
                    continue;
                }

                // 본인 냄새있는 곳 탐색
                candidates = new int[4];
                idx = 0;
                for (int dir = 0; dir < 4; dir++) {
                    int dx = shark.x + DELTAS[dir][0];
                    int dy = shark.y + DELTAS[dir][1];
                    if (dx >= 0 && dx < n && dy >= 0 && dy < n && previousTrace[dx][dy] != null && previousTrace[dx][dy].shark == num) {
                        candidates[idx++] = dir;
                    }
                }

                if (idx == 1) {
                    direction = candidates[0];
                } else if (idx > 1) {
                    direction = findDirection(shark, candidates, idx);
                }
                int dx = shark.x + DELTAS[direction][0];
                int dy = shark.y + DELTAS[direction][1];
                previousTrace[dx][dy] = null;
                sharks[num].x = dx;
                sharks[num].y = dy;
                sharks[num].direction = direction;
                trace[dx][dy].leftTime = k;
            }
        }
    }

    private static int findDirection(Shark shark, int[] candidates, int idx) {
        int[] priority = shark.priority[shark.direction];
        for (int dir : priority) {
            for (int i = 0; i < idx; i++) {
                if (dir == candidates[i]) {
                    return dir;
                }
            }
        }
        return -1;
    }

    private static void moveShark(int num, Shark shark, int dir) {
        int dx = shark.x + DELTAS[dir][0];
        int dy = shark.y + DELTAS[dir][1];
        if (map[dx][dy] != EMPTY) {
            sharks[num] = null;
            sharkNum--;
        } else {
            sharks[num].x = dx;
            sharks[num].y = dy;
            sharks[num].direction = dir;
            map[dx][dy] = num;
            trace[dx][dy] = new Smell(num, k);
        }
    }

    private static void reduceTrace(Smell[][] previousTrace) {
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                if (previousTrace[i][j] != null && --previousTrace[i][j].leftTime == 0) {
                    trace[i][j] = null;
                    map[i][j] = EMPTY;
                }
            }
        }
    }

    private static int[][] copyMap() {
        int[][] previousMap = new int[n][n];
        for (int i = 0; i < n; i++) {
            System.arraycopy(map[i], 0, previousMap[i], 0, n);
        }
        return previousMap;
    }

    private static Smell[][] copyTrace() {
        Smell[][] previousTrace = new Smell[n][n];
        for (int i = 0; i < n; i++) {
            System.arraycopy(trace[i], 0, previousTrace[i], 0, n);
        }
        return previousTrace;
    }
}
