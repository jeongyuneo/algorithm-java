package Programmers;

import java.util.Arrays;

public class Programmers_2022_Kakao_Level2_양궁대회 {

    public static void main(String[] args) {
        System.out.println(Arrays.toString(solution(5, new int[]{2, 1, 1, 1, 0, 0, 0, 0, 0, 0, 0})));
        System.out.println(Arrays.toString(solution(1, new int[]{1, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0})));
        System.out.println(Arrays.toString(solution(9, new int[]{0, 0, 1, 2, 0, 1, 1, 1, 1, 1, 1})));
        System.out.println(Arrays.toString(solution(MAX_SCORE, new int[]{0, 0, 0, 0, 0, 0, 0, 0, 3, 4, 3})));
    }

    private static final int MAX_SCORE = 10;

    private static int[] result;
    private static int[] ryan;
    private static int max;

    public static int[] solution(int n, int[] info) {
        result = new int[]{-1};
        ryan = new int[MAX_SCORE + 1];
        shot(info, n);
        return result;
    }

    public static void shot(int[] apeach, int n) {
        if (n == 0) {
            int apeach_point = 0;
            int lion_point = 0;
            for (int i = 0; i <= MAX_SCORE; i++) {
                if (apeach[i] != 0 || ryan[i] != 0) {
                    if (apeach[i] < ryan[i])
                        lion_point += MAX_SCORE - i;
                    else
                        apeach_point += MAX_SCORE - i;
                }
            }
            if (lion_point > apeach_point && lion_point - apeach_point >= max) {
                result = ryan.clone();
                max = lion_point - apeach_point;
            }
            return;
        }
        for (int j = 0; j <= MAX_SCORE && ryan[j] <= apeach[j]; j++) {
            ryan[j]++;
            shot(apeach, n - 1);
            ryan[j]--;
        }
    }
}
