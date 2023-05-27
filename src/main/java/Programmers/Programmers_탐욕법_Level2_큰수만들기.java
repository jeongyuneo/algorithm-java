package Programmers;

import java.util.Stack;
import java.util.stream.Collectors;

public class Programmers_탐욕법_Level2_큰수만들기 {

    public static void main(String[] args) {
        System.out.println(solution("1924", 2));
        System.out.println(solution("1231234", 3));
        System.out.println(solution("4177252841", 4));
        System.out.println(solution("93939", 3));
        System.out.println(solution("4321", 1));
    }

    public static String solution(String number, int k) {
        Stack<Character> numbers = new Stack<>();
        int idx = 0;
        while (idx < number.length() && k > 0) {
            if (numbers.isEmpty()) {
                numbers.push(number.charAt(idx++));
            }
            while (!numbers.isEmpty() && k - 1 >= 0 && numbers.peek() < number.charAt(idx)) {
                numbers.pop();
                k--;
            }
            numbers.push(number.charAt(idx++));
        }
        while (idx < number.length()) {
            numbers.push(number.charAt(idx++));
        }
        return numbers.stream()
                .map(String::valueOf)
                .limit(numbers.size() - k)
                .collect(Collectors.joining());
    }
}
