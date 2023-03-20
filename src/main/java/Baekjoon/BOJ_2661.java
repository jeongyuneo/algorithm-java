package Baekjoon;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class BOJ_2661 {

    private static int n;

    public static void main(String[] args) throws IOException {
        BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(System.in));
        n = Integer.parseInt(bufferedReader.readLine());
        findGoodSequence("");
    }

    private static void findGoodSequence(String sequence) {
        if (n == sequence.length()) {
            System.out.println(sequence);
            System.exit(0);
        }
        for (int number = 1; number <= 3; number++) {
            String nextSequence = sequence + number;
            if (isGoodSequence(nextSequence)) {
                findGoodSequence(nextSequence);
            }
        }
    }

    private static boolean isGoodSequence(String sequence) {
        int length = sequence.length();
        for (int i = 1; i <= length / 2; i++) {
            if (sequence.substring(length - i * 2, length - i).equals(sequence.substring(length - i, length))) {
                return false;
            }
        }
        return true;
    }
}
