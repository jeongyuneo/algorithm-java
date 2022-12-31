package Programmers;

import java.util.*;

public class Programmers_스택큐_Level2_기능개발 {

    public static void main(String[] args) {
        System.out.println(Arrays.toString(solution(new int[]{93, 30, 55}, new int[]{1, 30, 5})));
        System.out.println(Arrays.toString(solution(new int[]{95, 90, 99, 99, 80, 99}, new int[]{1, 1, 1, 1, 1, 1})));
    }

    public static int[] solution(int[] progresses, int[] speeds) {
        int[] distributionDays = new int[100];
        int previousDistribution = 0;
        for (int i = 0; i < progresses.length; i++) {
            int distribution = (int) Math.ceil((100 - progresses[i]) / (double) speeds[i]);
            if (previousDistribution < distribution) {
                previousDistribution = distribution;
            }
            distributionDays[previousDistribution]++;
        }
        return Arrays.stream(distributionDays).filter(distributionDay -> distributionDay != 0).toArray();
    }
}
