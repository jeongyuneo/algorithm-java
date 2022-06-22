package Baekjoon;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.PriorityQueue;

public class BOJ_1927 {

    public static void main(String[] args) throws IOException {
        BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(System.in));
        int n = Integer.parseInt(bufferedReader.readLine());
        PriorityQueue<Integer> priorityQueue = new PriorityQueue<>();
        StringBuilder answer = new StringBuilder();
        for (int i = 0; i < n; i++) {
            int input = Integer.parseInt(bufferedReader.readLine());
            if (input == 0) {
                if (priorityQueue.isEmpty()) {
                    answer.append(0)
                            .append("\n");
                } else {
                    answer.append(priorityQueue.poll())
                            .append("\n");
                }
            } else {
                priorityQueue.add(input);
            }
        }
        System.out.println(answer);
    }
}
