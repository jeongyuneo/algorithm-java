package Baekjoon;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;

public class BOJ_2012 {

    public static void main(String[] args) throws IOException {
        BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(System.in));
        int n = Integer.parseInt(bufferedReader.readLine());
        int[] grades = new int[n];
        for (int i = 0; i < n; i++) {
            grades[i] = Integer.parseInt(bufferedReader.readLine()) - 1;
        }
        Arrays.sort(grades);
        long sum = 0;
        for (int i = 0; i < n; i++) {
            sum += Math.abs(i - grades[i]);
        }
        System.out.println(sum);
    }
}
