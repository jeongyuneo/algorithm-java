package Baekjoon;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.HashSet;
import java.util.Set;

public class BOJ_1972 {

    private static final Set<String> WORDS = new HashSet<>();
    private static final String END = "*";

    public static void main(String[] args) throws IOException {
        BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder answer = new StringBuilder();
        while (true) {
            String input = bufferedReader.readLine();
            if (input.equals(END)) {
                break;
            }
            if (input.length() < 3) {
                answer.append(input).append(" is surprising.").append("\n");
                continue;
            }
            if (isSurprising(input)) {
                answer.append(input).append(" is surprising.").append("\n");
            } else {
                answer.append(input).append(" is NOT surprising.").append("\n");
            }
            WORDS.clear();
        }
        System.out.println(answer);
    }

    private static boolean isSurprising(String input) {
        int n = input.length();
        for (int i = 0; i <= n - 2; i++) {
            for (int j = 0; j < n - i - 1; j++) {
                String word = String.format("%s%s", input.charAt(j), input.charAt(j + i + 1));
                if (WORDS.contains(word)) {
                    return false;
                }
                WORDS.add(word);
            }
            WORDS.clear();
        }
        return true;
    }
}
