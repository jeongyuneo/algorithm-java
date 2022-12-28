package Programmers;

import java.util.*;

public class Programmers_스택큐_Level1_같은숫자는싫어 {

    public static void main(String[] args) {
        System.out.println(Arrays.toString(solution(new int[]{1, 1, 3, 3, 0, 1, 1})));
        System.out.println(Arrays.toString(solution(new int[]{4, 4, 4, 3, 3})));
    }

    public static int[] solution(int[] arr) {
        List<Integer> numbers = new ArrayList<>();
        int previous = 10;
        for (int number : arr) {
            if (previous != number) {
                numbers.add(number);
                previous = number;
            }
        }

        int[] answer = new int[numbers.size()];
        for (int i = 0; i < numbers.size(); i++) {
            answer[i] = numbers.get(i);
        }
        return answer;
    }
}
