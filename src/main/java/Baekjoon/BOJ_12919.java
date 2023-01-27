package Baekjoon;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class BOJ_12919 {

    public static void main(String[] args) throws IOException {
        BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(System.in));
        String s = bufferedReader.readLine();
        String t = bufferedReader.readLine();
        System.out.println(getAnswer(s, t));
    }

    private static int getAnswer(String s, String t) {
        if (s.length() == t.length()) {
            if (s.equals(t)) {
                return 1;
            }
            return 0;
        }

        int answer = 0;
        if (t.charAt(0) == 'B') {
            answer += getAnswer(s, new StringBuilder(t.substring(1)).reverse().toString());
        }
        if (t.charAt(t.length() - 1) == 'A') {
            answer += getAnswer(s, t.substring(0, t.length() - 1));
        }

        if (answer > 0) {
            return 1;
        } else {
            return 0;
        }
    }
}
