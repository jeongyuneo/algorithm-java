package Baekjoon;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class BOJ_2229 {

    public static void main(String[] args) throws IOException {
        BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(System.in));
        int n = Integer.parseInt(bufferedReader.readLine());
        int[] students = new int[n + 1];
        StringTokenizer stringTokenizer = new StringTokenizer(bufferedReader.readLine());
        int[] sums = new int[n + 1];
        for (int i = 1; i <= n; i++) {
            students[i] = Integer.parseInt(stringTokenizer.nextToken());
            int min = Integer.MAX_VALUE;
            int max = Integer.MIN_VALUE;
            for (int j = i; j > 0; j--) {
                min = Math.min(min, students[j]);
                max = Math.max(max, students[j]);
                sums[i] = Math.max(sums[i], sums[j - 1] + max - min);
            }
        }
        System.out.println(sums[n]);
    }
}
