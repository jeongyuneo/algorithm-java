package Baekjoon;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.HashSet;
import java.util.Set;

public class BOJ_1342 {

    private static final Set<Character> ALPHABETS = new HashSet<>();
    private static final int[] ALPHABET_COUNTS = new int[27];

    private static int length;
    private static int count;

    public static void main(String[] args) throws IOException {
        BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(System.in));
        String input = bufferedReader.readLine();
        length = input.length();
        for (int i = 0; i < length; i++) {
            char alphabet = input.charAt(i);
            ALPHABETS.add(alphabet);
            ALPHABET_COUNTS[alphabet - 'a']++;
        }
        findNumberOfLucky(0, ' ');
        System.out.println(count);
    }

    private static void findNumberOfLucky(int cnt, char previous) {
        if (cnt == length) {
            count++;
            return;
        }
        for (char alphabet : ALPHABETS) {
            int index = alphabet - 'a';
            if (previous != alphabet && ALPHABET_COUNTS[index] > 0) {
                ALPHABET_COUNTS[index]--;
                findNumberOfLucky(cnt + 1, alphabet);
                ALPHABET_COUNTS[index]++;
            }
        }
    }
}
