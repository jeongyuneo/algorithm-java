package Baekjoon;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class BOJ_20922 {

    public static void main(String[] args) throws IOException {
        BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer stringTokenizer = new StringTokenizer(bufferedReader.readLine());
        int n = Integer.parseInt(stringTokenizer.nextToken());
        int k = Integer.parseInt(stringTokenizer.nextToken());
        int[] numbers = new int[n];
        stringTokenizer = new StringTokenizer(bufferedReader.readLine());
        for (int i = 0; i < n; i++) {
            numbers[i] = Integer.parseInt(stringTokenizer.nextToken());
        }

        int[] counts = new int[100001];
        int start = 0;
        int end = 0;
        counts[numbers[start]]++;
        int length = 1;
        int maxLength = 0;
        while (start < n && end < n) {
            if (++end < n && ++counts[numbers[end]] <= k) {
                length++;
            } else {
                maxLength = Math.max(maxLength, length);
                while (start < n && end < n && numbers[start] != numbers[end]) {
                    counts[numbers[start++]]--;
                    length--;
                }
                counts[numbers[start++]]--;
            }
        }
        System.out.println(maxLength);
    }
}
