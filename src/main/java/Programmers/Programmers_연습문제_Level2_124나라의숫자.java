package Programmers;

public class Programmers_연습문제_Level2_124나라의숫자 {

    public static void main(String[] args) {
        System.out.println(solution(1));
        System.out.println(solution(2));
        System.out.println(solution(3));
        System.out.println(solution(4));
        System.out.println(solution(5));
    }

    private static final String[] NUMBERS = new String[]{"4", "1", "2"};

    public static String solution(int n) {
        StringBuilder answer = new StringBuilder();
        while (n > 0) {
            answer.insert(0, NUMBERS[n % 3]);
            n = (n - 1) / 3;
        }
        return answer.toString();
    }
}
