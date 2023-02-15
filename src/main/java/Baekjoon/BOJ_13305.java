package Baekjoon;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class BOJ_13305 {

    public static void main(String[] args) throws IOException {
        BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(System.in));
        int n = Integer.parseInt(bufferedReader.readLine());
        int[] distances = new int[n];
        long[] prices = new long[n];
        StringTokenizer stringTokenizer = new StringTokenizer(bufferedReader.readLine());
        for (int i = 1; i < n; i++) {
            distances[i] = Integer.parseInt(stringTokenizer.nextToken());
        }
        stringTokenizer = new StringTokenizer(bufferedReader.readLine());
        for (int i = 0; i < n; i++) {
            prices[i] = Long.parseLong(stringTokenizer.nextToken());
        }
        long price = prices[0];
        long totalPrice = 0;
        for (int i = 1; i < n; i++) {
            totalPrice += distances[i] * price;
            if (prices[i] < price) {
                price = prices[i];
            }
        }
        System.out.println(totalPrice);
    }
}
