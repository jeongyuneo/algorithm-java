package Programmers;

import java.util.PriorityQueue;

public class Programmers_연습문제_Level2_N개의최소공배수 {

    public static void main(String[] args) {
        System.out.println(solution(new int[]{2, 6, 8, 14}));
        System.out.println(solution(new int[]{1, 2, 3}));
    }

    public static int solution(int[] arr) {
        PriorityQueue<Integer> numbers = new PriorityQueue<>();
        for (int number : arr) {
            numbers.offer(number);
        }

        while (numbers.size() > 1) {
            int number1 = numbers.poll();
            int number2 = numbers.poll();
            int tempNumber1 = number1;
            int tempNumber2 = number2;
            while (tempNumber1 != tempNumber2) {
                if (tempNumber1 < tempNumber2) {
                    tempNumber1 += number1;
                } else {
                    tempNumber2 += number2;
                }
            }
            numbers.offer(tempNumber1);
        }
        return numbers.poll();
    }
}
