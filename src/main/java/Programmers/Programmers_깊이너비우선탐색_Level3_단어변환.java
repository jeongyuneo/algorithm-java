package Programmers;

public class Programmers_깊이너비우선탐색_Level3_단어변환 {

    public static void main(String[] args) {
        System.out.println(solution("hit", "cog", new String[]{"hot", "dot", "dog", "lot", "log", "cog"}));
        System.out.println(solution("hit", "cog", new String[]{"hot", "dot", "dog", "lot", "log"}));
    }

    private static boolean[] isChecked;
    private static int min;

    public static int solution(String begin, String target, String[] words) {
        isChecked = new boolean[words.length];
        min = Integer.MAX_VALUE;
        change(words, begin, target, 0);
        if (min == Integer.MAX_VALUE) {
            return 0;
        }
        return min;
    }

    private static void change(String[] words, String word, String target, int cnt) {
        if (word.equals(target)) {
            min = Math.min(min, cnt);
            return;
        }
        for (int i = 0, wordsSize = words.length; i < wordsSize; i++) {
            if (!isChecked[i] && getDifferentCount(word, words[i]) == 1) {
                isChecked[i] = true;
                change(words, words[i], target, cnt + 1);
                isChecked[i] = false;
            }
        }
    }

    private static int getDifferentCount(String word, String candidate) {
        int diffrent = 0;
        for (int j = 0; j < word.length(); j++) {
            if (word.charAt(j) != candidate.charAt(j)) {
                diffrent++;
            }
        }
        return diffrent;
    }
}
