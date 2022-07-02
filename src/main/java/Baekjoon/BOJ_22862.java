package Baekjoon;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class BOJ_22862 {

    public static void main(String[] args) throws IOException {
        BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer stringTokenizer = new StringTokenizer(bufferedReader.readLine());
        int n = Integer.parseInt(stringTokenizer.nextToken());
        int k = Integer.parseInt(stringTokenizer.nextToken());
        int[] numbers = new int[n];
        stringTokenizer = new StringTokenizer(bufferedReader.readLine());
        for (int i = 0; i < n; i++) {
            numbers[i] = Integer.parseInt(stringTokenizer.nextToken());
        }

        int maxLength = 0;
        int start = 0;
        if (n == 1) {
            if (numbers[start] % 2 == 0) {
                maxLength = 1;
            }
        } else {
            int end = 0;
            int removal = 0;
            int length = 0;
            while (end < n) {
                if (numbers[end++] % 2 == 1) {
                    if (++removal > k) {
                        while (removal > k) {
                            if (numbers[start++] % 2 == 1) {
                                removal--;
                            } else {
                                length--;
                            }
                        }
                    }
                } else {
                    length++;
                }

                if (maxLength < length) {
                    maxLength = length;
                }
            }
        }
        System.out.println(maxLength);
    }
}
