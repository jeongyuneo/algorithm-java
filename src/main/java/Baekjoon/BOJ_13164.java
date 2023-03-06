package Baekjoon;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class BOJ_13164 {

    public static void main(String[] args) throws IOException {
        BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer stringTokenizer = new StringTokenizer(bufferedReader.readLine());
        int n = Integer.parseInt(stringTokenizer.nextToken());
        int k = Integer.parseInt(stringTokenizer.nextToken());
        int[] gaps = new int[n - 1];
        stringTokenizer = new StringTokenizer(bufferedReader.readLine());
        int previous = Integer.parseInt(stringTokenizer.nextToken());
        for (int i = 0; i < n - 1; i++) {
            int current = Integer.parseInt(stringTokenizer.nextToken());
            gaps[i] = current - previous;
            previous = current;
        }
        Arrays.sort(gaps);
        int cost = 0;
        for (int i = 0; i < n - k; i++) {
            cost += gaps[i];
        }
        System.out.println(cost);
    }
}
