package Baekjoon;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class BOJ_9095 {

    public static void main(String[] args) throws IOException {
        BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(System.in));
        int n = Integer.parseInt(bufferedReader.readLine());
        int[] sumWays = getSumWays();
        StringBuilder answer = new StringBuilder();
        while (n-- > 0) {
            answer.append(sumWays[Integer.parseInt(bufferedReader.readLine()) - 1]).append("\n");
        }
        System.out.println(answer);
    }

    private static int[] getSumWays() {
        int[] sums = new int[10];
        sums[0] = 1;
        sums[1] = 2;
        sums[2] = 4;
        for (int i = 3; i < 10; i++) {
            sums[i] = sums[i - 1] + sums[i - 2] + sums[i - 3];
        }
        return sums;
    }
}
