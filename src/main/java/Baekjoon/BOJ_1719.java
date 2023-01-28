package Baekjoon;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class BOJ_1719 {

    private static final StringBuilder ANSWER = new StringBuilder();
    private static final int TO = 0;
    private static final int TIME = 1;
    private static final PriorityQueue<int[]> PRIORITY_QUEUE = new PriorityQueue<>(Comparator.comparing(info -> info[TIME]));

    private static List<int[]>[] adjList;
    private static int[] times;
    private static int[] paths;
    private static boolean[] isVisited;
    private static int n;

    public static void main(String[] args) throws IOException {
        BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer stringTokenizer = new StringTokenizer(bufferedReader.readLine());
        n = Integer.parseInt(stringTokenizer.nextToken());
        int m = Integer.parseInt(stringTokenizer.nextToken());
        adjList = new List[n];
        times = new int[n];
        paths = new int[n];
        isVisited = new boolean[n];
        for (int i = 0; i < n; i++) {
            adjList[i] = new ArrayList<>();
        }
        for (int i = 0; i < m; i++) {
            stringTokenizer = new StringTokenizer(bufferedReader.readLine());
            int from = Integer.parseInt(stringTokenizer.nextToken()) - 1;
            int to = Integer.parseInt(stringTokenizer.nextToken()) - 1;
            int time = Integer.parseInt(stringTokenizer.nextToken());
            adjList[from].add(new int[]{to, time});
            adjList[to].add(new int[]{from, time});
        }

        for (int i = 0; i < n; i++) {
            PRIORITY_QUEUE.clear();
            Arrays.fill(times, Integer.MAX_VALUE);
            Arrays.fill(paths, 0);
            Arrays.fill(isVisited, false);
            findMinDistance(i);
        }
        System.out.println(ANSWER);
    }

    private static void findMinDistance(int start) {
        PRIORITY_QUEUE.offer(new int[]{start, 0});
        times[start] = 0;
        paths[start] = start;
        while (!PRIORITY_QUEUE.isEmpty()) {
            int[] current = PRIORITY_QUEUE.poll();
            int to = current[TO];
            if (isVisited[to]) {
                continue;
            }
            isVisited[to] = true;
            for (int[] next : adjList[to]) {
                int nextTo = next[TO];
                int nextTime = times[to] + next[TIME];
                if (times[nextTo] > nextTime) {
                    times[nextTo] = nextTime;
                    paths[nextTo] = to;
                    PRIORITY_QUEUE.offer(new int[]{nextTo, nextTime});
                }
            }
        }
        updateAnswer(start);
    }

    private static void updateAnswer(int start) {
        for (int i = 0; i < n; i++) {
            if (i == start) {
                ANSWER.append("- ");
                continue;
            }
            ANSWER.append(findFirstPath(start, i)).append(" ");
        }
        ANSWER.append("\n");
    }

    private static int findFirstPath(int start, int end) {
        if (paths[end] == start) {
            return end + 1;
        }
        return findFirstPath(start, paths[end]);
    }
}
