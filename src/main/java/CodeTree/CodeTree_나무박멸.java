package CodeTree;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class CodeTree_나무박멸 {

    private static final int[][] DELTAS = {{0, 1}, {1, 0}, {0, -1}, {-1, 0}};
    private static final int[][] DIAGONALS = {{1, 1}, {1, -1}, {-1, -1}, {-1, 1}};
    private static final int WALL = -1;
    private static final int EMPTY = 0;

    private static int[][] map;
    private static int[][] drugSettingTime;
    private static int n;
    private static int k;
    private static int c;
    private static int total;

    public static void main(String[] args) throws IOException {
        BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer stringTokenizer = new StringTokenizer(bufferedReader.readLine());
        n = Integer.parseInt(stringTokenizer.nextToken());
        int m = Integer.parseInt(stringTokenizer.nextToken());
        k = Integer.parseInt(stringTokenizer.nextToken());
        c = Integer.parseInt(stringTokenizer.nextToken());
        map = new int[n][n];
        drugSettingTime = new int[n][n];
        for (int i = 0; i < n; i++) {
            stringTokenizer = new StringTokenizer(bufferedReader.readLine());
            for (int j = 0; j < n; j++) {
                map[i][j] = Integer.parseInt(stringTokenizer.nextToken());
            }
        }
        for (int time = 1; time <= m; time++) {
            grow();
            spread();
            sprayDrug();
            decreaseDrug();
        }
        System.out.println(total);
    }

    private static void decreaseDrug() {
        for (int x = 0; x < n; x++) {
            for (int y = 0; y < n; y++) {
                if (drugSettingTime[x][y] > 0) {
                    if (--drugSettingTime[x][y] == 0 && map[x][y] != WALL) {
                        map[x][y] = EMPTY;
                    }
                }
            }
        }
    }

    private static void sprayDrug() {
        int[] spraySpot = findSpraySpot();
        total += spraySpot[2];
        int x = spraySpot[0];
        int y = spraySpot[1];
        map[x][y] = EMPTY;
        drugSettingTime[x][y] = c + 1;
        for (int[] diagonal : DIAGONALS) {
            for (int i = 1; i <= k; i++) {
                int dx = x + diagonal[0] * i;
                int dy = y + diagonal[1] * i;
                if (dx >= 0 && dx < n && dy >= 0 && dy < n) {
                    drugSettingTime[dx][dy] = c + 1;
                    if (map[dx][dy] == EMPTY || map[dx][dy] == WALL) {
                        break;
                    }
                    map[dx][dy] = EMPTY;
                }
            }
        }
    }

    private static int[] findSpraySpot() {
        int[] spraySpot = new int[3];
        for (int x = 0; x < n; x++) {
            for (int y = 0; y < n; y++) {
                if (isTree(map[x][y])) {
                    int kill = map[x][y];
                    for (int[] diagonal : DIAGONALS) {
                        for (int i = 1; i <= k; i++) {
                            int dx = x + diagonal[0] * i;
                            int dy = y + diagonal[1] * i;
                            if (dx >= 0 && dx < n && dy >= 0 && dy < n) {
                                if (map[dx][dy] == EMPTY || map[dx][dy] == WALL) {
                                    break;
                                }
                                kill += map[dx][dy];
                            }
                        }
                    }
                    if (spraySpot[2] < kill) {
                        spraySpot[0] = x;
                        spraySpot[1] = y;
                        spraySpot[2] = kill;
                    } else if (spraySpot[2] == kill && spraySpot[0] > x) {
                        spraySpot[0] = x;
                        spraySpot[1] = y;
                    } else if (spraySpot[2] == kill && spraySpot[0] == x && spraySpot[1] > y) {
                        spraySpot[1] = y;
                    }
                }
            }
        }
        return spraySpot;
    }

    private static void spread() {
        int[][] tempMap = new int[n][n];
        for (int x = 0; x < n; x++) {
            for (int y = 0; y < n; y++) {
                if (isTree(map[x][y])) {
                    tempMap[x][y] += map[x][y];
                    int count = 0;
                    for (int[] delta : DELTAS) {
                        int dx = x + delta[0];
                        int dy = y + delta[1];
                        if (dx >= 0 && dx < n && dy >= 0 && dy < n && map[dx][dy] == EMPTY && drugSettingTime[dx][dy] == 0) {
                            count++;
                        }
                    }

                    for (int[] delta : DELTAS) {
                        int dx = x + delta[0];
                        int dy = y + delta[1];
                        if (dx >= 0 && dx < n && dy >= 0 && dy < n && map[dx][dy] == EMPTY && drugSettingTime[dx][dy] == 0) {
                            tempMap[dx][dy] += map[x][y] / count;
                        }
                    }
                } else if (map[x][y] != EMPTY) {
                    tempMap[x][y] = map[x][y];
                }
            }
        }
        map = tempMap;
    }

    private static void grow() {
        for (int x = 0; x < n; x++) {
            for (int y = 0; y < n; y++) {
                if (isTree(map[x][y])) {
                    int count = 0;
                    for (int[] delta : DELTAS) {
                        int dx = x + delta[0];
                        int dy = y + delta[1];
                        if (dx >= 0 && dx < n && dy >= 0 && dy < n && isTree(map[dx][dy])) {
                            count++;
                        }
                    }
                    map[x][y] += count;
                }
            }
        }
    }

    private static boolean isTree(int value) {
        return value >= 1;
    }
}
