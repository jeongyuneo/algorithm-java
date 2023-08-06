package Baekjoon;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class BOJ_1106 {

    private static final int MAX = 100;

    public static void main(String[] args) throws IOException {
        BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer stringTokenizer = new StringTokenizer(bufferedReader.readLine());
        int c = Integer.parseInt(stringTokenizer.nextToken());
        int n = Integer.parseInt(stringTokenizer.nextToken());
        int[] costs = new int[c + MAX];
        Arrays.fill(costs, Integer.MAX_VALUE);
        costs[0] = 0;
        for (int i = 0; i < n; i++) {
            stringTokenizer = new StringTokenizer(bufferedReader.readLine());
            int cost = Integer.parseInt(stringTokenizer.nextToken());
            int people = Integer.parseInt(stringTokenizer.nextToken());
            for (int j = people; j < c + MAX; j++) {
                if (costs[j - people] == Integer.MAX_VALUE) {
                    continue;
                }
                costs[j] = Math.min(costs[j], costs[j - people] + cost);
            }
        }
        int result = Integer.MAX_VALUE;
        for (int i = c; i < c + MAX; i++) {
            result = Math.min(result, costs[i]);
        }
        System.out.println(result);
    }
}
