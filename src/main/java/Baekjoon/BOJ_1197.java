package Baekjoon;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class BOJ_1197 {

    private static final int NUMBER = 0;
    private static final int WEIGHT = 1;
    private static final PriorityQueue<int[]> PRIORITY_QUEUE = new PriorityQueue<>(Comparator.comparing(edge -> edge[WEIGHT]));

    public static void main(String[] args) throws IOException {
        BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer stringTokenizer = new StringTokenizer(bufferedReader.readLine());
        int v = Integer.parseInt(stringTokenizer.nextToken());
        int e = Integer.parseInt(stringTokenizer.nextToken());
        List<int[]>[] edges = new List[v];
        for (int i = 0; i < v; i++) {
            edges[i] = new ArrayList<>();
        }
        for (int i = 0; i < e; i++) {
            stringTokenizer = new StringTokenizer(bufferedReader.readLine());
            int from = Integer.parseInt(stringTokenizer.nextToken()) - 1;
            int to = Integer.parseInt(stringTokenizer.nextToken()) - 1;
            int weight = Integer.parseInt(stringTokenizer.nextToken());
            edges[from].add(new int[]{to, weight});
            edges[to].add(new int[]{from, weight});
        }
        boolean[] isVisited = new boolean[v];
        int weight = 0;
        int combinedCount = 0;
        PRIORITY_QUEUE.offer(new int[]{0, 0});
        while (!PRIORITY_QUEUE.isEmpty()) {
            int[] current = PRIORITY_QUEUE.poll();
            int from = current[NUMBER];
            if (isVisited[from]) {
                continue;
            }
            weight += current[WEIGHT];
            if (++combinedCount == v) {
                break;
            }
            isVisited[from] = true;
            for (int[] next : edges[from]) {
                if (!isVisited[next[NUMBER]]) {
                    PRIORITY_QUEUE.offer(next);
                }
            }
        }
        System.out.println(weight);
    }
}
