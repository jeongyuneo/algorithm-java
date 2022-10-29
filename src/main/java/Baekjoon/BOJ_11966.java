package Baekjoon;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class BOJ_11966 {

    public static void main(String[] args) throws IOException {
        BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(System.in));
        int number = Integer.parseInt(bufferedReader.readLine());
        int answer = 1;
        while (number > 1) {
            if (number % 2 != 0) {
                answer = 0;
                break;
            }
            number /= 2;
        }
        System.out.println(answer);
    }
}
