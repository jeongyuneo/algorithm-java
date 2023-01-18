package Baekjoon;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class BOJ_10974 {

    private static final StringBuilder ANSWER = new StringBuilder();

    private static int[] selectedNumbers;
    private static int n;

    public static void main(String[] args) throws IOException {
        BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(System.in));
        n = Integer.parseInt(bufferedReader.readLine());
        selectedNumbers = new int[n];
        permutation(0, 0);
        System.out.println(ANSWER);
    }

    private static void permutation(int cnt, int flag) {
        if (cnt == n) {
            for (int selectedNumber : selectedNumbers) {
                ANSWER.append(selectedNumber).append(" ");
            }
            ANSWER.append("\n");
            return;
        }

        for (int i = 0; i < n; i++) {
            if ((flag & 1 << i) == 0) {
                selectedNumbers[cnt] = i + 1;
                permutation(cnt + 1, flag | 1 << i);
            }
        }
    }
}
