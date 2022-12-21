package Programmers;

import java.util.*;

public class Programmers_해시_Level2_위장 {

    private static final int TYPE = 1;

    public static void main(String[] args) {
        System.out.println(solution(new String[][]{{"yellow_hat", "headgear"}, {"blue_sunglasses", "eyewear"}, {"green_turban", "headgear"}}));
        System.out.println(solution(new String[][]{{"crow_mask", "face"}, {"blue_sunglasses", "face"}, {"smoky_makeup", "face"}}));
    }

    public static int solution(String[][] clothes) {
        Map<String, Integer> closet = new HashMap<>();
        for (String[] clothe : clothes) {
            String type = clothe[TYPE];
            closet.put(type, closet.getOrDefault(type, 0) + 1);
        }

        int answer = 1;
        for (int countOfClothes : closet.values()) {
            answer *= countOfClothes + 1;
        }
        return answer - 1;
    }
}
