package Programmers;

import java.util.*;

public class Programmers_스택큐_Level1_같은숫자는싫어 {

    public static void main(String[] args) {
        System.out.println(Arrays.toString(solution(new int[]{1, 1, 3, 3, 0, 1, 1})));
        System.out.println(Arrays.toString(solution(new int[]{4, 4, 4, 3, 3})));
    }

    public static int[] solution(int[] arr) {
        Deque<Integer> deque = new ArrayDeque<>();
        deque.offer(arr[0]);
        for (int i = 1, arrLength = arr.length; i < arrLength; i++) {
            int number = arr[i];
            if (deque.peekLast() == number) {
                continue;
            }
            deque.offer(number);
        }

        int[] answer = new int[deque.size()];
        int index = 0;
        while (!deque.isEmpty()) {
            answer[index++] = deque.poll();
        }
        return answer;
    }
}
