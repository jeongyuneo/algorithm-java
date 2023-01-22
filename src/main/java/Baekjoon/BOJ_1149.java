package Baekjoon;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class BOJ_1149 {

    public static void main(String[] args) throws IOException {
        BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(System.in));
        int n = Integer.parseInt(bufferedReader.readLine());
        int[][] houses = new int[n + 1][3];
        for (int i = 1; i <= n; i++) {
            StringTokenizer stringTokenizer = new StringTokenizer(bufferedReader.readLine());
            houses[i][0] = Math.min(houses[i - 1][1], houses[i - 1][2]) + Integer.parseInt(stringTokenizer.nextToken());
            houses[i][1] = Math.min(houses[i - 1][0], houses[i - 1][2]) + Integer.parseInt(stringTokenizer.nextToken());
            houses[i][2] = Math.min(houses[i - 1][0], houses[i - 1][1]) + Integer.parseInt(stringTokenizer.nextToken());
        }
        System.out.println(Math.min(houses[n][0], Math.min(houses[n][1], houses[n][2])));
    }
}
