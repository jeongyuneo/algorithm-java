package Baekjoon;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class BOJ_2230 {

    public static void main(String[] args) throws IOException {
        BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer stringTokenizer = new StringTokenizer(bufferedReader.readLine());
        int n = Integer.parseInt(stringTokenizer.nextToken());
        int m = Integer.parseInt(stringTokenizer.nextToken());
        int[] numbers = new int[n];
        for (int i = 0; i < n; i++) {
            numbers[i] = Integer.parseInt(bufferedReader.readLine());
        }
        Arrays.sort(numbers);
        int start = 0;
        int end = 1;
        int minGap = numbers[n - 1] - numbers[0] + 1;
        while (start < n && end < n) {
            int gap = numbers[end] - numbers[start];
            if (gap >= m) {
                if (gap < minGap) {
                    minGap = gap;
                }
                start++;
            } else {
                end++;
            }
        }
        System.out.println(minGap);
    }
}
