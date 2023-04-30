package Programmers;

import java.util.Arrays;
import java.util.Comparator;
import java.util.PriorityQueue;

public class Programmers_힙_Level3_이중우선순위큐 {

    public static void main(String[] args) {
        System.out.println(Arrays.toString(solution(new String[]{"I 16", "I -5643", "D -1", "D 1", "D 1", "I 123", "D -1"})));
        System.out.println(Arrays.toString(solution(new String[]{"I -45", "I 653", "D 1", "I -642", "I 45", "I 97", "D 1", "D -1", "I 333"})));
    }

    private static final String DELIMITER = " ";
    private static final String INSERT = "I";
    private static final int NUMBER = 1;
    private static final int MAX_VALUE_DELETE = 1;

    public static int[] solution(String[] operations) {
        PriorityQueue<Integer> orderedAsc = new PriorityQueue<>();
        PriorityQueue<Integer> orderedDesc = new PriorityQueue<>(Comparator.reverseOrder());
        for (String operation : operations) {
            int number = Integer.parseInt(operation.split(DELIMITER)[NUMBER]);
            if (operation.startsWith(INSERT)) {
                orderedAsc.offer(number);
                orderedDesc.offer(number);
            } else {
                if (orderedAsc.isEmpty()) {
                    continue;
                }
                if (number == MAX_VALUE_DELETE) {
                    int max = orderedDesc.poll();
                    orderedAsc.remove(max);
                } else {
                    int min = orderedAsc.poll();
                    orderedDesc.remove(min);
                }
            }
        }
        int[] answer = new int[2];
        if (orderedAsc.size() == 1 && orderedDesc.size() == 1) {
            answer[0] = orderedAsc.peek();
            answer[1] = orderedAsc.poll();
        } else if (orderedAsc.size() > 1 && orderedDesc.size() > 1) {
            answer[0] = orderedDesc.poll();
            answer[1] = orderedAsc.poll();
        }
        return answer;
    }
}
