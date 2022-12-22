package Programmers;

import java.util.HashSet;
import java.util.Set;

public class Programmers_해시_Level1_폰켓몬 {

    public static void main(String[] args) {
        System.out.println(solution(new int[]{3, 1, 2, 3}));
        System.out.println(solution(new int[]{3, 3, 3, 2, 2, 4}));
        System.out.println(solution(new int[]{3, 3, 3, 2, 2, 2}));
    }

    public static int solution(int[] nums) {
        Set<Integer> ponkemons = new HashSet<>();
        for (int num : nums) {
            ponkemons.add(num);
        }
        return Math.min(ponkemons.size(), nums.length / 2);
    }
}
