package Programmers;

import java.util.*;

import static java.util.stream.Collectors.*;

public class Programmers_해시_Level2_위장 {

    private static final int NAME = 0;
    private static final int TYPE = 1;

    public static void main(String[] args) {
        System.out.println(solution(new String[][]{{"yellow_hat", "headgear"}, {"blue_sunglasses", "eyewear"}, {"green_turban", "headgear"}}));
        System.out.println(solution(new String[][]{{"crow_mask", "face"}, {"blue_sunglasses", "face"}, {"smoky_makeup", "face"}}));
    }

    public static int solution(String[][] clothes) {
        return Arrays.stream(clothes)
                .collect(groupingBy(clothe -> clothe[TYPE], mapping(clothe -> clothe[NAME], counting())))
                .values()
                .stream()
                .reduce(1L, (a, b) -> a * (b + 1))
                .intValue() - 1;
    }
}
