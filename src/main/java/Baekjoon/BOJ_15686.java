package Baekjoon;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.StringTokenizer;

public class BOJ_15686 {

    private static final int HOUSE = 1;
    private static final int CHICKEN_STORE = 2;

    private static List<int[]> houses;
    private static List<int[]> chickenStores;
    private static boolean[] isClosed;
    private static int closedCount;
    private static int min;

    public static void main(String[] args) throws IOException {
        BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer stringTokenizer = new StringTokenizer(bufferedReader.readLine());
        int n = Integer.parseInt(stringTokenizer.nextToken());
        int m = Integer.parseInt(stringTokenizer.nextToken());
        int[][] map = new int[n][n];
        houses = new ArrayList<>();
        chickenStores = new ArrayList<>();
        for (int i = 0; i < n; i++) {
            stringTokenizer = new StringTokenizer(bufferedReader.readLine());
            for (int j = 0; j < n; j++) {
                int input = Integer.parseInt(stringTokenizer.nextToken());
                map[i][j] = input;
                if (input == HOUSE) {
                    houses.add(new int[]{i, j});
                } else if (input == CHICKEN_STORE) {
                    chickenStores.add(new int[]{i, j});
                }
            }
        }
        isClosed = new boolean[chickenStores.size()];
        closedCount = chickenStores.size() - m;
        min = Integer.MAX_VALUE;
        selectChickenStore(0, 0);
        System.out.println(min);
    }

    private static void selectChickenStore(int cnt, int start) {
        if (cnt == closedCount) {
            getMinChickenDistance();
            return;
        }

        for (int i = start; i < chickenStores.size(); i++) {
            isClosed[i] = true;
            selectChickenStore(cnt + 1, i + 1);
            isClosed[i] = false;
        }
    }

    private static void getMinChickenDistance() {
        int sum = 0;
        for (int[] house : houses) {
            int chickenDistance = Integer.MAX_VALUE;
            for (int i = 0; i < chickenStores.size(); i++) {
                if (!isClosed[i]) {
                    int[] chickenStore = chickenStores.get(i);
                    chickenDistance = Math.min(chickenDistance, getDistance(house[0], house[1], chickenStore[0], chickenStore[1]));
                }
            }
            sum += chickenDistance;
        }
        min = Math.min(min, sum);
    }

    private static int getDistance(int r1, int c1, int r2, int c2) {
        return Math.abs(r1 - r2) + Math.abs(c1 - c2);
    }
}
