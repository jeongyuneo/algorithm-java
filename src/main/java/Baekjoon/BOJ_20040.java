package Baekjoon;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class BOJ_20040 {

    private static int[] roots;

    public static void main(String[] args) throws IOException {
        BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer stringTokenizer = new StringTokenizer(bufferedReader.readLine());
        int n = Integer.parseInt(stringTokenizer.nextToken());
        int m = Integer.parseInt(stringTokenizer.nextToken());
        roots = new int[n];
        for (int i = 1; i < n; i++) {
            roots[i] = i;
        }
        int answer = 0;
        for (int order = 0; order < m; order++) {
            stringTokenizer = new StringTokenizer(bufferedReader.readLine());
            int from = Integer.parseInt(stringTokenizer.nextToken());
            int to = Integer.parseInt(stringTokenizer.nextToken());
            if (canNotUnion(from, to)) {
                answer = order + 1;
                break;
            }
        }
        System.out.println(answer);
    }

    private static boolean canNotUnion(int from, int to) {
        int rootFrom = find(from);
        int rootTo = find(to);
        if (rootFrom < rootTo) {
            roots[rootTo] = rootFrom;
        } else if (rootFrom > rootTo) {
            roots[rootFrom] = rootTo;
        } else {
            return true;
        }
        return false;
    }

    private static int find(int value) {
        if (value == roots[value]) {
            return value;
        }
        return roots[value] = find(roots[value]);
    }
}
