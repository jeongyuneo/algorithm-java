package Programmers;

import java.util.*;

public class Programmers_스택큐_Level2_기능개발 {

    public static void main(String[] args) {
        System.out.println(Arrays.toString(solution(new int[]{93, 30, 55}, new int[]{1, 30, 5})));
        System.out.println(Arrays.toString(solution(new int[]{95, 90, 99, 99, 80, 99}, new int[]{1, 1, 1, 1, 1, 1})));
    }

    public static int[] solution(int[] progresses, int[] speeds) {
        List<Integer> answer = new ArrayList<>();
        Queue<Integer> doneProgresses = new ArrayDeque<>();
        for (int i = 0; i < progresses.length; i++) {
            int duration = (int) Math.ceil((100 - progresses[i]) / (double) speeds[i]);
            if (!doneProgresses.isEmpty() && doneProgresses.peek() < duration) {
                answer.add(doneProgresses.size());
                doneProgresses.clear();
            }
            doneProgresses.offer(duration);
        }
        answer.add(doneProgresses.size());
        return answer.stream().mapToInt(Integer::intValue).toArray();
    }
}
