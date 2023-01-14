package Baekjoon;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class BOJ_1026 {

    public static void main(String[] args) throws IOException {
        BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(System.in));
        int n = Integer.parseInt(bufferedReader.readLine());
        int[] A = new int[n];
        int[] B = new int[n];
        StringTokenizer inputA = new StringTokenizer(bufferedReader.readLine());
        StringTokenizer inputB = new StringTokenizer(bufferedReader.readLine());
        for (int i = 0; i < n; i++) {
            A[i] = Integer.parseInt(inputA.nextToken());
            B[i] = Integer.parseInt(inputB.nextToken());
        }
        Arrays.sort(A);
        Arrays.sort(B);
        int S = 0;
        for (int i = 0; i < n; i++) {
            S += A[i] * B[n - i - 1];
        }
        System.out.println(S);
    }
}
