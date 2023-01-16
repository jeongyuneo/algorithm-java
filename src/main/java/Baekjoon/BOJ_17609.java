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
            int notEqualCount = checkPalindrome(input, 0, 0, input.length() - 1);
            if (notEqualCount == 0) {
                answer.append(PALINDROME);
            } else if (notEqualCount == 1) {
                answer.append(PSEUDO_PALINDROME);
            } else {
                answer.append(NOT_PALINDROME);
            }
            answer.append("\n");
        }
        System.out.println(answer);
    }

    private static int checkPalindrome(String input, int notEqualCount, int start, int end) {
        if (notEqualCount == NOT_PALINDROME) {
            return NOT_PALINDROME;
        }
        while (start <= end) {
            if (input.charAt(start) == input.charAt(end)) {
                start++;
                end--;
            } else {
                notEqualCount = Math.min(checkPalindrome(input, notEqualCount + 1, start + 1, end), checkPalindrome(input, notEqualCount + 1, start, end - 1));
                break;
            }
        }
        return notEqualCount;
    }
}
