package Baekjoon;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;

public class BOJ_16400 {

    public static void main(String[] args) throws IOException {
        BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(System.in));
        int n = Integer.parseInt(bufferedReader.readLine());
        List<Integer> primeNumbers = new ArrayList<>();
        boolean[] isPrime = new boolean[n + 1];
        for (int i = 2; i <= n; i++) {
            if (!isPrime[i]) {
                primeNumbers.add(i);
                isPrime[i] = true;
                int j = 2;
                while (i * j <= n) {
                    isPrime[i * j++] = true;
                }
            }
        }
        int[] cases = new int[n + 1];
        cases[0] = 1;
        for (int primeNumber : primeNumbers) {
            for (int j = primeNumber; j <= n; j++) {
                if (cases[j - primeNumber] > 0) {
                    cases[j] = (cases[j] + cases[j - primeNumber]) % 123456789;
                }
            }
        }
        System.out.println(cases[n]);
    }
}
