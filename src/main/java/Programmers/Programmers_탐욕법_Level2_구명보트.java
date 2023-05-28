package Programmers;

import java.util.ArrayDeque;
import java.util.Arrays;
import java.util.Deque;

public class Programmers_탐욕법_Level2_구명보트 {

    public static void main(String[] args) {
        System.out.println(solution(new int[]{70, 50, 80, 50}, 100));
        System.out.println(solution(new int[]{70, 80, 50}, 100));
        System.out.println(solution(new int[]{30, 30, 30, 50}, 90));
    }

    public static int solution(int[] people, int limit) {
        Arrays.sort(people);
        Deque<Integer> weights = new ArrayDeque<>();
        for (int person : people) {
            weights.offer(person);
        }
        int answer = 0;
        while (!weights.isEmpty()) {
            int weight = weights.pollLast();
            if (!weights.isEmpty() && weight + weights.peek() <= limit) {
                weights.poll();
            }
            answer++;
        }
        return answer;
    }
}
