package Baekjoon;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class BOJ_12738 {

    public static void main(String[] args) throws IOException {
        BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(System.in));
        int n = Integer.parseInt(bufferedReader.readLine());
        int[] numbers = new int[n];
        StringTokenizer stringTokenizer = new StringTokenizer(bufferedReader.readLine());
        for (int i = 0; i < n; i++) {
            numbers[i] = Integer.parseInt(stringTokenizer.nextToken());
        }
        int maxLength = 1;
        int[] lengths = new int[n];
        lengths[0] = numbers[0];
        for (int i = 1; i < n; i++) {
            int idx = getIdx(maxLength, lengths, numbers[i]);
            if (idx == maxLength) {
                lengths[maxLength++] = numbers[i];
            } else if (idx < maxLength) {
                lengths[idx] = numbers[i];
            }
        }
        System.out.println(maxLength);
    }

    private static int getIdx(int maxLength, int[] lengths, int number) {
        int start = 0;
        int end = maxLength;
        while (start < end) {
            int mid = (start + end) / 2;
            if (lengths[mid] < number) {
                start = mid + 1;
            } else {
                end = mid;
            }
        }
        return start;
    }
}
