package Baekjoon;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class BOJ_1789 {

    public static void main(String[] args) throws IOException {
        BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(System.in));
        long s = Long.parseLong(bufferedReader.readLine());
        long n = 0;
        int number = 1;
        while (s >= 0) {
            s -= number++;
            n++;
        }
        System.out.println(n - 1);
    }
}
