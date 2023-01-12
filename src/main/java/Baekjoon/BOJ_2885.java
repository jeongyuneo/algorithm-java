package Baekjoon;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class BOJ_2885 {

    private static final StringBuilder ANSWER = new StringBuilder();

    public static void main(String[] args) throws IOException {
        BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(System.in));
        int k = Integer.parseInt(bufferedReader.readLine());
        split(k, getChocolateSize(k));
        System.out.println(ANSWER);
    }

    private static int getChocolateSize(int k) {
        int chocolate = 1;
        while (true) {
            if (chocolate >= k) {
                ANSWER.append(chocolate).append(" ");
                return chocolate;
            }
            chocolate <<= 1;
        }
    }

    private static void split(int k, int size) {
        int count = 0;
        while (k % (size >> count) != 0) {
            count++;
        }
        ANSWER.append(count);
    }
}
