package Programmers;

import java.util.PriorityQueue;

public class Programmers_힙_Level2_더맵게 {

    public static void main(String[] args) {
        System.out.println(solution(new int[]{1, 2, 3, 9, 10, 12}, 7));
    }

    public static int solution(int[] scoville, int K) {
        PriorityQueue<Integer> foods = new PriorityQueue<>();
        for (int s : scoville) {
            foods.offer(s);
        }

        int answer = 0;
        while (foods.peek() < K) {
            if (foods.size() < 2) {
                return -1;
            }
            foods.offer(foods.poll() + (foods.poll() << 1));
            answer++;
        }
        return answer;
    }
}
