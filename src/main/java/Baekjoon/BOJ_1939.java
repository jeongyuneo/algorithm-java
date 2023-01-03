package Baekjoon;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

public class BOJ_1939 {

    private static final int FROM = 0;
    private static final int TO = 1;
    private static final int LIMIT = 2;

    private static int[] roots;

    public static void main(String[] args) throws IOException {
        BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer stringTokenizer = new StringTokenizer(bufferedReader.readLine());
        int n = Integer.parseInt(stringTokenizer.nextToken());
        int m = Integer.parseInt(stringTokenizer.nextToken());
        PriorityQueue<int[]> islands = new PriorityQueue<>((island1, island2) -> island2[LIMIT] - island1[LIMIT]);
        for (int i = 0; i < m; i++) {
            stringTokenizer = new StringTokenizer(bufferedReader.readLine());
            int a = Integer.parseInt(stringTokenizer.nextToken()) - 1;
            int b = Integer.parseInt(stringTokenizer.nextToken()) - 1;
            int c = Integer.parseInt(stringTokenizer.nextToken());
            islands.offer(new int[]{a, b, c});
        }
        stringTokenizer = new StringTokenizer(bufferedReader.readLine());
        int start = Integer.parseInt(stringTokenizer.nextToken()) - 1;
        int end = Integer.parseInt(stringTokenizer.nextToken()) - 1;

        initialize(n);
        System.out.println(getMaxWeight(islands, start, end));
    }

    private static void initialize(int n) {
        roots = new int[n];
        for (int i = 0; i < n; i++) {
            roots[i] = i;
        }
    }

    private static int getMaxWeight(PriorityQueue<int[]> islands, int start, int end) {
        while (!islands.isEmpty()) {
            int[] island = islands.poll();
            join(island[FROM], island[TO]);
            if (isSameSet(start, end)) {
                return island[LIMIT];
            }
        }
        return 0;
    }

    private static boolean isSameSet(int from, int to) {
        return find(from) == find(to);
    }

    private static void join(int from, int to) {
        int fromRoot = find(from);
        int toRoot = find(to);
        if (fromRoot != toRoot) {
            roots[toRoot] = fromRoot;
        }
    }

    private static int find(int element) {
        if (roots[element] == element) {
            return element;
        }
        return roots[element] = find(roots[element]);
    }
}
