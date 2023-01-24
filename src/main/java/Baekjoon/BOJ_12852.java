package Baekjoon;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.Queue;
import java.util.Stack;

public class BOJ_12852 {

    public static void main(String[] args) throws IOException {
        BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(System.in));
        int n = Integer.parseInt(bufferedReader.readLine());
        int[] parents = new int[n + 1];
        Queue<Integer> queue = new ArrayDeque<>();
        queue.offer(n);
        int count = -1;
        while (!queue.isEmpty()) {
            int size = queue.size();
            while (size-- > 0) {
                int current = queue.poll();
                if (current == 1) {
                    queue.clear();
                    break;
                }
                if (current % 3 == 0 && parents[current / 3] == 0) {
                    parents[current / 3] = current;
                    queue.offer(current / 3);
                }
                if (current % 2 == 0 && parents[current / 2] == 0) {
                    parents[current / 2] = current;
                    queue.offer(current / 2);
                }
                if (current - 1 >= 1 && parents[current - 1] == 0) {
                    parents[current - 1] = current;
                    queue.offer(current - 1);
                }
            }
            count++;
        }

        StringBuilder answer = new StringBuilder();
        answer.append(count).append("\n");
        Stack<Integer> stack = new Stack<>();
        int parent = 1;
        while (parents[parent] != 0) {
            stack.push(parent);
            parent = parents[parent];
        }
        answer.append(n).append(" ");
        while (!stack.isEmpty()) {
            answer.append(stack.pop()).append(" ");
        }
        System.out.println(answer);
    }
}
