package Programmers;

import java.util.Arrays;

public class Programmers_완전탐색_Level1_모의고사 {

    public static void main(String[] args) {
        System.out.println(Arrays.toString(solution(new int[]{1, 2, 3, 4, 5})));
        System.out.println(Arrays.toString(solution(new int[]{1, 3, 2, 4, 2})));
    }

    private static final int[] ONE_ANSWER = {1, 2, 3, 4, 5};
    private static final int[] TWO_ANSWER = {2, 1, 2, 3, 2, 4, 2, 5};
    private static final int[] THREE_ANSWER = {3, 3, 1, 1, 2, 2, 4, 4, 5, 5};

    public static int[] solution(int[] answers) {
        int n = answers.length;
        int[] scores = new int[4];
        for (int i = 0; i < n; i++) {
            if (answers[i] == ONE_ANSWER[(i + ONE_ANSWER.length) % ONE_ANSWER.length]) {
                scores[1]++;
            }
            if (answers[i] == TWO_ANSWER[(i + TWO_ANSWER.length) % TWO_ANSWER.length]) {
                scores[2]++;
            }
            if (answers[i] == THREE_ANSWER[(i + THREE_ANSWER.length) % THREE_ANSWER.length]) {
                scores[3]++;
            }
        }
        int maxScore = 0;
        int count = 0;
        for (int i = 1; i <= 3; i++) {
            if (maxScore < scores[i]) {
                count = 1;
                maxScore = scores[i];
            } else if (maxScore == scores[i]) {
                count++;
            }
        }
        int[] answer = new int[count];
        int idx = 0;
        for (int i = 1; i <= 3; i++) {
            if (scores[i] == maxScore) {
                answer[idx++] = i;
            }
        }
        return answer;
    }
}
