package Baekjoon;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Stack;

public class BOJ_9935 {

    public static void main(String[] args) throws IOException {
        BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(System.in));
        String input = bufferedReader.readLine();
        String explosionString = bufferedReader.readLine();
        Stack<Character> init = new Stack<>();
        Stack<Character> result = new Stack<>();
        for (int i = 0, size = input.length(); i < size; i++) {
            init.push(input.charAt(i));
        }

        int sizeExceptOneValue = explosionString.length() - 1;
        while (!init.isEmpty()) {
            char letter = init.pop();
            if (letter == explosionString.charAt(sizeExceptOneValue)) {
                if (init.size() >= sizeExceptOneValue) {
                    StringBuilder string = new StringBuilder();
                    extractString(init, sizeExceptOneValue, string);
                    string.reverse();
                    string.append(letter);
                    if (!explosionString.equals(string.toString())) {
                        result.push(letter);
                        for (int i = 0; i < string.length() - 1; i++) {
                            init.push(string.charAt(i));
                        }
                    }
                } else {
                    result.push(letter);
                }
            } else if (letter == explosionString.charAt(0)) {
                if (result.size() >= sizeExceptOneValue) {
                    StringBuilder string = new StringBuilder(String.valueOf(letter));
                    extractString(result, sizeExceptOneValue, string);
                    if (!explosionString.equals(string.toString())) {
                        for (int i = string.length() - 1; i >= 0; i--) {
                            result.push(string.charAt(i));
                        }
                    }
                } else {
                    result.push(letter);
                }
            } else {
                result.push(letter);
            }
        }

        if (result.isEmpty()) {
            System.out.println("FRULA");
        } else {
            StringBuilder answer = new StringBuilder();
            while (!result.isEmpty()) {
                answer.append(result.pop());
            }
            System.out.println(answer);
        }
    }

    private static void extractString(Stack<Character> stack, int sizeExceptOneValue, StringBuilder string) {
        int cnt = sizeExceptOneValue;
        while (cnt-- > 0) {
            string.append(stack.pop());
        }
    }
}
