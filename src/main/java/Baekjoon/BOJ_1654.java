package Baekjoon;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class BOJ_1654 {

    public static void main(String[] args) throws IOException {
        BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer stringTokenizer = new StringTokenizer(bufferedReader.readLine());
        int n = Integer.parseInt(stringTokenizer.nextToken());
        int k = Integer.parseInt(stringTokenizer.nextToken());
        int[] lines = new int[n];
        int maxLength = 0;
        for (int i = 0; i < n; i++) {
            int line = Integer.parseInt(bufferedReader.readLine());
            lines[i] = line;
            if (line > maxLength) {
                maxLength = line;
            }
        }
        Arrays.sort(lines);
        System.out.println(search(lines, 0, maxLength, k));
    }

    private static long search(int[] lines, long minLength, long maxLength, int k) {
        long maxLine = 0;
        while (minLength <= maxLength) {
            long mid = (minLength + maxLength) / 2;
            if (cut(lines, mid) < k) {
                maxLength = mid - 1;
            } else {
                minLength = mid + 1;
                maxLine = mid;
            }
        }
        return maxLine;
    }

    private static int cut(int[] lines, long mid) {
        if (mid == 0) {
            return 10001;
        }
        int count = 0;
        for (int line : lines) {
            count += line / mid;
        }
        return count;
    }
}
