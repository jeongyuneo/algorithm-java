package Baekjoon;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class BOJ_1477 {

    public static void main(String[] args) throws IOException {
        BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer stringTokenizer = new StringTokenizer(bufferedReader.readLine());
        int n = Integer.parseInt(stringTokenizer.nextToken());
        int m = Integer.parseInt(stringTokenizer.nextToken());
        int l = Integer.parseInt(stringTokenizer.nextToken());
        int[] locations = new int[n + 2];
        stringTokenizer = new StringTokenizer(bufferedReader.readLine());
        for (int i = 0; i < n; i++) {
            locations[i] = Integer.parseInt(stringTokenizer.nextToken());
        }
        locations[n] = 0;
        locations[n + 1] = l;
        n += 2;
        Arrays.sort(locations);
        int min = 1;
        int max = l - 1;
        while (min <= max) {
            int mid = (min + max) / 2;
            int count = 0;
            for (int i = 1; i < n; i++) {
                count += (locations[i] - locations[i - 1] - 1) / mid;
            }
            if (count > m) {
                min = mid + 1;
            } else {
                max = mid - 1;
            }
        }
        System.out.println(min);
    }
}
