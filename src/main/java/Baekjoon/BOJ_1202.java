package Baekjoon;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.Comparator;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

public class BOJ_1202 {

    private static final PriorityQueue<Integer> TAKING_JEWELS = new PriorityQueue<>(Comparator.reverseOrder());
    private static final int WEIGHT = 0;
    private static final int COST = 1;

    public static void main(String[] args) throws IOException {
        BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer stringTokenizer = new StringTokenizer(bufferedReader.readLine());
        int n = Integer.parseInt(stringTokenizer.nextToken());
        int k = Integer.parseInt(stringTokenizer.nextToken());
        int[][] jewels = new int[n][2];
        for (int i = 0; i < n; i++) {
            stringTokenizer = new StringTokenizer(bufferedReader.readLine());
            jewels[i][WEIGHT] = Integer.parseInt(stringTokenizer.nextToken());
            jewels[i][COST] = Integer.parseInt(stringTokenizer.nextToken());
        }
        int[] bags = new int[k];
        for (int i = 0; i < k; i++) {
            bags[i] = Integer.parseInt(bufferedReader.readLine());
        }
        Arrays.sort(jewels, Comparator.comparing(jewel -> jewel[WEIGHT]));
        Arrays.sort(bags);
        long sum = 0;
        for (int i = 0, j = 0; i < k; i++) {
            while (j < n && jewels[j][WEIGHT] <= bags[i]) {
                TAKING_JEWELS.offer(jewels[j++][COST]);
            }
            if (!TAKING_JEWELS.isEmpty()) {
                sum += TAKING_JEWELS.poll();
            }
        }
        System.out.println(sum);
    }
}
