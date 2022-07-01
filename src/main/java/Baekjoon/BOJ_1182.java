package Baekjoon;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class BOJ_1182 {

    private static int n;
    private static int s;
    private static int answer;

    public static void main(String[] args) throws IOException {
        BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer stringTokenizer = new StringTokenizer(bufferedReader.readLine());
        n = Integer.parseInt(stringTokenizer.nextToken());
        s = Integer.parseInt(stringTokenizer.nextToken());
        int[] numbers = new int[n];
        stringTokenizer = new StringTokenizer(bufferedReader.readLine());
        for (int i = 0; i < n; i++) {
            numbers[i] = Integer.parseInt(stringTokenizer.nextToken());
        }
        Arrays.sort(numbers);

        for (int i = 0; i < numbers.length; i++) {
            getAnswer(numbers, 1, i + 1, numbers[i]);
        }
        System.out.println(answer);
    }

    private static void getAnswer(int[] numbers, int cnt, int start, int sum) {
        if (sum == s) {
            answer++;
        }

        if (cnt == n) {
            return;
        }

        for (int i = start; i < n; i++) {
            getAnswer(numbers, cnt + 1, i + 1, sum + numbers[i]);
        }
    }
}
