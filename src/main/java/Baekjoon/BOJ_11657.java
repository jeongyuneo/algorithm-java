package Baekjoon;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.StringTokenizer;

public class BOJ_11657 {

    private static final StringBuilder ANSWER = new StringBuilder();
    public static final int FROM = 0;
    public static final int TO = 1;
    public static final int TIME = 2;

    public static void main(String[] args) throws IOException {
        BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer stringTokenizer = new StringTokenizer(bufferedReader.readLine());
        int n = Integer.parseInt(stringTokenizer.nextToken());
        int m = Integer.parseInt(stringTokenizer.nextToken());
        List<int[]> cities = new ArrayList<>();
        while (m-- > 0) {
            stringTokenizer = new StringTokenizer(bufferedReader.readLine());
            cities.add(new int[]{Integer.parseInt(stringTokenizer.nextToken()) - 1, Integer.parseInt(stringTokenizer.nextToken()) - 1, Integer.parseInt(stringTokenizer.nextToken())});
        }
        updateMinTime(n, cities);
        System.out.println(ANSWER);
    }

    private static void updateMinTime(int n, List<int[]> cities) {
        long[] times = new long[n];
        Arrays.fill(times, Long.MAX_VALUE);
        times[0] = 0;
        for (int from = 0; from < n; from++) {
            for (int[] city : cities) {
                if (times[city[FROM]] == Long.MAX_VALUE) {
                    continue;
                }
                if (times[city[TO]] > times[city[FROM]] + city[TIME]) {
                    times[city[TO]] = times[city[FROM]] + city[TIME];
                    if (from == n - 1) {
                        ANSWER.append(-1);
                        return;
                    }
                }
            }
        }
        for (int i = 1; i < n; i++) {
            if (times[i] == Long.MAX_VALUE) {
                ANSWER.append("-1\n");
            } else {
                ANSWER.append(times[i]).append("\n");
            }
        }
    }
}
