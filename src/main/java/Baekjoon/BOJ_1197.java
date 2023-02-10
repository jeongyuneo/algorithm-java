package Baekjoon;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.Comparator;
import java.util.StringTokenizer;

public class BOJ_1197 {

    private static final int FROM = 0;
    private static final int TO = 1;
    private static final int WEIGHT = 2;

    private static int[][] edges;
    private static int[] parents;

    public static void main(String[] args) throws IOException {
        BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer stringTokenizer = new StringTokenizer(bufferedReader.readLine());
        int v = Integer.parseInt(stringTokenizer.nextToken());
        int e = Integer.parseInt(stringTokenizer.nextToken());
        edges = new int[e][3];
        parents = new int[v];
        for (int i = 0; i < v; i++) {
            parents[i] = i;
        }
        for (int i = 0; i < e; i++) {
            stringTokenizer = new StringTokenizer(bufferedReader.readLine());
            edges[i] = new int[]{Integer.parseInt(stringTokenizer.nextToken()) - 1, Integer.parseInt(stringTokenizer.nextToken()) - 1, Integer.parseInt(stringTokenizer.nextToken())};
        }
        Arrays.sort(edges, Comparator.comparing(edge -> edge[WEIGHT]));
        System.out.println(getMinWeight(v));
    }

    private static int getMinWeight(int v) {
        int combinedCount = 1;
        int weight = 0;
        for (int[] edge : edges) {
            if (canCombine(edge[FROM], edge[TO])) {
                weight += edge[WEIGHT];
                if (++combinedCount == v) {
                    return weight;
                }
            }
        }
        return weight;
    }

    private static boolean canCombine(int a, int b) {
        int rootA = find(a);
        int rootB = find(b);
        if (rootA == rootB) {
            return false;
        } else if (rootA > rootB) {
            parents[rootA] = rootB;
        } else {
            parents[rootB] = rootA;
        }
        return true;
    }

    private static int find(int value) {
        if (parents[value] == value) {
            return value;
        }
        return parents[value] = find(parents[value]);
    }
}
