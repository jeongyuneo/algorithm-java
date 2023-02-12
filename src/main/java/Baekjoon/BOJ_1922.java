package Baekjoon;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class BOJ_1922 {

    private static final int NUMBER = 0;
    private static final int COST = 1;
    private static final PriorityQueue<int[]> PRIORITY_QUEUE = new PriorityQueue<>(Comparator.comparing(computer -> computer[COST]));

    public static void main(String[] args) throws IOException {
        BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(System.in));
        int n = Integer.parseInt(bufferedReader.readLine());
        int m = Integer.parseInt(bufferedReader.readLine());
        List<int[]>[] computers = new List[n];
        boolean[] isVisited = new boolean[n];
        for (int i = 0; i < n; i++) {
            computers[i] = new ArrayList<>();
        }
        for (int i = 0; i < m; i++) {
            StringTokenizer stringTokenizer = new StringTokenizer(bufferedReader.readLine());
            int from = Integer.parseInt(stringTokenizer.nextToken()) - 1;
            int to = Integer.parseInt(stringTokenizer.nextToken()) - 1;
            int cost = Integer.parseInt(stringTokenizer.nextToken());
            computers[from].add(new int[]{to, cost});
            if (from != to) {
                computers[to].add(new int[]{from, cost});
            }
        }
        int minCost = 0;
        int combinedCount = 0;
        PRIORITY_QUEUE.offer(new int[]{0, 0});
        while (!PRIORITY_QUEUE.isEmpty()) {
            int[] current = PRIORITY_QUEUE.poll();
            int from = current[NUMBER];
            if (isVisited[from]) {
                continue;
            }
            minCost += current[COST];
            if (++combinedCount == n) {
                break;
            }
            isVisited[from] = true;
            for (int[] next : computers[from]) {
                if (!isVisited[next[NUMBER]]) {
                    PRIORITY_QUEUE.offer(next);
                }
            }
        }
        System.out.println(minCost);
    }
}
