package Baekjoon;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class BOJ_2003 {

    public static void main(String[] args) throws IOException {
        BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer stringTokenizer = new StringTokenizer(bufferedReader.readLine());
        int n = Integer.parseInt(stringTokenizer.nextToken());
        int m = Integer.parseInt(stringTokenizer.nextToken());
        stringTokenizer = new StringTokenizer(bufferedReader.readLine());
        int[] numbers = new int[n];
        for (int i = 0; i < n; i++) {
            numbers[i] = Integer.parseInt(stringTokenizer.nextToken());
        }

        int start = 0;
        int end = 0;
        int sum = numbers[start];
        int answer = 0;
        while (true) {
            if (sum == m) {
                answer++;
                if (end == n - 1) {
                    break;
                }
                sum += numbers[++end];
                sum -= numbers[start++];
            } else if (sum < m) {
                if (end == n - 1) {
                    break;
                }
                sum += numbers[++end];
            } else {
                sum -= numbers[start++];
            }
        }
        System.out.println(answer);
    }
}
