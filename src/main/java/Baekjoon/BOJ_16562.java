package Baekjoon;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class BOJ_16562 {

    private static int[] roots;
    private static int[] costs;

    public static void main(String[] args) throws IOException {
        BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer stringTokenizer = new StringTokenizer(bufferedReader.readLine());
        int n = Integer.parseInt(stringTokenizer.nextToken());
        int m = Integer.parseInt(stringTokenizer.nextToken());
        int k = Integer.parseInt(stringTokenizer.nextToken());
        costs = new int[n];
        stringTokenizer = new StringTokenizer(bufferedReader.readLine());
        for (int i = 0; i < n; i++) {
            costs[i] = Integer.parseInt(stringTokenizer.nextToken());
        }
        roots = new int[n];
        for (int i = 1; i < n; i++) {
            roots[i] = i;
        }
        for (int i = 0; i < m; i++) {
            stringTokenizer = new StringTokenizer(bufferedReader.readLine());
            int v = Integer.parseInt(stringTokenizer.nextToken()) - 1;
            int w = Integer.parseInt(stringTokenizer.nextToken()) - 1;
            combine(v, w);
        }
        int cost = 0;
        for (int i = 0; i < n; i++) {
            if (roots[i] == i) {
                cost += costs[i];
            }
        }
        if (cost <= k) {
            System.out.println(cost);
        } else {
            System.out.println("Oh no");
        }
    }

    private static void combine(int a, int b) {
        int rootA = find(a);
        int rootB = find(b);
        if (rootA != rootB) {
            if (costs[rootA] > costs[rootB]) {
                roots[rootA] = rootB;
            } else {
                roots[rootB] = rootA;
            }
        }
    }

    private static int find(int value) {
        if (value == roots[value]) {
            return value;
        }
        return roots[value] = find(roots[value]);
    }
}
