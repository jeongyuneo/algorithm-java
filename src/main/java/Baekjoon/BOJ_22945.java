package Baekjoon;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class BOJ_22945 {

    public static void main(String[] args) throws IOException {
        BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(System.in));
        int n = Integer.parseInt(bufferedReader.readLine());
        int[] capacities = new int[n];
        StringTokenizer stringTokenizer = new StringTokenizer(bufferedReader.readLine());
        for (int i = 0; i < n; i++) {
            capacities[i] = Integer.parseInt(stringTokenizer.nextToken());
        }
        int left = 0;
        int right = n - 1;
        int maxCapacity = 0;
        while (left < right) {
            maxCapacity = Math.max(maxCapacity, (right - left - 1) * Math.min(capacities[left], capacities[right]));
            if (capacities[left] < capacities[right]) {
                left++;
            } else {
                right--;
            }
        }
        System.out.println(maxCapacity);
    }
}
