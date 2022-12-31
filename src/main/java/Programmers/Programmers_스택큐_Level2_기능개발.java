package Programmers;

import java.util.*;
import java.util.stream.IntStream;

public class Programmers_스택큐_Level2_기능개발 {

    public static void main(String[] args) {
        System.out.println(Arrays.toString(solution(new int[]{93, 30, 55}, new int[]{1, 30, 5})));
        System.out.println(Arrays.toString(solution(new int[]{95, 90, 99, 99, 80, 99}, new int[]{1, 1, 1, 1, 1, 1})));
    }

    private static final int PROGRESS = 0;
    private static final int ORDER = 1;

    public static int[] solution(int[] progresses, int[] speeds) {
        List<Integer> list = new ArrayList<>();
        Queue<int[]> processes = new ArrayDeque<>();
        Stack<Integer> done = new Stack<>();
        IntStream.range(0, progresses.length).forEach(order -> processes.offer(new int[]{progresses[order], order}));
        while (!processes.isEmpty()) {
            int size = processes.size();
            int order = 0;
            while (size-- > 0) {
                int[] process = processes.poll();
                int progress = process[PROGRESS] + speeds[process[ORDER]];
                if (progress >= 100 && ((done.isEmpty() && order == 0) || (!done.isEmpty() && done.peek() == order - 1))) {
                    done.push(order++);
                    continue;
                }
                processes.offer(new int[]{progress, process[ORDER]});
                order++;
            }
            if (!done.isEmpty()) {
                list.add(done.size());
                done.clear();
            }
        }

        int[] answer = new int[list.size()];
        int i = 0;
        for (int number : list) {
            answer[i++] = number;
        }
        return answer;
    }
}
