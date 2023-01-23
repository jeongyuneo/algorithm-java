package Baekjoon;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class BOJ_1912 {

    public static void main(String[] args) throws IOException {
        BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(System.in));
        int n = Integer.parseInt(bufferedReader.readLine());
        int[] numbers = new int[n];
        StringTokenizer stringTokenizer = new StringTokenizer(bufferedReader.readLine());
        for (int i = 0; i < n; i++) {
            numbers[i] = Integer.parseInt(stringTokenizer.nextToken());
        }
        int[] sums = new int[n];
        int answer = numbers[0];
        sums[0] = numbers[0];
        for (int i = 1; i < n; i++) {
            sums[i] = Math.max(sums[i - 1] + numbers[i], numbers[i]);
            answer = Math.max(answer, sums[i]);
        }
        System.out.println(answer);
    }
}
