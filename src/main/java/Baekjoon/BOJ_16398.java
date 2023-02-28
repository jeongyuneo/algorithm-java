package Baekjoon;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.StringTokenizer;

public class BOJ_16398 {

    private static final int FROM = 0;
    private static final int TO = 1;
    private static final int COST = 2;

    private static int[] roots;

    public static void main(String[] args) throws IOException {
        BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(System.in));
        int n = Integer.parseInt(bufferedReader.readLine());
        List<int[]> edges = new ArrayList<>();
        for (int from = 0; from < n; from++) {
            StringTokenizer stringTokenizer = new StringTokenizer(bufferedReader.readLine());
            for (int to = 0; to < n; to++) {
                int cost = Integer.parseInt(stringTokenizer.nextToken());
                if (from == to) {
                    continue;
                }
                edges.add(new int[]{from, to, cost});
            }
        }
        edges.sort(Comparator.comparing(edge -> edge[COST]));
        setRoots(n);
        long cost = 0;
        for (int[] edge : edges) {
            if (canCombine(edge[FROM], edge[TO])) {
                cost += edge[COST];
            }
        }
        System.out.println(cost);
    }

    private static void setRoots(int n) {
        roots = new int[n];
        for (int i = 1; i < n; i++) {
            roots[i] = i;
        }
    }

    private static boolean canCombine(int a, int b) {
        int rootA = find(a);
        int rootB = find(b);
        if (rootA == rootB) {
            return false;
        }
        roots[rootA] = rootB;
        return true;
    }

    private static int find(int value) {
        if (value == roots[value]) {
            return value;
        }
        return roots[value] = find(roots[value]);
    }
}
