package Baekjoon;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class BOJ_10025 {

    private static final int MAX = 1_000_000;

    public static void main(String[] args) throws IOException {
        BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer stringTokenizer = new StringTokenizer(bufferedReader.readLine());
        int n = Integer.parseInt(stringTokenizer.nextToken());
        int k = Integer.parseInt(stringTokenizer.nextToken());
        int[] zoo = new int[MAX + 1];
        for (int i = 0; i < n; i++) {
            stringTokenizer = new StringTokenizer(bufferedReader.readLine());
            int g = Integer.parseInt(stringTokenizer.nextToken());
            int x = Integer.parseInt(stringTokenizer.nextToken());
            zoo[x] = g;
        }
        int totalIce = 0;
        for (int i = 0; i <= k * 2 && i <= MAX; i++) {
            totalIce += zoo[i];
        }
        int max = totalIce;
        for (int i = k + 1; i <= MAX - k; i++) {
            totalIce -= zoo[i - k - 1];
            totalIce += zoo[i + k];
            max = Math.max(max, totalIce);
        }
        System.out.println(max);
    }
}
