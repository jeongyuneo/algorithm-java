package SWExpertAcademy;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class SWEA_4014 {

    private static final int HEIGHT_SIZE = 6;
    private static final int UPSIDE = -1;
    private static final int DOWNSIDE = 1;

    private static int n;
    private static int x;

    public static void main(String[] args) throws IOException {
        BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder stringBuilder = new StringBuilder();
        int testCase = Integer.parseInt(bufferedReader.readLine());
        for (int t = 1; t <= testCase; t++) {
            StringTokenizer stringTokenizer = new StringTokenizer(bufferedReader.readLine());
            n = Integer.parseInt(stringTokenizer.nextToken());
            x = Integer.parseInt(stringTokenizer.nextToken());
            int[][] map = new int[n][n];
            for (int i = 0; i < n; i++) {
                stringTokenizer = new StringTokenizer(bufferedReader.readLine());
                for (int j = 0; j < n; j++) {
                    map[i][j] = Integer.parseInt(stringTokenizer.nextToken());
                }
            }

            int answer = 0;
            for (int i = 0; i < n; i++) {
                if (canInstallByHeight(map, i)) {
                    answer++;
                }
                if (canInstallByWidth(map, i)) {
                    answer++;
                }
            }

            stringBuilder.append("#")
                    .append(t)
                    .append(" ")
                    .append(answer)
                    .append("\n");
        }
        System.out.println(stringBuilder);
    }

    private static boolean canInstallByHeight(int[][] map, int width) {
        int[] heights = new int[HEIGHT_SIZE + 1];
        heights[map[0][width]]++;
        for (int current = 1; current < n; current++) {
            int previous = current - 1;
            if (map[current][width] == map[previous][width]) {
                heights[map[current][width]]++;
            } else if (map[previous][width] - map[current][width] == UPSIDE) {    // 올라가는 경사
                if (heights[map[previous][width]] < x) {
                    return false;
                }
                heights[map[current][width]]++;
                heights[map[previous][width]] = 0;
            } else if (map[previous][width] - map[current][width] == DOWNSIDE && current + x <= n) {  // 내려가는 경사
                for (int nextX = current + 1; nextX < current + x; nextX++) {
                    if (map[current][width] != map[nextX][width]) {
                        return false;
                    }
                }
                current += x - 1;
            } else {
                return false;
            }
        }
        return true;
    }

    private static boolean canInstallByWidth(int[][] map, int height) {
        int[] heights = new int[HEIGHT_SIZE + 1];
        heights[map[height][0]]++;
        for (int current = 1; current < n; current++) {
            int previous = current - 1;
            if (map[height][current] == map[height][previous]) {
                heights[map[height][current]]++;
            } else if (map[height][previous] - map[height][current] == UPSIDE) { // 올라가는 경사
                if (heights[map[height][previous]] < x) {
                    return false;
                }
                heights[map[height][current]]++;
                heights[map[height][previous]] = 0;
            } else if (map[height][previous] - map[height][current] == DOWNSIDE && current + x <= n) {    // 내려가는 경사
                for (int nextY = current + 1; nextY < current + x; nextY++) {
                    if (map[height][current] != map[height][nextY]) {
                        return false;
                    }
                }
                current += x - 1;
            } else {
                return false;
            }
        }
        return true;
    }
}
