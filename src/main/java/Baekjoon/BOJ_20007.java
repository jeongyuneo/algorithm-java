package Baekjoon;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class BOJ_20007 {

    private static final int HOUSE = 0;
    private static final int DISTANCE = 1;

    public static void main(String[] args) throws IOException {
        BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer stringTokenizer = new StringTokenizer(bufferedReader.readLine());
        int n = Integer.parseInt(stringTokenizer.nextToken());
        int m = Integer.parseInt(stringTokenizer.nextToken());
        int x = Integer.parseInt(stringTokenizer.nextToken());
        int y = Integer.parseInt(stringTokenizer.nextToken());
        List<int[]>[] houses = new List[n];
        for (int i = 0; i < n; i++) {
            houses[i] = new ArrayList<>();
        }
        for (int i = 0; i < m; i++) {
            stringTokenizer = new StringTokenizer(bufferedReader.readLine());
            int a = Integer.parseInt(stringTokenizer.nextToken());
            int b = Integer.parseInt(stringTokenizer.nextToken());
            int distance = Integer.parseInt(stringTokenizer.nextToken());
            houses[a].add(new int[]{b, distance});
            houses[b].add(new int[]{a, distance});
        }
        int[] minDistances = getMinDistances(houses, n, x, y);
        Arrays.sort(minDistances);
        System.out.println(getDay(minDistances, n, x));
    }

    private static int getDay(int[] minDistances, int n, int x) {
        if (minDistances[n - 1] * 2 > x) {
            return -1;
        }
        int day = 0;
        int distance = 0;
        int idx = 0;
        while (idx < n) {
            while (idx < n && distance + minDistances[idx] * 2 <= x) {
                distance += minDistances[idx++] * 2;
            }
            distance = 0;
            day++;
        }
        return day;
    }

    private static int[] getMinDistances(List<int[]>[] houses, int n, int x, int y) {
        PriorityQueue<int[]> moves = new PriorityQueue<>(Comparator.comparing(move -> move[DISTANCE]));
        boolean[] isVisited = new boolean[n];
        int[] distances = new int[n];
        Arrays.fill(distances, Integer.MAX_VALUE);
        distances[y] = 0;
        moves.offer(new int[]{y, 0});
        while (!moves.isEmpty()) {
            int[] current = moves.poll();
            int from = current[HOUSE];
            if (isVisited[from]) {
                continue;
            }
            isVisited[from] = true;
            for (int[] next : houses[from]) {
                int to = next[HOUSE];
                int distance = current[DISTANCE] + next[DISTANCE];
                if (distances[to] > distance) {
                    distances[to] = distance;
                    moves.offer(new int[]{to, distance});
                }
            }
        }
        return distances;
    }
}
