package Baekjoon;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class BOJ_12015 {

    public static void main(String[] args) throws IOException {
        BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(System.in));
        int n = Integer.parseInt(bufferedReader.readLine());
        int[] numbers = new int[n];
        StringTokenizer stringTokenizer = new StringTokenizer(bufferedReader.readLine());
        for (int i = 0; i < n; i++) {
            numbers[i] = Integer.parseInt(stringTokenizer.nextToken());
        }
        int[] subnet = new int[n];
        int idx = 0;
        subnet[idx++] = numbers[0];
        for (int i = 1; i < n; i++) {
            int start = 0;
            int end = idx;
            while (start < end) {
                int mid = (start + end) / 2;
                if (subnet[mid] < numbers[i]) {
                    start = mid + 1;
                } else {
                    end = mid;
                }
            }
            if (start == idx) {
                subnet[idx++] = numbers[i];
            } else if (start < idx) {
                subnet[start] = numbers[i];
            }
        }
        System.out.println(idx);
    }
}
