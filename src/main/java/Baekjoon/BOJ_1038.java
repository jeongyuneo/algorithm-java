package Baekjoon;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class BOJ_1038 {

    private static final List<Long> DECREASING_NUMBERS = new ArrayList<>();

    public static void main(String[] args) throws IOException {
        BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(System.in));
        int n = Integer.parseInt(bufferedReader.readLine());
        if (n <= 10) {
            System.out.println(n);
        } else {
            for (int i = 0; i < 10; i++) {
                findDecreasingNumber(i, 1);
            }
            Collections.sort(DECREASING_NUMBERS);
            if (n >= DECREASING_NUMBERS.size()) {
                System.out.println(-1);
            } else {
                System.out.println(DECREASING_NUMBERS.get(n));
            }
        }
    }

    private static void findDecreasingNumber(long number, int cipher) {
        if (cipher > 10) {
            return;
        }
        DECREASING_NUMBERS.add(number);
        for (int i = 0; i < number % 10; i++) {
            findDecreasingNumber(number * 10 + i, cipher + 1);
        }
    }
}
