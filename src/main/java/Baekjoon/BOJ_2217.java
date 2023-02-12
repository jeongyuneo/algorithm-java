package Baekjoon;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;

public class BOJ_2217 {

    public static void main(String[] args) throws IOException {
        BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(System.in));
        int n = Integer.parseInt(bufferedReader.readLine());
        int[] weights = new int[n];
        for (int i = 0; i < n; i++) {
            weights[i] = Integer.parseInt(bufferedReader.readLine());
        }
        Arrays.sort(weights);
        int maxWeight = 0;
        for (int i = 0; i < n; i++) {
            maxWeight = Math.max(maxWeight, weights[i] * (n - i));
        }
        System.out.println(maxWeight);
    }
}
