package Baekjoon;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.Arrays;
import java.util.Queue;

public class BOJ_6443 {

    private static final Queue<String> WORDS = new ArrayDeque<>();
    private static final int[] COUNTS = new int[26];

    private static int length;

    public static void main(String[] args) throws IOException {
        BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(System.in));
        int n = Integer.parseInt(bufferedReader.readLine());
        StringBuilder answer = new StringBuilder();
        while (n-- > 0) {
            char[] input = bufferedReader.readLine().toCharArray();
            length = input.length;
            Arrays.fill(COUNTS, 0);
            Arrays.sort(input);
            for (int i = 0; i < length; i++) {
                COUNTS[input[i] - 'a']++;
            }
            createAnagram("");
            for (String word : WORDS) {
                answer.append(word).append("\n");
            }
            WORDS.clear();
        }
        System.out.println(answer);
    }

    private static void createAnagram(String word) {
        if (word.length() == length) {
            WORDS.add(word);
            return;
        }
        for (int i = 0; i < 26; i++) {
            if (COUNTS[i] > 0) {
                COUNTS[i]--;
                createAnagram(word + (char) (i + 'a'));
                COUNTS[i]++;
            }
        }
    }
}
