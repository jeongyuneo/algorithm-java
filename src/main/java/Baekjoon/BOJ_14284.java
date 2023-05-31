package Baekjoon;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class BOJ_14284 {

    private static final int VERTEX = 0;
    private static final int WEIGHT = 1;

    public static void main(String[] args) throws IOException {
        BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer stringTokenizer = new StringTokenizer(bufferedReader.readLine());
        int n = Integer.parseInt(stringTokenizer.nextToken());
        int m = Integer.parseInt(stringTokenizer.nextToken());
        List<int[]>[] edges = new List[n];
        for (int i = 0; i < n; i++) {
            edges[i] = new ArrayList<>();
        }
        for (int i = 0; i < m; i++) {
            stringTokenizer = new StringTokenizer(bufferedReader.readLine());
            int from = Integer.parseInt(stringTokenizer.nextToken()) - 1;
            int to = Integer.parseInt(stringTokenizer.nextToken()) - 1;
            int weight = Integer.parseInt(stringTokenizer.nextToken());
            edges[from].add(new int[]{to, weight});
            edges[to].add(new int[]{from, weight});
        }
        stringTokenizer = new StringTokenizer(bufferedReader.readLine());
        int start = Integer.parseInt(stringTokenizer.nextToken()) - 1;
        int end = Integer.parseInt(stringTokenizer.nextToken()) - 1;
        int min = getMinWeight(edges, n, start, end);
        System.out.println(min);
    }

    private static int getMinWeight(List<int[]>[] edges, int n, int start, int end) {
        PriorityQueue<int[]> priorityQueue = new PriorityQueue<>(Comparator.comparing(info -> info[WEIGHT]));
        boolean[] isVisited = new boolean[n];
        int[] weights = new int[n];
        priorityQueue.offer(new int[]{start, 0});
        Arrays.fill(weights, Integer.MAX_VALUE);
        weights[start] = 0;
        while (!priorityQueue.isEmpty()) {
            int[] current = priorityQueue.poll();
            int from = current[VERTEX];
            if (from == end) {
                return current[WEIGHT];
            }
            if (isVisited[from]) {
                continue;
            }
            isVisited[from] = true;
            for (int[] next : edges[from]) {
                int to = next[VERTEX];
                int weight = next[WEIGHT] + current[WEIGHT];
                if (weights[to] > weight) {
                    weights[to] = weight;
                    priorityQueue.offer(new int[]{to, weight});
                }
            }
        }
        return 0;
    }
}
