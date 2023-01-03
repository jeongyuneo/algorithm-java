package Baekjoon;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class BOJ_1939 {

    private static final int NUMBER = 0;
    private static final int LIMIT = 1;

    public static void main(String[] args) throws IOException {
        BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer stringTokenizer = new StringTokenizer(bufferedReader.readLine());
        int n = Integer.parseInt(stringTokenizer.nextToken());
        int m = Integer.parseInt(stringTokenizer.nextToken());
        List<int[]>[] islands = new List[n];
        for (int i = 0; i < n; i++) {
            islands[i] = new ArrayList<>();
        }
        for (int i = 0; i < m; i++) {
            stringTokenizer = new StringTokenizer(bufferedReader.readLine());
            int a = Integer.parseInt(stringTokenizer.nextToken()) - 1;
            int b = Integer.parseInt(stringTokenizer.nextToken()) - 1;
            int c = Integer.parseInt(stringTokenizer.nextToken());
            islands[a].add(new int[]{b, c});
            islands[b].add(new int[]{a, c});
        }
        stringTokenizer = new StringTokenizer(bufferedReader.readLine());
        int start = Integer.parseInt(stringTokenizer.nextToken()) - 1;
        int end = Integer.parseInt(stringTokenizer.nextToken()) - 1;
        int[] maximumWeights = new int[n];
        maximumWeights[start] = Integer.MAX_VALUE;
        PriorityQueue<int[]> priorityQueue = new PriorityQueue<>((island1, island2) -> island2[LIMIT] - island1[LIMIT]);
        priorityQueue.add(new int[]{start, Integer.MAX_VALUE});
        while (!priorityQueue.isEmpty()) {
            int[] current = priorityQueue.poll();
            int island = current[NUMBER];
            int limit = current[LIMIT];
            if (maximumWeights[island] > limit) {
                continue;
            }
            for (int[] next : islands[island]) {
                int nextIsland = next[NUMBER];
                int nextLimit = Math.min(next[LIMIT], limit);
                if (maximumWeights[nextIsland] < nextLimit) {
                    maximumWeights[nextIsland] = nextLimit;
                    priorityQueue.offer(new int[]{nextIsland, nextLimit});
                }
            }
        }
        System.out.println(maximumWeights[end]);
    }
}
