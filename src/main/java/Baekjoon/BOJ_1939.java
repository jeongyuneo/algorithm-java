package Baekjoon;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class BOJ_1939 {

    private static final Queue<Integer> QUEUE = new ArrayDeque<>();
    private static final int NUMBER = 0;
    private static final int LIMIT = 1;

    private static List<int[]>[] islands;
    private static boolean[] isVisited;
    private static int start;
    private static int end;

    public static void main(String[] args) throws IOException {
        BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer stringTokenizer = new StringTokenizer(bufferedReader.readLine());
        int n = Integer.parseInt(stringTokenizer.nextToken());
        int m = Integer.parseInt(stringTokenizer.nextToken());
        islands = new List[n];
        for (int i = 0; i < n; i++) {
            islands[i] = new ArrayList<>();
        }
        int minWeight = 1000000001;
        int maxWeight = 0;
        for (int i = 0; i < m; i++) {
            stringTokenizer = new StringTokenizer(bufferedReader.readLine());
            int a = Integer.parseInt(stringTokenizer.nextToken()) - 1;
            int b = Integer.parseInt(stringTokenizer.nextToken()) - 1;
            int c = Integer.parseInt(stringTokenizer.nextToken());
            islands[a].add(new int[]{b, c});
            islands[b].add(new int[]{a, c});
            minWeight = Math.min(minWeight, c);
            maxWeight = Math.max(maxWeight, c);
        }
        stringTokenizer = new StringTokenizer(bufferedReader.readLine());
        start = Integer.parseInt(stringTokenizer.nextToken()) - 1;
        end = Integer.parseInt(stringTokenizer.nextToken()) - 1;

        isVisited = new boolean[n];
        while (minWeight <= maxWeight) {
            int midWeight = (minWeight + maxWeight) / 2;
            if (canPathAllBridges(midWeight)) {
                minWeight = midWeight + 1;
            } else {
                maxWeight = midWeight - 1;
            }
        }
        System.out.println(minWeight);
    }

    private static boolean canPathAllBridges(int weight) {
        Arrays.fill(isVisited, false);
        QUEUE.clear();
        QUEUE.offer(start);
        while (!QUEUE.isEmpty()) {
            int island = QUEUE.poll();
            if (island == end) {
                return true;
            }
            for (int[] next : islands[island]) {
                int nextIsland = next[NUMBER];
                if (!isVisited[nextIsland] && weight < next[LIMIT]) {
                    isVisited[nextIsland] = true;
                    QUEUE.offer(nextIsland);
                }
            }
        }
        return false;
    }
}
