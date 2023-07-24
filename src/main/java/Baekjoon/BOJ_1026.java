package Baekjoon;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;

public class BOJ_1026 {

    private static final String DELIMITER = " ";

    public static void main(String[] args) throws IOException {
        BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(System.in));
        int n = Integer.parseInt(bufferedReader.readLine());
        String[] inputA = bufferedReader.readLine().split(DELIMITER);
        String[] inputB = bufferedReader.readLine().split(DELIMITER);
        int[] A = new int[n];
        int[] B = new int[n];
        for (int i = 0; i < n; i++) {
            A[i] = Integer.parseInt(inputA[i]);
            B[i] = Integer.parseInt(inputB[i]);
        }
        Arrays.sort(A);
        Arrays.sort(B);
        int S = 0;
        for (int i = 0; i < n; i++) {
            S += A[i] * B[n - 1 - i];
        }
        System.out.println(S);
    }
}
