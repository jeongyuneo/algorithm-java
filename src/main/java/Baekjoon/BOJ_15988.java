package Baekjoon;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class BOJ_15988 {

    public static void main(String[] args) throws IOException {
        BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(System.in));
        int n = Integer.parseInt(bufferedReader.readLine());
        long[] sums = new long[1000000];
        sums[0] = 1;
        sums[1] = 2;
        sums[2] = 4;
        for (int i = 3; i < 1000000; i++) {
            sums[i] = (sums[i - 1] + sums[i - 2] + sums[i - 3]) % 1000000009;
        }
        StringBuilder answer = new StringBuilder();
        while (n-- > 0) {
            answer.append(sums[Integer.parseInt(bufferedReader.readLine()) - 1]).append("\n");
        }
        System.out.println(answer);
    }
}
