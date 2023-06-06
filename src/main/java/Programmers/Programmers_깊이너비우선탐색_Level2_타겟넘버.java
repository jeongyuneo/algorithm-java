package Programmers;

public class Programmers_깊이너비우선탐색_Level2_타겟넘버 {

    public static void main(String[] args) {
        System.out.println(solution(new int[]{1, 1, 1, 1, 1,}, 3));
        System.out.println(solution(new int[]{4, 1, 2, 1,}, 4));
    }

    private static int numberCount;
    private static int answer;

    public static int solution(int[] numbers, int target) {
        numberCount = numbers.length;
        answer = 0;
        operate(numbers, target, 0, 0, 0);
        return answer;
    }

    private static void operate(int[] numbers, int target, int cnt, int start, int result) {
        if (cnt == numberCount) {
            if (result == target) {
                answer++;
            }
            return;
        }
        for (int i = start; i < numberCount; i++) {
            int number = numbers[i];
            operate(numbers, target, cnt + 1, i + 1, result + number);
            operate(numbers, target, cnt + 1, i + 1, result - number);
        }
    }
}
