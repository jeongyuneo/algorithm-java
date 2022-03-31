package Baekjoon;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class BOJ_1463 {

    public static void main(String[] args) throws IOException {
        BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(System.in));
        int n = Integer.parseInt(bufferedReader.readLine());
        int[] table = new int[n + 1];
        for (int i = 2; i <= n; i++) {
            table[i] = table[i - 1] + 1;
            if (i % 2 == 0 && table[i / 2] + 1 < table[i]) {
                table[i] = table[i / 2] + 1;
            }
            if (i >= 3 && i % 3 == 0 && table[i / 3] + 1 < table[i]) {
                table[i] = table[i / 3] + 1;
            }
        }
        System.out.println(table[n]);
    }
}
