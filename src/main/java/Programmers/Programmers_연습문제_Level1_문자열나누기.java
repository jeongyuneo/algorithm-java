package Programmers;

public class Programmers_연습문제_Level1_문자열나누기 {

    public static void main(String[] args) {
        System.out.println(solution("banana"));
        System.out.println(solution("abracadabra"));
        System.out.println(solution("aaabbaccccabba"));
    }

    public static int solution(String s) {
        int answer = 0;
        char x = s.charAt(0);
        int sameCount = 1;
        int differentCount = 0;
        for (int i = 1; i < s.length(); i++) {
            if (x == s.charAt(i)) {
                sameCount++;
            } else {
                differentCount++;
            }

            if (sameCount == differentCount) {
                answer++;
                if (i < s.length() - 1) {
                    x = s.charAt(++i);
                    sameCount = 1;
                    differentCount = 0;
                }
            }
        }

        if (sameCount != differentCount) {
            answer++;
        }
        return answer;
    }
}
