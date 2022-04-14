package Baekjoon;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class BOJ_9205 {

    public static void main(String[] args) throws IOException {
        BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer stringTokenizer;
        StringBuilder stringBuilder = new StringBuilder();
        int testCase = Integer.parseInt(bufferedReader.readLine());
        for (int t = 0; t < testCase; t++) {
            int n = Integer.parseInt(bufferedReader.readLine());
            int[][] locations = new int[n + 2][n + 2];
            for (int i = 0; i < n + 2; i++) {
                stringTokenizer = new StringTokenizer(bufferedReader.readLine());
                locations[i][0] = Integer.parseInt(stringTokenizer.nextToken());
                locations[i][1] = Integer.parseInt(stringTokenizer.nextToken());
            }

            boolean[][] table = new boolean[n + 2][n + 2];
            for (int i = 0; i < n + 2; i++) {
                for (int j = 0; j < n + 2; j++) {
                    int distance = getDistance(locations[i][0], locations[i][1], locations[j][0], locations[j][1]);
                    if (distance <= 1000) {
                        table[i][j] = true;
                    }
                }
            }

            for (int middle = 0; middle < n + 2; middle++) {
                for (int from = 0; from < n + 2; from++) {
                    if (middle == from) {
                        continue;
                    }
                    for (int to = from + 1; to < n + 2; to++) {
                        if (middle == to || from == to) {
                            continue;
                        }
                        if (table[from][middle] && table[middle][to]) {
                            table[from][to] = true;
                            table[to][from] = true;
                        }
                    }
                }
            }

            String feeling = "sad";
            if (table[0][n + 1]) {
                feeling = "happy";
            }

            stringBuilder.append(feeling)
                    .append("\n");
        }
        System.out.println(stringBuilder);
    }

    private static int getDistance(int previousX, int previousY, int currentX, int currentY) {
        return Math.abs(currentX - previousX) + Math.abs(currentY - previousY);
    }
}
