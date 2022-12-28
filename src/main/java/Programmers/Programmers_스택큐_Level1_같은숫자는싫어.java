package Programmers;

import java.util.*;

public class Programmers_스택큐_Level1_같은숫자는싫어 {

    public static void main(String[] args) {
        System.out.println(Arrays.toString(solution(new int[]{1, 1, 3, 3, 0, 1, 1})));
        System.out.println(Arrays.toString(solution(new int[]{4, 4, 4, 3, 3})));
    }

    public static int[] solution(int[] arr) {
        List<Integer> numbers = new ArrayList<>();
        numbers.add(arr[0]);
        for (int i = 1, arrLength = arr.length; i < arrLength; i++) {
            if (arr[i] == arr[i - 1]) {
                continue;
            }
            numbers.add(arr[i]);
        }

        int[] answer = new int[numbers.size()];
        for (int i = 0; i < numbers.size(); i++) {
            answer[i] = numbers.get(i);
        }
        return answer;
    }
}
