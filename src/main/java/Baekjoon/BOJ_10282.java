package Baekjoon;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class BOJ_10282 {

    private static final int DEPENDENCY = 0;
    private static final int TIME = 1;

    public static void main(String[] args) throws IOException {
        BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(System.in));
        int testCase = Integer.parseInt(bufferedReader.readLine());
        StringBuilder result = new StringBuilder();
        PriorityQueue<int[]> virus = new PriorityQueue<>(Comparator.comparing(info -> info[TIME]));
        while (testCase-- > 0) {
            StringTokenizer stringTokenizer = new StringTokenizer(bufferedReader.readLine());
            int n = Integer.parseInt(stringTokenizer.nextToken());
            int d = Integer.parseInt(stringTokenizer.nextToken());
            int c = Integer.parseInt(stringTokenizer.nextToken());
            List<int[]>[] dependencies = new List[n + 1];
            for (int i = 1; i <= n; i++) {
                dependencies[i] = new ArrayList<>();
            }
            for (int i = 0; i < d; i++) {
                stringTokenizer = new StringTokenizer(bufferedReader.readLine());
                int a = Integer.parseInt(stringTokenizer.nextToken());
                int b = Integer.parseInt(stringTokenizer.nextToken());
                int time = Integer.parseInt(stringTokenizer.nextToken());
                dependencies[b].add(new int[]{a, time});
            }
            int time = 0;
            int computer = 0;
            boolean[] isVisited = new boolean[n + 1];
            int[] times = new int[n + 1];
            Arrays.fill(times, Integer.MAX_VALUE);
            times[c] = 0;
            virus.offer(new int[]{c, 0});
            while (!virus.isEmpty()) {
                int[] current = virus.poll();
                int from = current[DEPENDENCY];
                if (isVisited[from]) {
                    continue;
                }
                isVisited[from] = true;
                computer++;
                time = current[TIME];
                for (int[] next : dependencies[from]) {
                    if (times[next[DEPENDENCY]] > times[from] + next[TIME]) {
                        times[next[DEPENDENCY]] = times[from] + next[TIME];
                        virus.offer(new int[]{next[DEPENDENCY], times[next[DEPENDENCY]]});
                    }
                }
            }
            result.append(computer).append(" ").append(time).append("\n");
        }
        System.out.println(result);
    }
}
