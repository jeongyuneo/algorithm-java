package Baekjoon;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class BOJ_2293 {

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
        cases[0] = 1;
        for (int coin : coins) {
            for (int i = 1; i <= k; i++) {
                if (i >= coin) {
                    cases[i] += cases[i - coin];
                }
            }
        }
        System.out.println(cases[k]);
    }
}
