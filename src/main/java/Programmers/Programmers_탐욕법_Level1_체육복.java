package Programmers;

import java.util.Set;
import java.util.TreeSet;

public class Programmers_탐욕법_Level1_체육복 {

    public static void main(String[] args) {
        System.out.println(solution(5, new int[]{2, 4}, new int[]{1, 3, 5}));
        System.out.println(solution(5, new int[]{2, 4}, new int[]{3}));
        System.out.println(solution(3, new int[]{3}, new int[]{1}));
    }

    public static int solution(int n, int[] lost, int[] reserve) {
        Set<Integer> studentsWhoHasLost = new TreeSet<>();
        for (int student : lost) {
            studentsWhoHasLost.add(student);
        }
        Set<Integer> studentsWhoHasReserve = new TreeSet<>();
        int answer = n - lost.length;
        for (int student : reserve) {
            if (studentsWhoHasLost.contains(student)) {
                studentsWhoHasLost.remove(student);
                answer++;
                continue;
            }
            studentsWhoHasReserve.add(student);
        }
        for (int student : studentsWhoHasLost) {
            if (studentsWhoHasReserve.contains(student - 1)) {
                studentsWhoHasReserve.remove(student - 1);
                answer++;
            } else if (studentsWhoHasReserve.contains(student + 1)) {
                studentsWhoHasReserve.remove(student + 1);
                answer++;
            }
        }
        return answer;
    }
}
