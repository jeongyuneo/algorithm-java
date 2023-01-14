package Baekjoon;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class BOJ_1946 {

    public static void main(String[] args) throws IOException {
        BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(System.in));
        int testCase = Integer.parseInt(bufferedReader.readLine());
        StringBuilder answer = new StringBuilder();
        while (testCase-- > 0) {
            int n = Integer.parseInt(bufferedReader.readLine());
            int[] grades = new int[n];
            for (int i = 0; i < n; i++) {
                StringTokenizer stringTokenizer = new StringTokenizer(bufferedReader.readLine());
                grades[Integer.parseInt(stringTokenizer.nextToken()) - 1] = Integer.parseInt(stringTokenizer.nextToken());
            }
            int maxRecruit = 1;
            int lowestInterviewGrade = grades[0];
            for (int i = 1; i < n; i++) {
                if (grades[i] < lowestInterviewGrade) {
                    maxRecruit++;
                    lowestInterviewGrade = grades[i];
                }
            }
            answer.append(maxRecruit).append("\n");
        }
        System.out.println(answer);
    }
}
