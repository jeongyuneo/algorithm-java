package Baekjoon;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class BOJ_2805 {

    public static void main(String[] args) throws IOException {
        BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer stringTokenizer = new StringTokenizer(bufferedReader.readLine());
        int n = Integer.parseInt(stringTokenizer.nextToken());
        int m = Integer.parseInt(stringTokenizer.nextToken());
        int[] trees = new int[n];
        stringTokenizer = new StringTokenizer(bufferedReader.readLine());
        int maxHeight = 0;
        for (int i = 0; i < n; i++) {
            int tree = Integer.parseInt(stringTokenizer.nextToken());
            trees[i] = tree;
            if (tree > maxHeight) {
                maxHeight = tree;
            }
        }
        Arrays.sort(trees);
        System.out.println(search(trees, 0, maxHeight, m));
    }

    private static long search(int[] trees, int minHeight, int maxHeight, int m) {
        while (minHeight <= maxHeight) {
            int mid = (minHeight + maxHeight) / 2;
            if (cut(trees, mid) < m) {
                maxHeight = mid - 1;
            } else {
                minHeight = mid + 1;
            }
        }
        return maxHeight;
    }

    private static long cut(int[] trees, int mid) {
        long totalCutTree = 0;
        for (int tree : trees) {
            if (tree > mid) {
                totalCutTree += tree - mid;
            }
        }
        return totalCutTree;
    }
}
