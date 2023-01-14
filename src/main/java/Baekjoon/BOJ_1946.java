package Baekjoon;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.Comparator;
import java.util.StringTokenizer;

public class BOJ_1946 {

    private static final int DOCUMENT_EXAMINATION_GRADE = 0;
    private static final int INTERVIEW_GRADE = 1;

    public static void main(String[] args) throws IOException {
        BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(System.in));
        int testCase = Integer.parseInt(bufferedReader.readLine());
        StringBuilder answer = new StringBuilder();
        while (testCase-- > 0) {
            int n = Integer.parseInt(bufferedReader.readLine());
            int[][] candidates = new int[n][2];
            for (int i = 0; i < n; i++) {
                StringTokenizer stringTokenizer = new StringTokenizer(bufferedReader.readLine());
                candidates[i][DOCUMENT_EXAMINATION_GRADE] = Integer.parseInt(stringTokenizer.nextToken());
                candidates[i][INTERVIEW_GRADE] = Integer.parseInt(stringTokenizer.nextToken());
            }
            Arrays.sort(candidates, Comparator.comparing(newRecruit -> newRecruit[0]));
            int maxRecruit = 1;
            int lowestGrade = candidates[0][INTERVIEW_GRADE];
            for (int i = 1; i < n; i++) {
                if (candidates[i][INTERVIEW_GRADE] < lowestGrade) {
                    maxRecruit++;
                    lowestGrade = candidates[i][INTERVIEW_GRADE];
                }
            }
            answer.append(maxRecruit).append("\n");
        }
        System.out.println(answer);
    }
}
