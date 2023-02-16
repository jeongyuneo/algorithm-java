package Baekjoon;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Comparator;
import java.util.PriorityQueue;

public class BOJ_1655 {

    public static void main(String[] args) throws IOException {
        BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder answer = new StringBuilder();
        int n = Integer.parseInt(bufferedReader.readLine());
        PriorityQueue<Integer> front = new PriorityQueue<>(Comparator.reverseOrder());
        PriorityQueue<Integer> back = new PriorityQueue<>();
        for (int i = 0; i < n; i++) {
            int number = Integer.parseInt(bufferedReader.readLine());
            if (front.size() == back.size()) {
                front.offer(number);
            } else {
                back.offer(number);
            }
            if (!back.isEmpty() && front.peek() > back.peek()) {
                back.offer(front.poll());
                front.offer(back.poll());
            }
            answer.append(front.peek()).append("\n");
        }
        System.out.println(answer);
    }
}
