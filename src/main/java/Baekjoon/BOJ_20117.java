package Baekjoon;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class BOJ_20117 {

    public static void main(String[] args) throws IOException {
        BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(System.in));
        int n = Integer.parseInt(bufferedReader.readLine());
        int[] products = new int[n];
        StringTokenizer stringTokenizer = new StringTokenizer(bufferedReader.readLine());
        for (int i = 0; i < n; i++) {
            products[i] = Integer.parseInt(stringTokenizer.nextToken());
        }
        Arrays.sort(products);
        int interest = 0;
        if (n % 2 == 0) {
            for (int i = n - 1, end = n / 2; i >= end; i--) {
                interest += products[i] * 2;
            }
        } else {
            for (int i = n - 1, end = n / 2; i > end; i--) {
                interest += products[i] * 2;
            }
            interest += products[n / 2];
        }
        System.out.println(interest);
    }
}
