package Programmers;

import java.util.Arrays;

public class Programmers_완전탐색_Level2_카펫 {

    public static void main(String[] args) {
        System.out.println(Arrays.toString(solution(10, 2)));
        System.out.println(Arrays.toString(solution(8, 1)));
        System.out.println(Arrays.toString(solution(24, 24)));
    }

    public static int[] solution(int brown, int yellow) {
        int width = 0;
        int height = 0;
        int total = brown + yellow;
        for (int i = 1; i < brown; i++) {
            if (total % i != 0) {
                continue;
            }
            int j = total / i;
            if (i < j) {
                continue;
            }
            if (i * 2 + j * 2 - 4 == brown) {
                width = i;
                height = j;
                break;
            }
        }
        return new int[]{width, height};
    }
}
