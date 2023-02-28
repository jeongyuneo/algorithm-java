package Baekjoon;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class BOJ_16398 {

    private static final int NUMBER = 0;
    private static final int COST = 1;

    public static void main(String[] args) throws IOException {
        BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(System.in));
        int n = Integer.parseInt(bufferedReader.readLine());
        List<int[]>[] edges = new List[n];
        for (int from = 0; from < n; from++) {
            edges[from] = new ArrayList<>();
            StringTokenizer stringTokenizer = new StringTokenizer(bufferedReader.readLine());
            for (int to = 0; to < n; to++) {
                int cost = Integer.parseInt(stringTokenizer.nextToken());
                if (from == to) {
                    continue;
                }
                edges[from].add(new int[]{to, cost});
            }
        }
        boolean[] isVisited = new boolean[n];
        PriorityQueue<int[]> priorityQueue = new PriorityQueue<>(Comparator.comparing(edge -> edge[COST]));
        priorityQueue.offer(new int[]{0, 0});
        long cost = 0;
        while (!priorityQueue.isEmpty()) {
            int[] current = priorityQueue.poll();
            int from = current[NUMBER];
            if (isVisited[from]) {
                continue;
            }
            cost += current[COST];
            isVisited[from] = true;
            for (int[] next : edges[from]) {
                priorityQueue.offer(next);
            }
        }
        System.out.println(cost);
    }
}
