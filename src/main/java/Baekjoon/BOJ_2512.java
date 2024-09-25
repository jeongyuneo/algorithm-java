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
        final StringTokenizer stringTokenizer = new StringTokenizer(bufferedReader.readLine());
        int totalRequest = 0;
        for (int area = 0; area < n; area++) {
            final int request = Integer.parseInt(stringTokenizer.nextToken());
            requests[area] = request;
            totalRequest += request;
        }
        Arrays.sort(requests);
        int upperLimit = requests[n - 1];
        int budget = Integer.parseInt(bufferedReader.readLine());
        if (totalRequest > budget) {
            int start = 0;
            int end = upperLimit;
            while (start <= end) {
                int limit = (start + end) / 2;
                int allocatedBudget = 0;
                for (int request : requests) {
                    if (request <= limit) {
                        allocatedBudget += request;
                    } else {
                        allocatedBudget += limit;
                    }
                }
                if (budget >= allocatedBudget) {
                    start = limit + 1;
                    upperLimit = limit;
                } else {
                    end = limit - 1;
                }
            }
        }
        System.out.println(upperLimit);
    }
}
