package Baekjoon;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class BOJ_2512 {

    public static void main(String[] args) throws IOException {
        BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(System.in));
        int n = Integer.parseInt(bufferedReader.readLine());
        int[] requests = new int[n];
        StringTokenizer stringTokenizer = new StringTokenizer(bufferedReader.readLine());
        int sum = 0;
        int maxBudget = 0;
        for (int i = 0; i < n; i++) {
            int request = Integer.parseInt(stringTokenizer.nextToken());
            requests[i] = request;
            sum += request;
            if (maxBudget < request) {
                maxBudget = request;
            }
        }
        int budget = Integer.parseInt(bufferedReader.readLine());
        if (sum > budget) {
            Arrays.sort(requests);
            int start = 0;
            int end = budget;
            maxBudget = 0;
            while (start <= end) {
                int mid = (start + end) / 2;
                sum = 0;
                int max = 0;
                for (int request : requests) {
                    int assign = 0;
                    if (mid < request) {
                        assign = mid;
                    } else {
                        assign = request;
                    }
                    sum += assign;
                    if (max < assign) {
                        max = assign;
                    }
                }

                if (sum <= budget && maxBudget < max) {
                    maxBudget = max;
                    start = mid + 1;
                } else {
                    end = mid - 1;
                }
            }
        }
        System.out.println(maxBudget);
    }
}
