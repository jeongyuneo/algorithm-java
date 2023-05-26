package Baekjoon;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class BOJ_2294 {

    public static void main(String[] args) throws IOException {
        BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer stringTokenizer = new StringTokenizer(bufferedReader.readLine());
        int n = Integer.parseInt(stringTokenizer.nextToken());
        int k = Integer.parseInt(stringTokenizer.nextToken());
        int[] coins = new int[n];
        for (int i = 0; i < n; i++) {
            coins[i] = Integer.parseInt(bufferedReader.readLine());
        }
        int[] cases = new int[k + 1];
        Arrays.fill(cases, 100001);
        cases[0] = 0;
        for (int i = 0; i < n; i++) {
            for (int j = coins[i]; j <= k; j++) {
                cases[j] = Math.min(cases[j], cases[j - coins[i]] + 1);
            }
        }
        if (cases[k] == 100001) {
            cases[k] = -1;
        }
        System.out.println(cases[k]);
    }
}
