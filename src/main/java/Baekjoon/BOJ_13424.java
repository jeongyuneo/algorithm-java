package Baekjoon;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class BOJ_13424 {

    private static final int NUMBER = 0;
    private static final int DISTANCE = 1;
    private static final PriorityQueue<int[]> PRIORITY_QUEUE = new PriorityQueue<>(Comparator.comparing(info -> info[DISTANCE]));

    private static int[] distances;
    private static boolean[] isVisited;
    private static int[] totalDistances;

    public static void main(String[] args) throws IOException {
        BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(System.in));
        int testCase = Integer.parseInt(bufferedReader.readLine());
        StringBuilder answer = new StringBuilder();
        while (testCase-- > 0) {
            StringTokenizer stringTokenizer = new StringTokenizer(bufferedReader.readLine());
            int n = Integer.parseInt(stringTokenizer.nextToken());
            int m = Integer.parseInt(stringTokenizer.nextToken());
            List<int[]>[] rooms = new List[n];
            distances = new int[n];
            isVisited = new boolean[n];
            totalDistances = new int[n];
            for (int i = 0; i < n; i++) {
                rooms[i] = new ArrayList<>();
            }
            for (int i = 0; i < m; i++) {
                stringTokenizer = new StringTokenizer(bufferedReader.readLine());
                int from = Integer.parseInt(stringTokenizer.nextToken()) - 1;
                int to = Integer.parseInt(stringTokenizer.nextToken()) - 1;
                int distance = Integer.parseInt(stringTokenizer.nextToken());
                rooms[from].add(new int[]{to, distance});
                rooms[to].add(new int[]{from, distance});
            }
            int k = Integer.parseInt(bufferedReader.readLine());
            stringTokenizer = new StringTokenizer(bufferedReader.readLine());
            for (int i = 0; i < k; i++) {
                PRIORITY_QUEUE.clear();
                Arrays.fill(distances, Integer.MAX_VALUE);
                Arrays.fill(isVisited, false);
                findMinDistance(rooms, n, Integer.parseInt(stringTokenizer.nextToken()) - 1);
            }
            answer.append(getMeetingRoom(n)).append("\n");
        }
        System.out.println(answer);
    }

    private static void findMinDistance(List<int[]>[] rooms, int n, int start) {
        PRIORITY_QUEUE.offer(new int[]{start, 0});
        distances[start] = 0;
        while (!PRIORITY_QUEUE.isEmpty()) {
            int[] current = PRIORITY_QUEUE.poll();
            int from = current[NUMBER];
            if (isVisited[from]) {
                continue;
            }
            isVisited[from] = true;
            for (int[] next : rooms[from]) {
                int to = next[NUMBER];
                int distance = distances[from] + next[DISTANCE];
                if (distances[to] > distance) {
                    distances[to] = distance;
                    PRIORITY_QUEUE.offer(new int[]{to, distance});
                }
            }
        }
        sumDistances(n);
    }

    private static void sumDistances(int n) {
        for (int i = 0; i < n; i++) {
            totalDistances[i] += distances[i];
        }
    }

    private static int getMeetingRoom(int n) {
        int minDistance = Integer.MAX_VALUE;
        int meetingRoom = 0;
        for (int i = 0; i < n; i++) {
            if (minDistance > totalDistances[i]) {
                minDistance = totalDistances[i];
                meetingRoom = i + 1;
            }
        }
        return meetingRoom;
    }
}
