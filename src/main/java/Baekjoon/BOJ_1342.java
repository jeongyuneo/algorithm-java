package Baekjoon;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class BOJ_1342 {

    private static final int MAX_VALUE = 1000000;

    public static void main(String[] args) throws IOException {
        BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(System.in));
        int testCase = Integer.parseInt(bufferedReader.readLine());
        StringBuilder answer = new StringBuilder();
        while (testCase-- > 0) {
            StringTokenizer stringTokenizer = new StringTokenizer(bufferedReader.readLine());
            int n = Integer.parseInt(stringTokenizer.nextToken());
            int m = Integer.parseInt(stringTokenizer.nextToken());
            int[][] rooms = new int[n][n];
            for (int i = 0; i < n; i++) {
                for (int j = 0; j < n; j++) {
                    if (i != j) {
                        rooms[i][j] = MAX_VALUE;
                    }
                }
            }
            for (int i = 0; i < m; i++) {
                stringTokenizer = new StringTokenizer(bufferedReader.readLine());
                int from = Integer.parseInt(stringTokenizer.nextToken()) - 1;
                int to = Integer.parseInt(stringTokenizer.nextToken()) - 1;
                int distance = Integer.parseInt(stringTokenizer.nextToken());
                rooms[from][to] = distance;
                rooms[to][from] = distance;
            }
            for (int pass = 0; pass < n; pass++) {
                for (int start = 0; start < n; start++) {
                    for (int end = 0; end < n; end++) {
                        rooms[start][end] = Math.min(rooms[start][end], rooms[start][pass] + rooms[pass][end]);
                    }
                }
            }
            int k = Integer.parseInt(bufferedReader.readLine());
            stringTokenizer = new StringTokenizer(bufferedReader.readLine());
            int[] friends = new int[k];
            for (int i = 0; i < k; i++) {
                friends[i] = Integer.parseInt(stringTokenizer.nextToken()) - 1;
            }
            int minDistance = Integer.MAX_VALUE;
            int meetingRoom = n;
            for (int i = 0; i < n; i++) {
                int totalDistance = 0;
                for (int j = 0; j < k; j++) {
                    totalDistance += rooms[i][friends[j]];
                }
                if (minDistance > totalDistance) {
                    minDistance = totalDistance;
                    meetingRoom = i + 1;
                }
            }
            answer.append(meetingRoom).append("\n");
        }
        System.out.println(answer);
    }
}
