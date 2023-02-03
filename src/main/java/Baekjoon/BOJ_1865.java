package Baekjoon;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class BOJ_1865 {

    private static final int MAX_VALUE = 50000000;
    private static final int FROM = 0;
    private static final int TO = 1;
    private static final int TIME = 2;

    public static void main(String[] args) throws IOException {
        BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(System.in));
        int t = Integer.parseInt(bufferedReader.readLine());
        StringBuilder answer = new StringBuilder();
        while (t-- > 0) {
            StringTokenizer stringTokenizer = new StringTokenizer(bufferedReader.readLine());
            int n = Integer.parseInt(stringTokenizer.nextToken());
            int m = Integer.parseInt(stringTokenizer.nextToken());
            int w = Integer.parseInt(stringTokenizer.nextToken());
            int[][] worlds = new int[m * 2 + w][3];
            for (int i = 0; i < m * 2; i += 2) {
                stringTokenizer = new StringTokenizer(bufferedReader.readLine());
                int from = Integer.parseInt(stringTokenizer.nextToken()) - 1;
                int to = Integer.parseInt(stringTokenizer.nextToken()) - 1;
                int time = Integer.parseInt(stringTokenizer.nextToken());
                worlds[i] = new int[]{from, to, time};
                worlds[i + 1] = new int[]{to, from, time};
            }
            for (int i = 0; i < w; i++) {
                stringTokenizer = new StringTokenizer(bufferedReader.readLine());
                worlds[m * 2 + i] = new int[]{Integer.parseInt(stringTokenizer.nextToken()) - 1, Integer.parseInt(stringTokenizer.nextToken()) - 1, -Integer.parseInt(stringTokenizer.nextToken())};
            }

            if (canGoPast(worlds, n)) {
                answer.append("YES\n");
            } else {
                answer.append("NO\n");
            }
        }
        System.out.println(answer);
    }

    private static boolean canGoPast(int[][] worlds, int n) {
        int[] times = new int[n];
        Arrays.fill(times, MAX_VALUE);
        for (int i = 0; i < n; i++) {
            for (int[] world : worlds) {
                int from = world[FROM];
                int to = world[TO];
                int time = world[TIME];
                if (times[to] > times[from] + time) {
                    times[to] = times[from] + time;
                    if (i == n - 1) {
                        return true;
                    }
                }
            }
        }
        return false;
    }
}
