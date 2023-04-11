package Baekjoon;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class BOJ_16472 {

    public static void main(String[] args) throws IOException {
        BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(System.in));
        int n = Integer.parseInt(bufferedReader.readLine());
        String input = bufferedReader.readLine();
        int maxLength = 1;
        int start = 0;
        int end = 0;
        int[] alphabets = new int[26];
        alphabets[input.charAt(start) - 'a']++;
        int length = 1;
        while (end < input.length() - 1) {
            end++;
            char alphabet = input.charAt(end);
            int index = alphabet - 'a';
            if (++alphabets[index] == 1) {
                length++;
            }
            while (length > n) {
                int index2 = input.charAt(start) - 'a';
                if (--alphabets[index2] == 0) {
                    length--;
                }
                start++;
            }
            maxLength = Math.max(maxLength, end - start + 1);
        }
        System.out.println(maxLength);
    }
}
