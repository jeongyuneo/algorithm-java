package Programmers;

import java.util.ArrayList;
import java.util.List;

public class Programmers_완전탐색_Level2_모음사전 {

    public static void main(String[] args) {
        System.out.println(solution("AAAAE"));
        System.out.println(solution("AAAE"));
        System.out.println(solution("I"));
        System.out.println(solution("EIO"));
    }

    private static final int LENGTH = 5;
    private static final String[] WORDS = new String[]{"A", "E", "I", "O", "U"};
    private static final List<String> DICTIONARY = new ArrayList<>();

    public static int solution(String word) {
        makeDictionary(0, "");
        return DICTIONARY.indexOf(word) + 1;
    }

    private static void makeDictionary(int cnt, String word) {
        if (cnt == LENGTH) {
            return;
        }
        for (int i = 0; i < LENGTH; i++) {
            DICTIONARY.add(word + WORDS[i]);
            makeDictionary(cnt + 1, word + WORDS[i]);
        }
    }
}
