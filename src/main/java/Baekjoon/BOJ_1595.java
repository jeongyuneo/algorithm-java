package Baekjoon;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class BOJ_1595 {

    private static final int MAX = 10_000;
    private static final int CITY = 0;
    private static final int DISTANCE = 1;

    private static int max;
    private static boolean[] isVisited;

    public static void main(String[] args) throws IOException {
        BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(System.in));
        List<int[]>[] roads = new List[MAX];
        for (int i = 0; i < MAX; i++) {
            roads[i] = new ArrayList<>();
        }
        Set<Integer> cities = new HashSet<>();
        isVisited = new boolean[MAX];
        while (true) {
            String line = bufferedReader.readLine();
            if (line == null || line.isEmpty()) {
                break;
            }
            StringTokenizer stringTokenizer = new StringTokenizer(line);
            int from = Integer.parseInt(stringTokenizer.nextToken()) - 1;
            int to = Integer.parseInt(stringTokenizer.nextToken()) - 1;
            int distance = Integer.parseInt(stringTokenizer.nextToken());
            roads[from].add(new int[]{to, distance});
            roads[to].add(new int[]{from, distance});
            cities.add(from);
            cities.add(to);
        }
        for (int city : cities) {
            findRoot(roads, city, 0);
        }
        System.out.println(max);
    }

    private static void findRoot(List<int[]>[] roads, int city, int sum) {
        isVisited[city] = true;
        max = Math.max(max, sum);
        for (int[] next : roads[city]) {
            if (!isVisited[next[CITY]]) {
                findRoot(roads, next[CITY], sum + next[DISTANCE]);
            }
        }
        isVisited[city] = false;
    }
}
