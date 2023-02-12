package Baekjoon;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class BOJ_16208 {

    public static void main(String[] args) throws IOException {
        BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(System.in));
        int n = Integer.parseInt(bufferedReader.readLine());
        int[] sticks = new int[n];
        long length = 0;
        StringTokenizer stringTokenizer = new StringTokenizer(bufferedReader.readLine());
        for (int i = 0; i < n; i++) {
            sticks[i] = Integer.parseInt(stringTokenizer.nextToken());
            length += sticks[i];
        }
        Arrays.sort(sticks);
        long cost = 0;
        for (int i = 0; i < n; i++) {
            length -= sticks[i];
            cost += length * sticks[i];
        }
        System.out.println(cost);
    }
}
