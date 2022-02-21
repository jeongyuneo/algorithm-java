package Baekjoon;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.StringTokenizer;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class BOJ_1759 {

    private static final Pattern VOWELS = Pattern.compile("[aeiou]");
    private static final List<String> RESULT = new ArrayList<>();
    private static final int MIN_VOWEL = 1;
    private static final int MIN_CONSONANT = 2;
    private static final StringBuilder STRING_BUILDER = new StringBuilder();

    private static int l;
    private static int c;
    private static String[] selected;

    public static void main(String[] args) throws IOException {
        BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer stringTokenizer = new StringTokenizer(bufferedReader.readLine());
        l = Integer.parseInt(stringTokenizer.nextToken());
        c = Integer.parseInt(stringTokenizer.nextToken());

        List<String> alphabets = new ArrayList<>();
        stringTokenizer = new StringTokenizer(bufferedReader.readLine());
        for (int i = 0; i < c; i++) {
            alphabets.add(stringTokenizer.nextToken());
        }
        selected = new String[l];
        findCombination(alphabets, 0, 0);
        RESULT.stream().sorted()
                .forEach(STRING_BUILDER::append);
        System.out.println(STRING_BUILDER);
    }

    private static void findCombination(List<String> alphabets, int cnt, int start) {
        if (cnt == l) {
            int vowelCnt = 0;
            int consonantCnt = 0;
            for (String selectedAlphabet : selected) {
                if (isVowel(selectedAlphabet)) {
                    vowelCnt++;
                } else {
                    consonantCnt++;
                }
            }
            if (vowelCnt >= MIN_VOWEL && consonantCnt >= MIN_CONSONANT) {
                StringBuilder code = new StringBuilder();
                Arrays.stream(selected)
                        .sorted()
                        .forEach(code::append);
                code.append("\n");
                RESULT.add(code.toString());
            }
            return;
        }
        for (int i = start; i < c; i++) {
            selected[cnt] = alphabets.get(i);
            findCombination(alphabets, cnt+1, i+1);
        }
    }

    private static boolean isVowel(String alphabet) {
        Matcher matcher = VOWELS.matcher(alphabet);
        return matcher.matches();
    }
}
