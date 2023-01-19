package Baekjoon;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class BOJ_1062 {

    private static final String PREFIX_AND_SUFFIX = "acnit";

    private static int[] words;
    private static int k;
    private static int answer;
    private static int prefixAndSuffix;

    public static void main(String[] args) throws IOException {
        BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer stringTokenizer = new StringTokenizer(bufferedReader.readLine());
        int n = Integer.parseInt(stringTokenizer.nextToken());
        k = Integer.parseInt(stringTokenizer.nextToken()) - 5;
        if (k >= 0) {
            for (int i = 0; i < 5; i++) {
                prefixAndSuffix |= 1 << PREFIX_AND_SUFFIX.charAt(i) - 'a';
            }
            words = new int[n];
            for (int i = 0; i < n; i++) {
                String word = bufferedReader.readLine();
                int wordBit = 0;
                for (int j = 4, length = word.length() - 4; j < length; j++) {
                    int alphabet = 1 << word.charAt(j) - 'a';
                    if ((prefixAndSuffix & alphabet) == 0) {
                        wordBit |= alphabet;
                    }
                }
                words[i] = wordBit;
            }
            selectLearningAlphabets(0, 0, 0);
        }
        System.out.println(answer);
    }

    private static void selectLearningAlphabets(int cnt, int start, int learningAlphabets) {
        if (cnt == k) {
            updateMaxLearnableWords(learningAlphabets);
            return;
        }

        for (int i = start; i < 26; i++) {
            int alphabet = 1 << i;
            if ((prefixAndSuffix & alphabet) == 0) {
                selectLearningAlphabets(cnt + 1, i + 1, learningAlphabets | alphabet);
            }
        }
    }

    private static void updateMaxLearnableWords(int learningAlphabets) {
        int learnableWords = 0;
        for (int word : words) {
            if ((word & learningAlphabets) == word) {
                learnableWords++;
            }
        }
        answer = Math.max(answer, learnableWords);
    }
}
