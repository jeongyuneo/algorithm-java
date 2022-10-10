package Baekjoon;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class BOJ_17779 {

    private static int[][] map;
    private static boolean[][] isBoundary;
    private static int[] populations;
    private static int n;
    private static int totalPopulation;

    public static void main(String[] args) throws IOException {
        BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(System.in));
        n = Integer.parseInt(bufferedReader.readLine());
        map = new int[n + 1][n + 1];
        for (int i = 1; i <= n; i++) {
            StringTokenizer stringTokenizer = new StringTokenizer(bufferedReader.readLine());
            for (int j = 1; j <= n; j++) {
                int input = Integer.parseInt(stringTokenizer.nextToken());
                map[i][j] = input;
                totalPopulation += input;
            }
        }

        int minGap = Integer.MAX_VALUE;
        for (int x = 1; x <= n; x++) {
            for (int y = 1; y <= n; y++) {
                for (int d1 = 1; d1 <= n; d1++) {
                    for (int d2 = 1; d2 <= n - y; d2++) {
                        if (x + d1 + d2 > n || y - d1 < 1 || y + d2 > n) {
                            continue;
                        }
                        isBoundary = new boolean[n + 1][n + 1];
                        populations = new int[5];
                        drawBoundary(x, y, d1, d2);
                        searchDistrictOne(x, y, d1);
                        searchDistrictTwo(x, y, d2);
                        searchDistrictThree(x, y, d1, d2);
                        searchDistrictFour(x, y, d1, d2);
                        searchDistrictFive();
                        Arrays.sort(populations);
                        minGap = Math.min(minGap, populations[4] - populations[0]);
                    }
                }
            }
        }
        System.out.println(minGap);
    }

    private static void drawBoundary(int x, int y, int d1, int d2) {
        for (int i = 0; i <= d1; i++) {
            isBoundary[x + i][y - i] = true;
            isBoundary[x + d2 + i][y + d2 - i] = true;
        }
        for (int i = 0; i <= d2; i++) {
            isBoundary[x + i][y + i] = true;
            isBoundary[x + d1 + i][y - d1 + i] = true;
        }
    }

    private static void searchDistrictOne(int x, int y, int d1) {
        for (int i = 1; i < x + d1; i++) {
            for (int j = 1; j <= y; j++) {
                if (isBoundary[i][j]) {
                    break;
                }
                populations[0] += map[i][j];
            }
        }
    }

    private static void searchDistrictTwo(int x, int y, int d2) {
        for (int i = 1; i <= x + d2; i++) {
            for (int j = n; j > y; j--) {
                if (isBoundary[i][j]) {
                    break;
                }
                populations[1] += map[i][j];
            }
        }
    }

    private static void searchDistrictThree(int x, int y, int d1, int d2) {
        for (int i = x + d1; i <= n; i++) {
            for (int j = 1; j < y - d1 + d2; j++) {
                if (isBoundary[i][j]) {
                    break;
                }
                populations[2] += map[i][j];
            }
        }
    }

    private static void searchDistrictFour(int x, int y, int d1, int d2) {
        for (int i = x + d2 + 1; i <= n; i++) {
            for (int j = n; j >= y - d1 + d2; j--) {
                if (isBoundary[i][j]) {
                    break;
                }
                populations[3] += map[i][j];
            }
        }
    }

    private static void searchDistrictFive() {
        int sum = 0;
        for (int population : populations) {
            sum += population;
        }
        populations[4] = totalPopulation - sum;
    }
}
