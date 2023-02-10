package Baekjoon;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class BOJ_2211 {

    private static final int FROM = 0;
    private static final int TO = 1;
    private static final int TIME = 2;

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
            int time = Integer.parseInt(stringTokenizer.nextToken());
            edges[from].add(new int[]{from, to, time});
            edges[to].add(new int[]{to, from, time});
        }
        int[] times = new int[n];
        Arrays.fill(times, Integer.MAX_VALUE);
        List<int[]> networks = new ArrayList<>();
        PriorityQueue<int[]> priorityQueue = new PriorityQueue<>(Comparator.comparing(edge -> edge[TIME]));
        priorityQueue.offer(new int[]{0, 0, 0});
        times[0] = 0;
        boolean[] isVisited = new boolean[n];
        while (!priorityQueue.isEmpty()) {
            int[] current = priorityQueue.poll();
            int from = current[FROM];
            int to = current[TO];
            if (isVisited[to]) {
                continue;
            }
            isVisited[to] = true;
            if (from != to) {
                networks.add(new int[]{from + 1, to + 1});
            }
            for (int[] next : edges[to]) {
                int nextTo = next[TO];
                int time = times[to] + next[TIME];
                if (times[nextTo] > time) {
                    times[nextTo] = time;
                    priorityQueue.offer(new int[]{to, nextTo, time});
                }
            }
        }
        StringBuilder answer = new StringBuilder();
        answer.append(networks.size()).append("\n");
        for (int[] network : networks) {
            answer.append(network[FROM]).append(" ").append(network[TO]).append("\n");
        }
        System.out.println(answer);
    }
}
