package Baekjoon;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

public class BOJ_1766 {

    public static void main(String[] args) throws IOException {
        BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer stringTokenizer = new StringTokenizer(bufferedReader.readLine());
        int n = Integer.parseInt(stringTokenizer.nextToken());
        int m = Integer.parseInt(stringTokenizer.nextToken());
        List<Integer>[] problems = new List[n + 1];
        for (int i = 1; i <= n; i++) {
            problems[i] = new ArrayList<>();
        }
        int[] prerequisites = new int[n + 1];
        for (int i = 0; i < m; i++) {
            stringTokenizer = new StringTokenizer(bufferedReader.readLine());
            int prerequisite = Integer.parseInt(stringTokenizer.nextToken());
            int problem = Integer.parseInt(stringTokenizer.nextToken());
            problems[prerequisite].add(problem);
            prerequisites[problem]++;
        }
        StringBuilder answer = new StringBuilder();
        PriorityQueue<Integer> priorityQueue = new PriorityQueue<>();
        for (int i = 1; i <= n; i++) {
            if (prerequisites[i] == 0) {
                priorityQueue.offer(i);
            }
        }
        while (!priorityQueue.isEmpty()) {
            int problem = priorityQueue.poll();
            answer.append(problem).append(" ");
            for (int nextProblem : problems[problem]) {
                if (--prerequisites[nextProblem] == 0) {
                    priorityQueue.offer(nextProblem);
                }
            }
        }
        System.out.println(answer);
    }
}
