package Programmers;

import java.util.Arrays;

public class Programmers_연습문제_Level1_가장가까운같은글자 {

    public static void main(String[] args) {
        System.out.println(Arrays.toString(solution("banana")));
        System.out.println(Arrays.toString(solution("foobar")));
    }

    public static int[] solution(String s) {
        int[] lastLocations = new int[26];
        Arrays.fill(lastLocations, -1);
        int[] answer = new int[s.length()];
        for (int current = 0; current < s.length(); current++) {
            int alphabet = s.charAt(current) - 97;
            int last = lastLocations[alphabet];
            if (last == -1) {
                answer[current] = -1;
            } else {
                answer[current] = current - last;
            }
            lastLocations[alphabet] = current;
        }
        return answer;
    }
}
