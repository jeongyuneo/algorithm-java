package Baekjoon;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.StringTokenizer;

public class BOJ_1922 {

    private static final int FROM = 0;
    private static final int TO = 1;
    private static final int COST = 2;

    private static int[] roots;

    public static void main(String[] args) throws IOException {
        BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(System.in));
        int n = Integer.parseInt(bufferedReader.readLine());
        int m = Integer.parseInt(bufferedReader.readLine());
        roots = new int[n];
        for (int i = 0; i < n; i++) {
            roots[i] = i;
        }
        List<int[]> computers = new ArrayList<>();
        for (int i = 0; i < m; i++) {
            StringTokenizer stringTokenizer = new StringTokenizer(bufferedReader.readLine());
            int from = Integer.parseInt(stringTokenizer.nextToken()) - 1;
            int to = Integer.parseInt(stringTokenizer.nextToken()) - 1;
            int cost = Integer.parseInt(stringTokenizer.nextToken());
            computers.add(new int[]{from, to, cost});
        }
        computers.sort(Comparator.comparing(computer -> computer[COST]));
        System.out.println(getCost(computers));
    }

    private static int getCost(List<int[]> computers) {
        int cost = 0;
        for (int[] computer : computers) {
            if (canCombine(computer[FROM], computer[TO])) {
                cost += computer[COST];
            }
        }
        return cost;
    }

    private static boolean canCombine(int a, int b) {
        int rootA = find(a);
        int rootB = find(b);
        if (rootA == rootB) {
            return false;
        }
        roots[rootB] = rootA;
        return true;
    }

    private static int find(int value) {
        if (roots[value] == value) {
            return value;
        }
        return roots[value] = find(roots[value]);
    }
}
