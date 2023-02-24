package Baekjoon;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class BOJ_1904 {

    public static void main(String[] args) throws IOException {
        BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(System.in));
        int n = Integer.parseInt(bufferedReader.readLine());
        int[] cases = new int[n + 1];
        cases[1] = 1;
        if (n > 1) {
            cases[2] = 2;
            for (int i = 3; i <= n; i++) {
                cases[i] = (cases[i - 1] + cases[i - 2]) % 15746;
            }
        }
        System.out.println(cases[n]);
    }
}
