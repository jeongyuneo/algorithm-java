package Baekjoon;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class BOJ_1238 {

    private static final int NUMBER = 0;
    private static final int TIME = 1;
    private static final PriorityQueue<int[]> PRIORITY_QUEUE = new PriorityQueue<>(Comparator.comparing(town -> town[TIME]));

    public static void main(String[] args) throws IOException {
        BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer stringTokenizer = new StringTokenizer(bufferedReader.readLine());
        int n = Integer.parseInt(stringTokenizer.nextToken());
        int m = Integer.parseInt(stringTokenizer.nextToken());
        int x = Integer.parseInt(stringTokenizer.nextToken()) - 1;
        List<int[]>[] towns = new List[n];
        for (int i = 0; i < n; i++) {
            towns[i] = new ArrayList<>();
        }
        for (int i = 0; i < m; i++) {
            stringTokenizer = new StringTokenizer(bufferedReader.readLine());
            int from = Integer.parseInt(stringTokenizer.nextToken()) - 1;
            int to = Integer.parseInt(stringTokenizer.nextToken()) - 1;
            int time = Integer.parseInt(stringTokenizer.nextToken());
            towns[from].add(new int[]{to, time});
        }
        int[] distances = new int[n];
        int maxTime = 0;
        for (int destination = 0; destination < n; destination++) {
            if (destination == x) {
                continue;
            }
            maxTime = Math.max(maxTime, getDistance(towns, distances, x, destination) + getDistance(towns, distances, destination, x));
        }
        System.out.println(maxTime);
    }

    private static int getDistance(List<int[]>[] towns, int[] distances, int start, int destination) {
        Arrays.fill(distances, Integer.MAX_VALUE);
        distances[start] = 0;
        PRIORITY_QUEUE.clear();
        PRIORITY_QUEUE.offer(new int[]{start, 0});
        while (!PRIORITY_QUEUE.isEmpty()) {
            int[] current = PRIORITY_QUEUE.poll();
            int number = current[NUMBER];
            if (number == destination) {
                return current[TIME];
            }
            for (int[] town : towns[number]) {
                int nextTown = town[NUMBER];
                int nextTime = distances[number] + town[TIME];
                if (distances[nextTown] > nextTime) {
                    distances[nextTown] = nextTime;
                    PRIORITY_QUEUE.offer(new int[]{nextTown, nextTime});
                }
            }
        }
        return 0;
    }
}
