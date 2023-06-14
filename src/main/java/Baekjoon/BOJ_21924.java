package Baekjoon;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Comparator;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

public class BOJ_21924 {

    private static final int FROM = 0;
    private static final int TO = 1;
    private static final int COST = 2;

    private static int[] roots;

    public static void main(String[] args) throws IOException {
        BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer stringTokenizer = new StringTokenizer(bufferedReader.readLine());
        int n = Integer.parseInt(stringTokenizer.nextToken());
        int m = Integer.parseInt(stringTokenizer.nextToken());
        roots = new int[n];
        for (int i = 1; i < n; i++) {
            roots[i] = i;
        }
        PriorityQueue<int[]> roads = new PriorityQueue<>(Comparator.comparing(road -> road[COST]));
        long totalCost = 0;
        for (int i = 0; i < m; i++) {
            stringTokenizer = new StringTokenizer(bufferedReader.readLine());
            int from = Integer.parseInt(stringTokenizer.nextToken()) - 1;
            int to = Integer.parseInt(stringTokenizer.nextToken()) - 1;
            int cost = Integer.parseInt(stringTokenizer.nextToken());
            roads.offer(new int[]{from, to, cost});
            totalCost += cost;
        }
        long minCost = getMinCost(n, roads);
        if (minCost > 0) {
            System.out.println(totalCost - minCost);
        } else {
            System.out.println(minCost);
        }
    }

    private static long getMinCost(int n, PriorityQueue<int[]> roads) {
        long minCost = 0;
        int count = n - 1;
        while (!roads.isEmpty()) {
            int[] current = roads.poll();
            if (connect(current[FROM], current[TO])) {
                minCost += current[COST];
                if (--count == 0) {
                    return minCost;
                }
            }
        }
        return -1;
    }

    private static boolean connect(int a, int b) {
        int rootA = find(a);
        int rootB = find(b);
        if (rootA < rootB) {
            roots[rootB] = rootA;
        } else if (rootA > rootB) {
            roots[rootA] = rootB;
        } else {
            return false;
        }
        return true;
    }

    private static int find(int value) {
        if (value == roots[value]) {
            return value;
        }
        return roots[value] = find(roots[value]);
    }
}
