package Programmers;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Stack;

public class Programmers_스택큐_Level2_기능개발 {

    public static void main(String[] args) {
        System.out.println(Arrays.toString(solution(new int[]{93, 30, 55}, new int[]{1, 30, 5})));
        System.out.println(Arrays.toString(solution(new int[]{95, 90, 99, 99, 80, 99}, new int[]{1, 1, 1, 1, 1, 1})));
    }

    public static int[] solution(int[] progresses, int[] speeds) {
        List<Integer> releases = new ArrayList<>();
        int length = progresses.length;
        int[] done = new int[length];
        for (int i = 0; i < length; i++) {
            done[i] += (int) Math.ceil((100.0 - progresses[i]) / speeds[i]);
        }
        Stack<Integer> stack = new Stack<>();
        int first = done[0];
        stack.push(first);
        for (int i = 1; i < length; i++) {
            if (first >= done[i]) {
                stack.push(done[i]);
            } else {
                releases.add(stack.size());
                stack.clear();
                first = done[i];
                stack.push(first);
            }
        }
        if (!stack.isEmpty()) {
            releases.add(stack.size());
        }
        int[] result = new int[releases.size()];
        for (int i = 0; i < releases.size(); i++) {
            result[i] = releases.get(i);
        }
        return result;
    }
}
