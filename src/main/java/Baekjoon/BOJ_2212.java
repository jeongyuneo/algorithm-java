package Baekjoon;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class BOJ_2212 {

    public static void main(String[] args) throws IOException {
        BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(System.in));
        int n = Integer.parseInt(bufferedReader.readLine());
        int k = Integer.parseInt(bufferedReader.readLine());
        int[] sensors = new int[n];
        StringTokenizer stringTokenizer = new StringTokenizer(bufferedReader.readLine());
        for (int i = 0; i < n; i++) {
            sensors[i] = Integer.parseInt(stringTokenizer.nextToken());
        }
        Arrays.sort(sensors);
        int[] gaps = new int[--n];
        for (int i = 0; i < n; i++) {
            gaps[i] = sensors[i + 1] - sensors[i];
        }
        Arrays.sort(gaps);
        int sum = 0;
        for (int i = 0; i <= n - k; i++) {
            sum += gaps[i];
        }
        System.out.println(sum);
    }
}
