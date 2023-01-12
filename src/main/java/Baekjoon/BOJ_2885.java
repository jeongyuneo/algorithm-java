package Baekjoon;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class BOJ_2885 {

    private static final StringBuilder ANSWER = new StringBuilder();
    private static final int MAX_SIZE = 1000000;

    public static void main(String[] args) throws IOException {
        BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(System.in));
        int k = Integer.parseInt(bufferedReader.readLine());
        split(k, getChocolateSize(k));
        System.out.println(ANSWER);
    }

    private static int getChocolateSize(int k) {
        int size = 1;
        for (int i = 0; i < MAX_SIZE; i++) {
            int chocolate = size << i;
            if (chocolate >= k) {
                ANSWER.append(chocolate).append(" ");
                return chocolate;
            }
        }
        return 0;
    }

    private static void split(int k, int size) {
        if (k % size == 0) {
            ANSWER.append(0);
            return;
        }
        int count = 1;
        while ((size >>= 1) > 0) {
            if (k % size == 0) {
                ANSWER.append(count);
                return;
            }
            count++;
        }
    }
}
