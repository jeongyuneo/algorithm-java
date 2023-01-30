package Baekjoon;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class BOJ_14938 {

    private static final int NUMBER = 0;
    private static final int DISTANCE = 1;
    private static final PriorityQueue<int[]> PRIORITY_QUEUE = new PriorityQueue<>(Comparator.comparing(info -> info[DISTANCE]));

    private static int[] items;
    private static int[] distances;
    private static boolean[] isVisited;
    private static List<int[]>[] map;
    private static int n;
    private static int m;
    private static int maxItem;

    public static void main(String[] args) throws IOException {
        BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer stringTokenizer = new StringTokenizer(bufferedReader.readLine());
        n = Integer.parseInt(stringTokenizer.nextToken());
        m = Integer.parseInt(stringTokenizer.nextToken());
        int r = Integer.parseInt(stringTokenizer.nextToken());
        items = new int[n];
        distances = new int[n];
        isVisited = new boolean[n];
        map = new List[n];
        stringTokenizer = new StringTokenizer(bufferedReader.readLine());
        for (int i = 0; i < n; i++) {
            items[i] = Integer.parseInt(stringTokenizer.nextToken());
            map[i] = new ArrayList<>();
        }
        while (r-- > 0) {
            stringTokenizer = new StringTokenizer(bufferedReader.readLine());
            int from = Integer.parseInt(stringTokenizer.nextToken()) - 1;
            int to = Integer.parseInt(stringTokenizer.nextToken()) - 1;
            int distance = Integer.parseInt(stringTokenizer.nextToken());
            map[from].add(new int[]{to, distance});
            map[to].add(new int[]{from, distance});
        }
        for (int i = 0; i < n; i++) {
            Arrays.fill(distances, Integer.MAX_VALUE);
            Arrays.fill(isVisited, false);
            findMinDistance(i);
        }
        System.out.println(maxItem);
    }

    private static void findMinDistance(int start) {
        PRIORITY_QUEUE.offer(new int[]{start, 0});
        distances[start] = 0;
        while (!PRIORITY_QUEUE.isEmpty()) {
            int[] current = PRIORITY_QUEUE.poll();
            int from = current[NUMBER];
            if (isVisited[from]) {
                continue;
            }
            isVisited[from] = true;
            for (int[] next : map[from]) {
                int to = next[NUMBER];
                int distance = distances[from] + next[DISTANCE];
                if (distances[to] > distance) {
                    distances[to] = distance;
                    PRIORITY_QUEUE.offer(new int[]{to, distance});
                }
            }
        }
        updateMaxItem();
    }

    private static void updateMaxItem() {
        int item = 0;
        for (int i = 0; i < n; i++) {
            if (distances[i] <= m) {
                item += items[i];
            }
            maxItem = Math.max(maxItem, item);
        }
    }
}
