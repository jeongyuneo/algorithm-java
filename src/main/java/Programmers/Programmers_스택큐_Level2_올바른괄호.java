package Programmers;

public class Programmers_스택큐_Level2_올바른괄호 {

    public static void main(String[] args) {
        System.out.println(solution("()()"));
        System.out.println(solution("(())()"));
        System.out.println(solution(")()("));
        System.out.println(solution("(()("));
    }

    private static final char OPENING_BRACKET = '(';

    public static boolean solution(String s) {
        int bracket = 0;
        for (int i = 0; i < s.length(); i++) {
            if (s.charAt(i) == OPENING_BRACKET) {
                bracket++;
            } else {
                if (--bracket < 0) {
                    return false;
                }
            }
        }
        return bracket == 0;
    }
}
