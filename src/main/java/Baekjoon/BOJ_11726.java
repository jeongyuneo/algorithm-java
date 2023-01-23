package Baekjoon;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class BOJ_11726 {

    public static void main(String[] args) throws IOException {
        BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(System.in));
        int n = Integer.parseInt(bufferedReader.readLine());
        long[] waysToFill = new long[n];
        waysToFill[0] = 1;
        if (n > 1) {
            waysToFill[1] = 2;
            for (int i = 2; i < n; i++) {
                waysToFill[i] = (waysToFill[i - 1] + waysToFill[i - 2]) % 10007;
            }
        }
        System.out.println(waysToFill[n - 1]);
    }
}
