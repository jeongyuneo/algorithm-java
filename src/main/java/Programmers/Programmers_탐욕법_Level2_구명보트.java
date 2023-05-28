package Programmers;

import java.util.Arrays;

public class Programmers_탐욕법_Level2_구명보트 {

    public static void main(String[] args) {
        System.out.println(solution(new int[]{70, 50, 80, 50}, 100));
        System.out.println(solution(new int[]{70, 80, 50}, 100));
        System.out.println(solution(new int[]{30, 30, 30, 50}, 90));
    }

    public static int solution(int[] people, int limit) {
        Arrays.sort(people);
        int answer = 0;
        int first = 0;
        int last = people.length - 1;
        while (first <= last) {
            if (people[first] + people[last] <= limit) {
                first++;
            }
            last--;
            answer++;
        }
        return answer;
    }
}
