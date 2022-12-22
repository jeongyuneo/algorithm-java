package Programmers;

import java.util.*;

import static java.util.stream.Collectors.*;

public class Programmers_해시_Level2_위장 {

    private static final int NAME = 0;
    private static final int TYPE = 1;
    private static final int DO_NOT_PUT_ON = 0;
    private static final int PUT_ON = 1;

    public static void main(String[] args) {
        System.out.println(solution(new String[][]{{"yellow_hat", "headgear"}, {"blue_sunglasses", "eyewear"}, {"green_turban", "headgear"}}));
        System.out.println(solution(new String[][]{{"crow_mask", "face"}, {"blue_sunglasses", "face"}, {"smoky_makeup", "face"}}));
    }

    public static int solution(String[][] clothes) {
        int[] counts = Arrays.stream(clothes)
                .collect(groupingBy(clothe -> clothe[TYPE], mapping(clothe -> clothe[NAME], counting())))
                .values()
                .stream()
                .mapToInt(Long::intValue)
                .toArray();
        int kind = counts.length;
        int[][] cases = new int[kind][2];
        cases[0][DO_NOT_PUT_ON] = 1;
        cases[0][PUT_ON] = counts[0];
        for (int i = 1; i < kind; i++) {
            int previous = i - 1;
            cases[i][DO_NOT_PUT_ON] = cases[previous][DO_NOT_PUT_ON] + cases[previous][PUT_ON];
            cases[i][PUT_ON] = cases[previous][DO_NOT_PUT_ON] * counts[i] + cases[previous][PUT_ON] * counts[i];
        }
        return cases[kind - 1][DO_NOT_PUT_ON] + cases[kind - 1][PUT_ON] - 1;
    }
}
