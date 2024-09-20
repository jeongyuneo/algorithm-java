package Baekjoon;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.math.BigInteger;
import java.util.StringTokenizer;

public class BOJ_2061 {

    private static final String GOOD_CODE = "GOOD";
    private static final String BAD_CODE = "BAD";

    public static void main(String[] args) throws IOException {
        BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(System.in));
        final StringTokenizer stringTokenizer = new StringTokenizer(bufferedReader.readLine());
        BigInteger K = new BigInteger(stringTokenizer.nextToken());
        int L = Integer.parseInt(stringTokenizer.nextToken());
        int smallestFactor = 0;
        for (int factor = 2; factor < L; factor++) {
            if (K.remainder(BigInteger.valueOf(factor)).compareTo(BigInteger.ZERO) == 0) {
                smallestFactor = factor;
                break;
            }
        }
        if (smallestFactor > 0) {
            System.out.println(BAD_CODE + " " + smallestFactor);
        } else {
            System.out.println(GOOD_CODE);
        }
    }
}
