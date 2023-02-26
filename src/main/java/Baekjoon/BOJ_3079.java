package Baekjoon;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class BOJ_3079 {

    public static void main(String[] args) throws IOException {
        BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer stringTokenizer = new StringTokenizer(bufferedReader.readLine());
        int n = Integer.parseInt(stringTokenizer.nextToken());
        int m = Integer.parseInt(stringTokenizer.nextToken());
        int[] times = new int[n];
        for (int i = 0; i < n; i++) {
            times[i] = Integer.parseInt(bufferedReader.readLine());
        }
        Arrays.sort(times);
        long min = 1;
        long max = (long) m * times[n - 1];
        while (min <= max) {
            long mid = (min + max) / 2;
            if (isLessThen(times, m, mid)) {
                min = mid + 1;
            } else {
                max = mid - 1;
            }
        }
        System.out.println(min);
    }

    private static boolean isLessThen(int[] times, int m, long mid) {
        long count = 0;
        for (int time : times) {
            count += mid / time;
            if (count >= m) {
                return false;
            }
        }
        return true;
    }
}
