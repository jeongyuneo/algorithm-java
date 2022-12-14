package Baekjoon;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class BOJ_1916 {

    private static final int STARTING_CITY = 0;
    private static final int ARRIVING_CITY = 1;
    private static final int COST = 2;

    public static void main(String[] args) throws IOException {
        BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(System.in));
        int n = Integer.parseInt(bufferedReader.readLine());
        int m = Integer.parseInt(bufferedReader.readLine());
        List<int[]>[] busInfos = new List[n+1];
        for (int i = 1; i <= n; i++) {
            busInfos[i] = new ArrayList<>();
        }
        for (int i = 0; i < m; i++) {
            StringTokenizer stringTokenizer = new StringTokenizer(bufferedReader.readLine());
            int from = Integer.parseInt(stringTokenizer.nextToken());
            int to = Integer.parseInt(stringTokenizer.nextToken());
            int cost = Integer.parseInt(stringTokenizer.nextToken());
            busInfos[from].add(new int[]{from, to, cost});
        }

        StringTokenizer stringTokenizer = new StringTokenizer(bufferedReader.readLine());
        int start = Integer.parseInt(stringTokenizer.nextToken());
        int arrive = Integer.parseInt(stringTokenizer.nextToken());
        int[] costs = new int[n + 1];
        Arrays.fill(costs, Integer.MAX_VALUE);
        costs[start] = 0;
        PriorityQueue<int[]> priorityQueue = new PriorityQueue<>(Comparator.comparing(busInfo -> busInfo[COST]));
        priorityQueue.addAll(busInfos[start]);
        while (!priorityQueue.isEmpty()) {
            int[] current = priorityQueue.poll();
            int from = current[STARTING_CITY];
            int to = current[ARRIVING_CITY];
            int cost = current[COST];

            if (costs[to] == 0) {
                continue;
            }

            int nextCost = costs[from] + cost;
            if (costs[to] > nextCost) {
                costs[to] = nextCost;
                for (int[] city : busInfos[to]) {
                    priorityQueue.offer(city);
                }
            }
        }
        System.out.println(costs[arrive]);
    }
}
