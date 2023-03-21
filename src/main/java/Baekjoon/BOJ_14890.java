package Baekjoon;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class BOJ_14890 {

    private static int[][] map;
    private static boolean[][] hasSlopeWay;
    private static int n;
    private static int l;
    private static int way;

    public static void main(String[] args) throws IOException {
        BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer stringTokenizer = new StringTokenizer(bufferedReader.readLine());
        n = Integer.parseInt(stringTokenizer.nextToken());
        l = Integer.parseInt(stringTokenizer.nextToken());
        map = new int[n][n];
        hasSlopeWay = new boolean[n][n];
        for (int i = 0; i < n; i++) {
            stringTokenizer = new StringTokenizer(bufferedReader.readLine());
            for (int j = 0; j < n; j++) {
                map[i][j] = Integer.parseInt(stringTokenizer.nextToken());
            }
        }
        way = 2 * n;
        for (int i = 0; i < n; i++) {
            findHorizontalWay(i);
            findVerticalWay(i);
        }
        System.out.println(way);
    }

    private static void findHorizontalWay(int i) {
        boolean canNotPass = false;
        int previous = map[i][0];
        boolean[] canSetSlopeWay = new boolean[n];
        for (int j = 1; j < n; j++) {
            int current = map[i][j];
            if (current == previous) {
                continue;
            }
            if (current - 1 == previous) {
                for (int k = 1; k <= l; k++) {
                    if (j - k < 0 || map[i][j - k] != previous || canSetSlopeWay[j - k]) {
                        canNotPass = true;
                        break;
                    }
                    canSetSlopeWay[j - k] = true;
                }
            } else if (current + 1 == previous) {
                for (int k = 1; k < l; k++) {
                    if (j + k >= n || map[i][j + k] != current || canSetSlopeWay[j + k]) {
                        canNotPass = true;
                        break;
                    }
                    canSetSlopeWay[j + k] = true;
                }
                if (!canNotPass) {
                    canSetSlopeWay[j] = true;
                    j += l - 1;
                }
            } else {
                canNotPass = true;
                break;
            }
            if (canNotPass) {
                break;
            }
            previous = map[i][j];
        }
        if (canNotPass) {
            way--;
            return;
        }
        for (int j = 0; j < n; j++) {
            if (!hasSlopeWay[i][j] && canSetSlopeWay[j]) {
                hasSlopeWay[i][j] = canSetSlopeWay[j];
            }
        }
    }

    private static void findVerticalWay(int i) {
        boolean canNotPass = false;
        int previous = map[0][i];
        boolean[] canSetSlopeWay = new boolean[n];
        for (int j = 1; j < n; j++) {
            int current = map[j][i];
            if (current == previous) {
                continue;
            }
            if (current - 1 == previous) {
                for (int k = 1; k <= l; k++) {
                    if (j - k < 0 || map[j - k][i] != previous || canSetSlopeWay[j - k]) {
                        canNotPass = true;
                        break;
                    }
                    canSetSlopeWay[j - k] = true;
                }
            } else if (current + 1 == previous) {
                for (int k = 1; k < l; k++) {
                    if (j + k >= n || map[j + k][i] != current || canSetSlopeWay[j + k]) {
                        canNotPass = true;
                        break;
                    }
                    canSetSlopeWay[j + k] = true;
                }
                if (!canNotPass) {
                    canSetSlopeWay[j] = true;
                    j += l - 1;
                }
            } else {
                canNotPass = true;
                break;
            }
            if (canNotPass) {
                break;
            }
            previous = map[j][i];
        }
        if (canNotPass) {
            way--;
            return;
        }
        for (int j = 0; j < n; j++) {
            if (!hasSlopeWay[j][i] && canSetSlopeWay[j]) {
                hasSlopeWay[j][i] = canSetSlopeWay[j];
            }
        }
    }
}
