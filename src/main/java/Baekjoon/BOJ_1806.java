package Baekjoon;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class BOJ_1806 {

    public static void main(String[] args) throws IOException {
        BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer stringTokenizer = new StringTokenizer(bufferedReader.readLine());
        int n = Integer.parseInt(stringTokenizer.nextToken());
        int s = Integer.parseInt(stringTokenizer.nextToken());
        stringTokenizer = new StringTokenizer(bufferedReader.readLine());
        int[] numbers = new int[n];
        for (int i = 0; i < n; i++) {
            numbers[i] = Integer.parseInt(stringTokenizer.nextToken());
        }
        long sum = numbers[0];
        int start = 0;
        int end = 0;
        int length = 1;
        int minLength = n + 1;
        while (true) {
            if (sum >= s) {
                minLength = Math.min(minLength, length);
                sum -= numbers[start++];
                length--;
            } else {
                if (end == n - 1) {
                    break;
                }
                sum += numbers[++end];
                length++;
            }
        }
        if (minLength == n + 1) {
            minLength = 0;
        }
        System.out.println(minLength);
    }
}
