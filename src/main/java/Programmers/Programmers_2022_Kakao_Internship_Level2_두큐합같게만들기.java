package Programmers;

import java.util.ArrayDeque;
import java.util.Queue;

public class Programmers_2022_Kakao_Internship_Level2_두큐합같게만들기 {

    public static void main(String[] args) {
        System.out.println(solution(new int[]{3, 2, 7, 2}, new int[]{4, 6, 5, 1}));
        System.out.println(solution(new int[]{1, 2, 1, 2}, new int[]{1, 10, 1, 2}));
        System.out.println(solution(new int[]{1, 1}, new int[]{1, 5}));
    }

    public static int solution(int[] queue1, int[] queue2) {
        Queue<Integer> firstQueue = new ArrayDeque<>();
        Queue<Integer> secondQueue = new ArrayDeque<>();
        int n = queue1.length;
        long sum1 = 0;
        long sum2 = 0;
        for (int i = 0; i < n; i++) {
            sum1 += queue1[i];
            sum2 += queue2[i];
            firstQueue.offer(queue1[i]);
            secondQueue.offer(queue2[i]);
        }
        int answer = 0;
        while (sum1 != sum2) {
            if (answer > n * 4) {
                answer = -1;
                break;
            }
            if (sum1 < sum2) {
                int number = secondQueue.poll();
                firstQueue.offer(number);
                sum2 -= number;
                sum1 += number;
            } else {
                int number = firstQueue.poll();
                secondQueue.offer(number);
                sum1 -= number;
                sum2 += number;
            }
            answer++;
        }
        return answer;
    }
}
