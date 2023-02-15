package Baekjoon;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class BOJ_13305 {

    private static final int DISTANCE = 0;
    private static final int PRICE = 1;

    public static void main(String[] args) throws IOException {
        BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(System.in));
        int n = Integer.parseInt(bufferedReader.readLine()) - 1;
        int[][] cities = new int[n][2];
        StringTokenizer stringTokenizer = new StringTokenizer(bufferedReader.readLine());
        for (int i = 0; i < n; i++) {
            cities[i][DISTANCE] = Integer.parseInt(stringTokenizer.nextToken());
        }
        stringTokenizer = new StringTokenizer(bufferedReader.readLine());
        for (int i = 0; i < n; i++) {
            cities[i][PRICE] = Integer.parseInt(stringTokenizer.nextToken());
        }
        int car = 0;
        long totalPrice = 0;
        while (car < n) {
            int price = cities[car][PRICE];
            int distance = cities[car][DISTANCE];
            while (++car < n && price < cities[car][PRICE]) {
                distance += cities[car][DISTANCE];
            }
            totalPrice += (long) price * distance;
        }
        System.out.println(totalPrice);
    }
}
