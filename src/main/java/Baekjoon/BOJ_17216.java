package Baekjoon;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class BOJ_17216 {

    public static void main(String[] args) throws IOException {
        BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(System.in));
        int n = Integer.parseInt(bufferedReader.readLine());
        int[] numbers = new int[n];
        StringTokenizer stringTokenizer = new StringTokenizer(bufferedReader.readLine());
        for (int i = 0; i < n; i++) {
            numbers[i] = Integer.parseInt(stringTokenizer.nextToken());
        }
        int[] sums = new int[n];
        Arrays.fill(sums, Integer.MAX_VALUE);
        sums[0] = numbers[0];
        int max = 0;
        for (int i = 1; i < n; i++) {
            sums[i] = numbers[i];
            for (int j = 0; j < i; j++) {
                if (numbers[j] > numbers[i] && sums[i] < sums[j] + numbers[i]) {
                    sums[i] = sums[j] + numbers[i];
                }
            }
            max = Math.max(max, sums[i]);
        }
        System.out.println(max);
    }
}
