package Baekjoon;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;
import java.util.regex.Pattern;

public class BOJ_1062 {

    private static final String PREFIX_AND_SUFFIX = "[acnit]";
    private static final String EMPTY = "";
    private static final Pattern PATTERN = Pattern.compile(PREFIX_AND_SUFFIX);

    private static String[] words;
    private static int k;
    private static int answer;

    public static void main(String[] args) throws IOException {
        BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer stringTokenizer = new StringTokenizer(bufferedReader.readLine());
        int n = Integer.parseInt(stringTokenizer.nextToken());
        k = Integer.parseInt(stringTokenizer.nextToken()) - 5;
        answer = 0;
        if (k >= 0) {
            words = new String[n];
            for (int i = 0; i < n; i++) {
                words[i] = bufferedReader.readLine().replaceAll(PREFIX_AND_SUFFIX, EMPTY);
            }
            selectLearningAlphabets(0, 0, 0);
        }
        System.out.println(answer);
    }

    private static void selectLearningAlphabets(int cnt, int start, int flag) {
        if (cnt == k) {
            updateMaxLearnableWords(flag);
            return;
        }

        for (int i = start; i < 26; i++) {
            String word = String.valueOf((char) (i + 'a'));
            if (!PATTERN.matcher(word).matches()) {
                selectLearningAlphabets(cnt + 1, i + 1, flag | 1 << i);
            }
        }
    }

    private static void updateMaxLearnableWords(int flag) {
        int learnableWords = 0;
        for (String word : words) {
            int wordBit = 0;
            for (int i = 0; i < word.length(); i++) {
                wordBit |= 1 << word.charAt(i) - 'a';
            }
            if ((wordBit & flag) == wordBit) {
                learnableWords++;
            }
        }
        answer = Math.max(answer, learnableWords);
    }
}
