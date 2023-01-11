package Programmers;

import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;

public class Programmers_완전탐색_Level2_소수찾기 {

    public static void main(String[] args) {
        System.out.println(solution("17"));
        System.out.println(solution("011"));
    }

    private static final int MAXIMUM_SIZE = 10000000;
    private static final boolean[] IS_PRIME_NUMBER = new boolean[MAXIMUM_SIZE];
    private static final Set<Integer> NUMBERS = new HashSet<>();

    private static String[] papers;
    private static boolean[] isSelected;
    private static String[] selectedPapers;
    private static int paperCount;
    private static int permutationCount;

    public static int solution(String numbers) {
        initializePrimeNumber();
        paperCount = numbers.length();
        papers = numbers.split("");
        isSelected = new boolean[paperCount];

        for (permutationCount = 1; permutationCount <= paperCount; permutationCount++) {
            selectedPapers = new String[permutationCount];
            permutation(0);
        }
        return (int) NUMBERS.stream()
                .filter(number -> IS_PRIME_NUMBER[number])
                .count();
    }

    private static void permutation(int cnt) {
        if (cnt == permutationCount) {
            NUMBERS.add(Integer.parseInt(String.join("", selectedPapers)));
            return;
        }
        for (int i = 0; i < paperCount; i++) {
            if (!isSelected[i]) {
                selectedPapers[cnt] = papers[i];
                isSelected[i] = true;
                permutation(cnt + 1);
                isSelected[i] = false;
            }
        }
    }

    private static void initializePrimeNumber() {
        Arrays.fill(IS_PRIME_NUMBER, true);
        IS_PRIME_NUMBER[0] = false;
        IS_PRIME_NUMBER[1] = false;
        for (int i = 2, sqrt = (int)Math.sqrt(MAXIMUM_SIZE); i < sqrt; i++) {
            if (!IS_PRIME_NUMBER[i]) {
                continue;
            }
            for (int j = 2; i * j < MAXIMUM_SIZE; j++) {
                IS_PRIME_NUMBER[i * j] = false;
            }
        }
    }
}
