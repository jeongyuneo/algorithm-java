package Baekjoon;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class BOJ_21924 {

    private static final int BUILDING = 0;
    private static final int COST = 1;

    public static void main(String[] args) throws IOException {
        BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer stringTokenizer = new StringTokenizer(bufferedReader.readLine());
        int n = Integer.parseInt(stringTokenizer.nextToken());
        int m = Integer.parseInt(stringTokenizer.nextToken());
        List<int[]>[] roads = new List[n];
        for (int i = 0; i < n; i++) {
            roads[i] = new ArrayList<>();
        }
        long totalCost = 0;
        for (int i = 0; i < m; i++) {
            stringTokenizer = new StringTokenizer(bufferedReader.readLine());
            int from = Integer.parseInt(stringTokenizer.nextToken()) - 1;
            int to = Integer.parseInt(stringTokenizer.nextToken()) - 1;
            int cost = Integer.parseInt(stringTokenizer.nextToken());
            roads[from].add(new int[]{to, cost});
            roads[to].add(new int[]{from, cost});
            totalCost += cost;
        }
        long minCost = getMinCost(n, roads);
        if (minCost > 0) {
            System.out.println(totalCost - minCost);
        } else {
            System.out.println(minCost);
        }
    }

    private static long getMinCost(int n, List<int[]>[] roads) {
        PriorityQueue<int[]> moves = new PriorityQueue<>(Comparator.comparing(move -> move[COST]));
        boolean[] isVisited = new boolean[n];
        long minCost = 0;
        int count = 0;
        moves.offer(new int[]{0, 0});
        while (!moves.isEmpty()) {
            int[] current = moves.poll();
            int from = current[BUILDING];
            if (isVisited[from]) {
                continue;
            }
            minCost += current[COST];
            if (++count == n) {
                return minCost;
            }
            isVisited[from] = true;
            for (int[] next : roads[from]) {
                if (!isVisited[next[BUILDING]]) {
                    moves.offer(next);
                }
            }
        }
        return -1;
    }
}
