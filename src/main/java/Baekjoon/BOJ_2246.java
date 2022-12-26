package Baekjoon;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class BOJ_2246 {

    private static final int DISTANCE = 0;
    private static final int COST = 1;

    public static void main(String[] args) throws IOException {
        BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(System.in));
        int n = Integer.parseInt(bufferedReader.readLine());
        int[][] condos = new int[n][2];
        for (int i = 0; i < n; i++) {
            StringTokenizer stringTokenizer = new StringTokenizer(bufferedReader.readLine());
            condos[i][DISTANCE] = Integer.parseInt(stringTokenizer.nextToken());
            condos[i][COST] = Integer.parseInt(stringTokenizer.nextToken());
        }
        Arrays.sort(condos, (condo1, condo2) -> {
            if (condo1[DISTANCE] == condo2[DISTANCE]) {
                return condo1[COST] - condo2[COST];
            }
            return condo1[DISTANCE] - condo2[DISTANCE];
        });
        int candidateCount = 1;
        int minCost = condos[0][COST];
        for (int i = 1; i < n; i++) {
            if (condos[i][COST] < minCost) {
                minCost = condos[i][COST];
                candidateCount++;
            }
        }
        System.out.println(candidateCount);
    }
}
