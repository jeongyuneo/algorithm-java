package Baekjoon;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.StringTokenizer;

public class BOJ_15686 {

    private static final int X = 0;
    private static final int Y = 1;

    private static int minDistance = Integer.MAX_VALUE;
    private static int n;
    private static int m;

    public static void main(String[] args) throws IOException {
        BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer stringTokenizer = new StringTokenizer(bufferedReader.readLine());
        n = Integer.parseInt(stringTokenizer.nextToken());
        m = Integer.parseInt(stringTokenizer.nextToken());
        int[][] city = new int[n+1][n+1];
        List<int[]> chickenShops = new ArrayList<>();
        List<int[]> houses = new ArrayList<>();
        for (int i = 1; i <= n; i++) {
            stringTokenizer = new StringTokenizer(bufferedReader.readLine());
            for (int j = 1; j <= n; j++) {
                int input = Integer.parseInt(stringTokenizer.nextToken());
                if (input == 2) {
                    chickenShops.add(new int[]{i, j});
                } else if (input == 1) {
                    houses.add(new int[]{i, j});
                }
                city[i][j] = input;
            }
        }

        combination(chickenShops, houses, 0, 0, new ArrayList<>());
        System.out.println(minDistance);
    }

    private static void combination(List<int[]> chickenShops, List<int[]> houses, int cnt, int start, List<int[]> selectedChickenShops) {
        if (cnt == m) {
            int totalDistance = 0;
            for (int[] house : houses) {
                int distance = n*n;
                for (int[] selectedChickenShop : selectedChickenShops) {
                    distance = Math.min(distance, Math.abs(selectedChickenShop[X] - house[X]) + Math.abs(selectedChickenShop[Y] - house[Y]));
                }
                totalDistance += distance;
            }
            minDistance = Math.min(minDistance, totalDistance);
            return;
        }

        for (int i = start; i < chickenShops.size(); i++) {
            selectedChickenShops.add(chickenShops.get(i));
            combination(chickenShops, houses, cnt+1, i+1, selectedChickenShops);
            selectedChickenShops.remove(chickenShops.get(i));
        }
    }
}
