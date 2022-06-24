package Baekjoon;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.PriorityQueue;

public class BOJ_1715 {

    public static void main(String[] args) throws IOException {
        BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(System.in));
        int n = Integer.parseInt(bufferedReader.readLine());
        PriorityQueue<Integer> priorityQueue = new PriorityQueue<>();
        for (int i = 0; i < n; i++) {
            priorityQueue.add(Integer.valueOf(bufferedReader.readLine()));
        }
        int answer = 0;
        while (priorityQueue.size() > 1) {
            int sum = priorityQueue.poll() + priorityQueue.poll();
            priorityQueue.add(sum);
            answer += sum;
        }
        System.out.println(answer);
    }
}
