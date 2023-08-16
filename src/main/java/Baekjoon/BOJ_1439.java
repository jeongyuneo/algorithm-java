package Baekjoon;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class BOJ_1439 {

    public static void main(String[] args) throws IOException {
        BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(System.in));
        String s = bufferedReader.readLine();
        int idx = 1;
        char word = s.charAt(0);
        int[] counts = new int[2];
        while (true) {
            while (idx < s.length() && word == s.charAt(idx)) {
                idx++;
            }
            if (idx == s.length()) {
                break;
            }
            counts[s.charAt(idx) - '0']++;
            word = s.charAt(idx);
        }
        System.out.println(Math.max(counts[0], counts[1]));
    }
}
