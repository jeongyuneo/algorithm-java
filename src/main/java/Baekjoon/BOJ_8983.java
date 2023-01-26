package Baekjoon;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class BOJ_8983 {

    public static void main(String[] args) throws IOException {
        BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer stringTokenizer = new StringTokenizer(bufferedReader.readLine());
        int m = Integer.parseInt(stringTokenizer.nextToken());
        int n = Integer.parseInt(stringTokenizer.nextToken());
        int l = Integer.parseInt(stringTokenizer.nextToken());
        stringTokenizer = new StringTokenizer(bufferedReader.readLine());
        int[] launchers = new int[m];
        for (int i = 0; i < m; i++) {
            launchers[i] = Integer.parseInt(stringTokenizer.nextToken());
        }
        Arrays.sort(launchers);
        int catchableAnimals = 0;
        int launcherCount = launchers.length - 1;
        for (int i = 0; i < n; i++) {
            stringTokenizer = new StringTokenizer(bufferedReader.readLine());
            int x = Integer.parseInt(stringTokenizer.nextToken());
            int y = Integer.parseInt(stringTokenizer.nextToken());
            int start = 0;
            int end = launcherCount;
            while (start <= end) {
                int mid = (start + end) / 2;
                if (Math.abs(launchers[mid] - x) + y <= l) {
                    catchableAnimals++;
                    break;
                }
                if (launchers[mid] > x) {
                    end = mid - 1;
                } else {
                    start = mid + 1;
                }
            }
        }
        System.out.println(catchableAnimals);
    }
}
