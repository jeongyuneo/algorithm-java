package Baekjoon;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class BOJ_23630 {

    public static void main(String[] args) throws IOException {
        BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(System.in));
        int n = Integer.parseInt(bufferedReader.readLine());
        int[] numbers = new int[n];
        StringTokenizer stringTokenizer = new StringTokenizer(bufferedReader.readLine());
        for (int i = 0; i < n; i++) {
            numbers[i] = Integer.parseInt(stringTokenizer.nextToken());
        }
        int[] bitCountsOfOne = new int[32];
        for (int number : numbers) {
            int idx = 0;
            while (number != 0) {
                bitCountsOfOne[idx++] += number % 2;
                number >>= 1;
            }
        }
        Arrays.sort(bitCountsOfOne);
        System.out.println(bitCountsOfOne[31]);
    }
}
