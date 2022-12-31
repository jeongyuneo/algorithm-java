package Programmers;

import java.util.Stack;

public class Programmers_스택큐_Level2_올바른괄호 {

    public static void main(String[] args) {
        System.out.println(solution("()()"));
        System.out.println(solution("(())()"));
        System.out.println(solution(")()("));
        System.out.println(solution("(()("));
    }

    private static final char OPENING_BRACKET = '(';
    private static final char CLOSING_BRACKET = ')';

    public static boolean solution(String s) {
        Stack<Character> stack = new Stack<>();
        for (int i = 0; i < s.length(); i++) {
            char current = s.charAt(i);
            if (current == CLOSING_BRACKET) {
                if (!stack.isEmpty() && stack.peek() == OPENING_BRACKET) {
                    stack.pop();
                } else {
                    return false;
                }
            } else {
                stack.push(current);
            }
        }
        return stack.isEmpty();
    }
}
