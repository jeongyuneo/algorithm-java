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
    private static final String EMPTY = "";

    public static int solution(String numbers) {
        initializePrimeNumber();
        permutation("", numbers);
        return (int) NUMBERS.stream()
                .filter(number -> number == 2 || number % 2 != 0 && IS_PRIME_NUMBER[number])
                .count();
    }

    private static void initializePrimeNumber() {
        Arrays.fill(IS_PRIME_NUMBER, true);
        IS_PRIME_NUMBER[0] = false;
        IS_PRIME_NUMBER[1] = false;
        for (int i = 3, sqrt = (int) Math.sqrt(MAXIMUM_SIZE); i < sqrt; i += 2) {
            if (!IS_PRIME_NUMBER[i]) {
                continue;
            }
            for (int j = 2; i * j < MAXIMUM_SIZE; j++) {
                IS_PRIME_NUMBER[i * j] = false;
            }
        }
    }

    private static void permutation(String permutationPapers, String restPapers) {
        if (!permutationPapers.equals(EMPTY)) {
            NUMBERS.add(Integer.parseInt(permutationPapers));
        }

        for (int i = 0, size = restPapers.length(); i < size; i++) {
            permutation(permutationPapers + restPapers.charAt(i), restPapers.substring(0, i) + restPapers.substring(i + 1, size));
        }
    }
}
