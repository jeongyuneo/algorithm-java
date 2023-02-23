package Baekjoon;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class BOJ_13911 {

    private static final int NUMBER = 0;
    private static final int DISTANCE = 1;

    public static void main(String[] args) throws IOException {
        BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer stringTokenizer = new StringTokenizer(bufferedReader.readLine());
        int v = Integer.parseInt(stringTokenizer.nextToken());
        int e = Integer.parseInt(stringTokenizer.nextToken());
        boolean[] isMacdonald = new boolean[v];
        boolean[] isStarbucks = new boolean[v];
        List<int[]>[] houses = new List[v];
        for (int i = 0; i < v; i++) {
            houses[i] = new ArrayList<>();
        }
        List<Integer> macdonalds = new ArrayList<>();
        List<Integer> starbuckses = new ArrayList<>();
        for (int i = 0; i < e; i++) {
            stringTokenizer = new StringTokenizer(bufferedReader.readLine());
            int from = Integer.parseInt(stringTokenizer.nextToken()) - 1;
            int to = Integer.parseInt(stringTokenizer.nextToken()) - 1;
            int distance = Integer.parseInt(stringTokenizer.nextToken());
            houses[from].add(new int[]{to, distance});
            houses[to].add(new int[]{from, distance});
        }
        PriorityQueue<int[]> priorityQueue = new PriorityQueue<>(Comparator.comparing(house -> house[DISTANCE]));
        int[] distancesFromMacdonald = new int[v];
        Arrays.fill(distancesFromMacdonald, Integer.MAX_VALUE);
        boolean[] isVisited = new boolean[v];
        stringTokenizer = new StringTokenizer(bufferedReader.readLine());
        int m = Integer.parseInt(stringTokenizer.nextToken());
        int macDistrict = Integer.parseInt(stringTokenizer.nextToken());
        stringTokenizer = new StringTokenizer(bufferedReader.readLine());
        for (int i = 0; i < m; i++) {
            int macdonald = Integer.parseInt(stringTokenizer.nextToken()) - 1;
            isMacdonald[macdonald] = true;
            distancesFromMacdonald[macdonald] = 0;
            priorityQueue.offer(new int[]{macdonald, 0});
        }
        findClosestDistance(priorityQueue, distancesFromMacdonald, isVisited, isMacdonald, houses, macDistrict);
        priorityQueue.clear();
        int[] distancesFromStarbucks = new int[v];
        Arrays.fill(distancesFromStarbucks, Integer.MAX_VALUE);
        Arrays.fill(isVisited, false);
        stringTokenizer = new StringTokenizer(bufferedReader.readLine());
        int s = Integer.parseInt(stringTokenizer.nextToken());
        int starbucksDistrict = Integer.parseInt(stringTokenizer.nextToken());
        stringTokenizer = new StringTokenizer(bufferedReader.readLine());
        for (int i = 0; i < s; i++) {
            int starbucks = Integer.parseInt(stringTokenizer.nextToken()) - 1;
            isStarbucks[starbucks] = true;
            starbuckses.add(starbucks);
            distancesFromStarbucks[starbucks] = 0;
            priorityQueue.offer(new int[]{starbucks, 0});
        }
        findClosestDistance(priorityQueue, distancesFromStarbucks, isVisited, isStarbucks, houses, starbucksDistrict);
        int minDistance = Integer.MAX_VALUE;
        for (int i = 0; i < v; i++) {
            if (distancesFromMacdonald[i] != 0 && distancesFromStarbucks[i] != 0 && distancesFromMacdonald[i] <= macDistrict && distancesFromStarbucks[i] <= starbucksDistrict) {
                minDistance = Math.min(minDistance, distancesFromMacdonald[i] + distancesFromStarbucks[i]);
            }
        }
        if (minDistance != Integer.MAX_VALUE) {
            System.out.println(minDistance);
        } else {
            System.out.println(-1);
        }
    }

    private static void findClosestDistance(PriorityQueue<int[]> priorityQueue, int[] distances, boolean[] isVisited, boolean[] isStore, List<int[]>[] houses, int district) {
        while (!priorityQueue.isEmpty()) {
            int[] current = priorityQueue.poll();
            int from = current[NUMBER];
            if (isVisited[from]) {
                continue;
            }
            isVisited[from] = true;
            for (int[] next : houses[from]) {
                int to = next[NUMBER];
                int distance = distances[from] + next[DISTANCE];
                if (distances[to] > distance) {
                    distances[to] = distance;
                    priorityQueue.offer(new int[]{to, distance});
                }
            }
        }
    }
}
