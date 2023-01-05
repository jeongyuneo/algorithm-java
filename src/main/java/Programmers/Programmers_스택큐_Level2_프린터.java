package Programmers;

import java.util.ArrayDeque;
import java.util.Arrays;
import java.util.Queue;

public class Programmers_스택큐_Level2_프린터 {

    public static void main(String[] args) {
        System.out.println(solution(new int[]{2, 1, 3, 2}, 2));
        System.out.println(solution(new int[]{1, 1, 9, 1, 1, 1}, 0));
    }

    public static int solution(int[] priorities, int location) {
        int order = 0;
        Queue<Integer> printer = new ArrayDeque<>();
        for (int priority : priorities) {
            printer.offer(priority);
        }
        Arrays.sort(priorities);
        int documentNumber = priorities.length - 1;
        while (!printer.isEmpty()) {
            int priority = printer.poll();
            if (priority == priorities[documentNumber - order]) {
                order++;
                if (--location < 0) {
                    break;
                }
            } else {
                printer.add(priority);
                location = (location + printer.size() - 1) % printer.size();
            }
        }
        return order;
    }
}
