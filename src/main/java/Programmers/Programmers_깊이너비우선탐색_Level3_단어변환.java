package Programmers;

import java.util.Arrays;
import java.util.List;

public class Programmers_깊이너비우선탐색_Level3_단어변환 {

    public static void main(String[] args) {
        System.out.println(solution("hit", "cog", new String[]{"hot", "dot", "dog", "lot", "log", "cog"}));
        System.out.println(solution("hit", "cog", new String[]{"hot", "dot", "dog", "lot", "log"}));
    }

    private static List<String> wordList;
    private static boolean[] isChecked;
    private static int length;
    private static int min;

    public static int solution(String begin, String target, String[] words) {
        length = begin.length();
        wordList = Arrays.asList(words);
        isChecked = new boolean[words.length + 1];
        min = Integer.MAX_VALUE;
        change(begin, target, 0);
        if (min == Integer.MAX_VALUE) {
            return 0;
        }
        return min;
    }

    private static void change(String word, String target, int cnt) {
        if (word.equals(target)) {
            min = Math.min(min, cnt);
            return;
        }
        for (int j = 0, wordListSize = wordList.size(); j < wordListSize; j++) {
            if (isChecked[j + 1]) {
                continue;
            }
            int diffrent = 0;
            for (int i = 0; i < length; i++) {
                if (word.charAt(i) != wordList.get(j).charAt(i)) {
                    diffrent++;
                }
            }
            if (diffrent == 1) {
                isChecked[j + 1] = true;
                change(wordList.get(j), target, cnt + 1);
                isChecked[j + 1] = false;
            }
        }
    }
}
