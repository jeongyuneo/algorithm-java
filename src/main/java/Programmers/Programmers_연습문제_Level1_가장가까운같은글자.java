package Programmers;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

public class Programmers_연습문제_Level1_가장가까운같은글자 {

    public static void main(String[] args) {
        System.out.println(Arrays.toString(solution("banana")));
        System.out.println(Arrays.toString(solution("foobar")));
    }

    public static int[] solution(String s) {
        Map<Character, Integer> lastLocations = new HashMap<>();
        int[] answer = new int[s.length()];
        for (int current = 0; current < s.length(); current++) {
            char alphabet = s.charAt(current);
            if (lastLocations.containsKey(alphabet)) {
                answer[current] = current - lastLocations.get(alphabet);
            } else {
                answer[current] = -1;
            }
            lastLocations.put(alphabet, current);
        }
        return answer;
    }
}
