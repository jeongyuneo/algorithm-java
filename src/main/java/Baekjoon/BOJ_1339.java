package Baekjoon;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;

public class BOJ_1339 {

    public static void main(String[] args) throws IOException {
        BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(System.in));
        int n = Integer.parseInt(bufferedReader.readLine());
        int[] alphabets = new int[26];
        for (int i = 0; i < n; i++) {
            String word = bufferedReader.readLine();
            int number = (int) Math.pow(10, word.length() - 1);
            for (int j = 0; j < word.length(); j++) {
                alphabets[word.charAt(j) - 'A'] += number;
                number /= 10;
            }
        }
        Arrays.sort(alphabets);
        int number = 9;
        int sum = 0;
        for (int i = 25; i >= 17; i--) {
            sum += alphabets[i] * number--;
        }
        System.out.println(sum);
    }
}
