package Baekjoon;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class BOJ_1504 {

    private static final int NUMBER = 0;
    private static final int DISTANCE = 1;
    private static final int MAX_ACCUMULATED_DISTANCE = 200000000;
    private static final PriorityQueue<int[]> PRIORITY_QUEUE = new PriorityQueue<>(Comparator.comparing(vertex -> vertex[DISTANCE]));

    public static void main(String[] args) throws IOException {
        BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer stringTokenizer = new StringTokenizer(bufferedReader.readLine());
        int n = Integer.parseInt(stringTokenizer.nextToken());
        int e = Integer.parseInt(stringTokenizer.nextToken());
        List<int[]>[] undirectedGraph = new List[n];
        int[] distances = new int[n];
        for (int i = 0; i < n; i++) {
            undirectedGraph[i] = new ArrayList<>();
        }
        for (int i = 0; i < e; i++) {
            stringTokenizer = new StringTokenizer(bufferedReader.readLine());
            int a = Integer.parseInt(stringTokenizer.nextToken()) - 1;
            int b = Integer.parseInt(stringTokenizer.nextToken()) - 1;
            int c = Integer.parseInt(stringTokenizer.nextToken());
            undirectedGraph[a].add(new int[]{b, c});
            undirectedGraph[b].add(new int[]{a, c});
        }
        stringTokenizer = new StringTokenizer(bufferedReader.readLine());
        int v1 = Integer.parseInt(stringTokenizer.nextToken()) - 1;
        int v2 = Integer.parseInt(stringTokenizer.nextToken()) - 1;
        int minDistance = Math.min(getDistance(undirectedGraph, distances, 0, v1) + getDistance(undirectedGraph, distances, v1, v2) + getDistance(undirectedGraph, distances, v2, n - 1),
                getDistance(undirectedGraph, distances, 0, v2) + getDistance(undirectedGraph, distances, v2, v1) + getDistance(undirectedGraph, distances, v1, n - 1));
        if (minDistance < MAX_ACCUMULATED_DISTANCE) {
            System.out.println(minDistance);
        } else {
            System.out.println(-1);
        }
    }

    private static int getDistance(List<int[]>[] undirectedGraph, int[] distances, int from, int to) {
        Arrays.fill(distances, MAX_ACCUMULATED_DISTANCE);
        distances[from] = 0;
        PRIORITY_QUEUE.clear();
        for (int[] vertex : undirectedGraph[from]) {
            PRIORITY_QUEUE.offer(vertex);
            distances[vertex[NUMBER]] = vertex[DISTANCE];
        }
        while (!PRIORITY_QUEUE.isEmpty()) {
            int[] current = PRIORITY_QUEUE.poll();
            int number = current[NUMBER];
            if (number == to) {
                break;
            }

            for (int[] next : undirectedGraph[number]) {
                int nextNumber = next[NUMBER];
                int nextDistance = next[DISTANCE] + current[DISTANCE];
                if (distances[nextNumber] > nextDistance) {
                    distances[nextNumber] = nextDistance;
                    PRIORITY_QUEUE.offer(new int[]{nextNumber, nextDistance});
                }
            }
        }
        return distances[to];
    }
}
