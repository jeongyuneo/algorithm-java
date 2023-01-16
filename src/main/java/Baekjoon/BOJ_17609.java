package Baekjoon;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class BOJ_17609 {

    private static final int PALINDROME = 0;
    private static final int PSEUDO_PALINDROME = 1;
    private static final int NOT_PALINDROME = 2;

    public static void main(String[] args) throws IOException {
        BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(System.in));
        int n = Integer.parseInt(bufferedReader.readLine());
        StringBuilder answer = new StringBuilder();
        while (n-- > 0) {
            String input = bufferedReader.readLine();
            answer.append(checkPalindrome(input, 0, 0, input.length() - 1)).append("\n");
        }
        System.out.println(answer);
    }

    private static int checkPalindrome(String input, int notEqualCount, int start, int end) {
        while (start <= end) {
            if (input.charAt(start) == input.charAt(end)) {
                start++;
                end--;
            } else {
                if (notEqualCount == PSEUDO_PALINDROME) {
                    return NOT_PALINDROME;
                }
                if (checkPalindrome(input, notEqualCount + 1, start + 1, end) == PALINDROME
                        || checkPalindrome(input, notEqualCount + 1, start, end - 1) == PALINDROME) {
                    return PSEUDO_PALINDROME;
                } else {
                    return NOT_PALINDROME;
                }
            }
        }
        return PALINDROME;
    }
}
