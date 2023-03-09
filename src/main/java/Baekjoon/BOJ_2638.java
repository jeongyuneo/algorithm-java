package Baekjoon;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.Arrays;
import java.util.Queue;
import java.util.StringTokenizer;

public class BOJ_2638 {

    private static final int[][] DELTAS = {{0, 1}, {1, 0}, {0, -1}, {-1, 0}};
    private static final Queue<int[]> EMPTY_SPACES = new ArrayDeque<>();
    private static final int CHEESE = 1;
    private static final int AIR = 0;

    private static int[][] map;
    private static int[][] adjacentAirs;
    private static boolean[][] isVisited;
    private static int n;
    private static int m;

    public static void main(String[] args) throws IOException {
        BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer stringTokenizer = new StringTokenizer(bufferedReader.readLine());
        n = Integer.parseInt(stringTokenizer.nextToken());
        m = Integer.parseInt(stringTokenizer.nextToken());
        map = new int[n][m];
        adjacentAirs = new int[n][m];
        isVisited = new boolean[n][m];
        int cheese = 0;
        for (int i = 0; i < n; i++) {
            stringTokenizer = new StringTokenizer(bufferedReader.readLine());
            for (int j = 0; j < m; j++) {
                int input = Integer.parseInt(stringTokenizer.nextToken());
                map[i][j] = input;
                if (input == CHEESE) {
                    cheese++;
                }
            }
        }
        int time = 0;
        while (cheese > 0) {
            time++;
            initialize();
            findMeltingCheeses();
            cheese -= getMeltingCheese();
        }
        System.out.println(time);
    }

    private static void initialize() {
        for (int i = 0; i < n; i++) {
            Arrays.fill(isVisited[i], false);
        }
    }

    private static void findMeltingCheeses() {
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < m; j++) {
                if (map[i][j] == AIR) {
                    isVisited[i][j] = true;
                    EMPTY_SPACES.offer(new int[]{i, j});
                    while (!EMPTY_SPACES.isEmpty()) {
                        int[] current = EMPTY_SPACES.poll();
                        int x = current[0];
                        int y = current[1];
                        for (int[] delta : DELTAS) {
                            int dx = x + delta[0];
                            int dy = y + delta[1];
                            if (dx >= 0 && dx < n && dy >= 0 && dy < m && !isVisited[dx][dy]) {
                                if (map[dx][dy] == AIR) {
                                    EMPTY_SPACES.offer(new int[]{dx, dy});
                                    isVisited[dx][dy] = true;
                                } else {
                                    adjacentAirs[dx][dy]++;
                                }
                            }
                        }
                    }
                    return;
                }
            }
        }
    }

    private static int getMeltingCheese() {
        int meltingCheese = 0;
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < m; j++) {
                if (adjacentAirs[i][j] >= 2) {
                    map[i][j] = AIR;
                    meltingCheese++;
                }
                adjacentAirs[i][j] = 0;
            }
        }
        return meltingCheese;
    }
}
