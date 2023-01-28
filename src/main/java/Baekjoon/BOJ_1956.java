package Baekjoon;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class BOJ_1956 {

    private static final int MAX_VALUE = 40000000;

    public static void main(String[] args) throws IOException {
        BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer stringTokenizer = new StringTokenizer(bufferedReader.readLine());
        int villageCount = Integer.parseInt(stringTokenizer.nextToken());
        int roadCount = Integer.parseInt(stringTokenizer.nextToken());
        int[][] villages = new int[villageCount][villageCount];
        for (int i = 0; i < villageCount; i++) {
            Arrays.fill(villages[i], MAX_VALUE);
            villages[i][i] = 0;
        }
        for (int i = 0; i < roadCount; i++) {
            stringTokenizer = new StringTokenizer(bufferedReader.readLine());
            villages[Integer.parseInt(stringTokenizer.nextToken()) - 1][Integer.parseInt(stringTokenizer.nextToken()) - 1] = Integer.parseInt(stringTokenizer.nextToken());
        }
        for (int pass = 0; pass < villageCount; pass++) {
            for (int start = 0; start < villageCount; start++) {
                if (pass == start) {
                    continue;
                }
                for (int end = 0; end < villageCount; end++) {
                    if (pass == end || start == end) {
                        continue;
                    }
                    villages[start][end] = Math.min(villages[start][end], villages[start][pass] + villages[pass][end]);
                }
            }
        }

        int minDistance = MAX_VALUE;
        for (int i = 0; i < villageCount; i++) {
            for (int j = 0; j < villageCount; j++) {
                if (i != j && villages[i][j] != MAX_VALUE && villages[j][i] != MAX_VALUE) {
                    minDistance = Math.min(minDistance, villages[i][j] + villages[j][i]);
                }
            }
        }
        if (minDistance != MAX_VALUE) {
            System.out.println(minDistance);
        } else {
            System.out.println(-1);
        }
    }
}
