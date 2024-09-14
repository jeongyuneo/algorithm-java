package Baekjoon;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class BOJ_1225 {

    public static void main(String[] args) throws IOException {
        BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(System.in));
        final StringTokenizer stringTokenizer = new StringTokenizer(bufferedReader.readLine());
        final String A = stringTokenizer.nextToken();
        final String B = stringTokenizer.nextToken();
        System.out.println(sumOfEachNumber(A) * sumOfEachNumber(B));
    }

    private static long sumOfEachNumber(String input) {
        long sumOfEachNumber = 0;
        for (int i = 0, aLength = input.length(); i < aLength; i++) {
            sumOfEachNumber += input.charAt(i) - '0';
        }
        return sumOfEachNumber;
    }
}
