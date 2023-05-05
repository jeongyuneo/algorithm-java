package Programmers;

import java.util.Arrays;

public class Programmers_정렬_Level2_HIndex {

    public static void main(String[] args) {
        System.out.println(solution(new int[]{3, 0, 6, 1, 5}));
    }

    public static int solution(int[] citations) {
        int citationCount = citations.length;
        Arrays.sort(citations);
        int answer = 0;
        for (int i = 0; i < citationCount; i++) {
            int citation = citations[i];
            if (citationCount - i <= citation) {
                answer = citationCount - i;
                break;
            }
        }
        return answer;
    }
}
