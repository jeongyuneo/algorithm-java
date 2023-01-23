package Baekjoon;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class BOJ_11055 {

    public static void main(String[] args) throws IOException {
        BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(System.in));
        int n = Integer.parseInt(bufferedReader.readLine());
        int[] numbers = new int[n];
        StringTokenizer stringTokenizer = new StringTokenizer(bufferedReader.readLine());
        for (int i = 0; i < n; i++) {
            numbers[i] = Integer.parseInt(stringTokenizer.nextToken());
        }
        int[] sums = new int[n];
        int answer = 0;
        for (int i = 0; i < n; i++) {
            sums[i] = numbers[i];
            for (int j = i - 1; j >= 0; j--) {
                if (numbers[i] > numbers[j] && sums[i] < sums[j] + numbers[i]) {
                    sums[i] = sums[j] + numbers[i];
                }
            }
            answer = Math.max(answer, sums[i]);
        }
        System.out.println(answer);
    }
}
