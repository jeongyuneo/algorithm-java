package Programmers;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

public class Programmers_정렬_Level2_가장큰수 {

    public static void main(String[] args) {
        System.out.println(solution(new int[]{6, 10, 2}));
        System.out.println(solution(new int[]{3, 30, 34, 5, 9}));
    }

    private static final String ZERO = "0";
    private static final String EMPTY = "";

    public static String solution(int[] numbers) {
        List<String> stringNumbers = Arrays.stream(numbers)
                .mapToObj(String::valueOf)
                .sorted((s1, s2) -> (s2 + s1).compareTo(s1 + s2))
                .collect(Collectors.toList());
        if (stringNumbers.get(0).equals(ZERO)) {
            return ZERO;
        }
        return String.join(EMPTY, stringNumbers);
    }
}
