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
        long result = 0;
        for (int i = 0, aLength = A.length(); i < aLength; i++) {
            for (int j = 0, bLength = B.length(); j < bLength; j++) {
                result += (long) (A.charAt(i) - '0') * (B.charAt(j) - '0');
            }
        }
        System.out.println(result);
    }
}
