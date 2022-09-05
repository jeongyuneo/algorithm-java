package Baekjoon;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class BOJ_2110 {

    public static void main(String[] args) throws IOException {
        BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer stringTokenizer = new StringTokenizer(bufferedReader.readLine());
        int n = Integer.parseInt(stringTokenizer.nextToken());
        int c = Integer.parseInt(stringTokenizer.nextToken());
        int[] houses = new int[n];
        for (int i = 0; i < n; i++) {
            houses[i] = Integer.parseInt(bufferedReader.readLine());
        }
        Arrays.sort(houses);

        int min = 1;
        int max = houses[n - 1] + houses[0];
        int answer = 0;
        while (min <= max) {
            int mid = (min + max) / 2;
            int previous = houses[0];
            int count = 1;
            for (int i = 1, housesLength = houses.length; i < housesLength; i++) {
                if (houses[i] - previous >= mid) {
                    previous = houses[i];
                    count++;
                }
            }
            if (count < c) {
                max = mid - 1;
            } else {
                answer = mid;
                min = mid + 1;
            }
        }
        System.out.println(answer);
    }
}
