package Programmers;

import java.util.ArrayDeque;
import java.util.Queue;

public class Programmers_깊이너비우선탐색_Level2_타겟넘버 {

    public static void main(String[] args) {
        System.out.println(solution(new int[]{1, 1, 1, 1, 1,}, 3));
        System.out.println(solution(new int[]{4, 1, 2, 1,}, 4));
    }

    public static int solution(int[] numbers, int target) {
        int numberCount = numbers.length;
        int answer = 0;
        Queue<int[]> operation = new ArrayDeque<>();
        operation.offer(new int[]{0, 0});
        while (!operation.isEmpty()) {
            int[] current = operation.poll();
            int number = current[0];
            int count = current[1];
            if (count == numberCount) {
                if (number == target) {
                    answer++;
                }
                continue;
            }
            operation.offer(new int[]{number + numbers[count], count + 1});
            operation.offer(new int[]{number - numbers[count], count + 1});
        }
        return answer;
    }
}
