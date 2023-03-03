package Baekjoon;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class BOJ_2285 {

    private static final int LOCATION = 0;
    private static final int POPULATION = 1;

    public static void main(String[] args) throws IOException {
        BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(System.in));
        int n = Integer.parseInt(bufferedReader.readLine());
        int[][] towns = new int[n][2];
        long people = 0;
        for (int i = 0; i < n; i++) {
            StringTokenizer stringTokenizer = new StringTokenizer(bufferedReader.readLine());
            int location = Integer.parseInt(stringTokenizer.nextToken());
            int population = Integer.parseInt(stringTokenizer.nextToken());
            towns[i] = new int[]{location, population};
            people += population;
        }
        Arrays.sort(towns, (town1, town2) -> {
            if (town1[LOCATION] == town2[LOCATION]) {
                return town1[POPULATION] - town2[POPULATION];
            }
            return town1[LOCATION] - town2[LOCATION];
        });
        System.out.println(getPostOffice(towns, (people + 1) / 2));
    }

    private static int getPostOffice(int[][] towns, long mid) {
        long sum = 0;
        for (int[] town : towns) {
            if ((sum += town[POPULATION]) >= mid) {
                return town[LOCATION];
            }
        }
        return -1;
    }
}
