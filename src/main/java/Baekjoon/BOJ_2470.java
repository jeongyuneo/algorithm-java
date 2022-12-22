package Baekjoon;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class BOJ_2470 {

    public static void main(String[] args) throws IOException {
        BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(System.in));
        int n = Integer.parseInt(bufferedReader.readLine());
        int[] numbers = new int[n];
        StringTokenizer stringTokenizer = new StringTokenizer(bufferedReader.readLine());
        for (int i = 0; i < n; i++) {
            numbers[i] = Integer.parseInt(stringTokenizer.nextToken());
        }
        Arrays.sort(numbers);
        int start = 0;
        int end = n - 1;
        int[] answer = new int[2];
        int minGap = Integer.MAX_VALUE;
        while (start < end) {
            int gap = Math.abs(numbers[start] + numbers[end]);
            if (gap < minGap) {
                answer[0] = numbers[start];
                answer[1] = numbers[end];
                minGap = gap;
            }

            if (Math.abs(numbers[start] + numbers[end - 1]) > Math.abs(numbers[start + 1] + numbers[end])) {
                start++;
            } else {
                end--;
            }
        }
        System.out.println(answer[0] + " " + answer[1]);
    }
}
