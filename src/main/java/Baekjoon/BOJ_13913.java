package Baekjoon;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class BOJ_13913 {

    private static final int NOT_VISIT = -1;

    public static void main(String[] args) throws IOException {
        BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer stringTokenizer = new StringTokenizer(bufferedReader.readLine());
        int n = Integer.parseInt(stringTokenizer.nextToken());
        int k = Integer.parseInt(stringTokenizer.nextToken());

        Queue<Integer> subin = new ArrayDeque<>();
        subin.offer(n);
        int[] visited = new int[100001];
        Arrays.fill(visited, -1);
        visited[n] = Integer.MAX_VALUE;
        int time = 0;
        while (!subin.isEmpty()) {
            int size = subin.size();
            while (size-- > 0) {
                int current = subin.poll();
                if (current == k) {
                    Stack traces = new Stack();
                    int parent = k;
                    while (parent != Integer.MAX_VALUE) {
                        traces.push(parent);
                        parent = visited[parent];
                    }
                    StringBuilder answer = new StringBuilder();
                    answer.append(time).append("\n");
                    while (!traces.isEmpty()) {
                        answer.append(traces.pop()).append(" ");
                    }
                    System.out.println(answer);
                    break;
                }

                int[] nexts = {current - 1, current + 1, current * 2};
                for (int next : nexts) {
                    if (next >= 0 && next <= 100000 && visited[next] == NOT_VISIT) {
                        visited[next] = current;
                        subin.offer(next);
                    }
                }
            }
            time++;
        }
    }
}
