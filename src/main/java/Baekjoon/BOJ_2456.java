package Baekjoon;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class BOJ_2456 {

    private static final int CANDIDATE_NUMBER = 0;
    private static final int SCORE_ONE = 1;
    private static final int SCORE_TWO = 2;
    private static final int SCORE_THREE = 3;
    private static final int TOTAL_SCORE = 4;

    public static void main(String[] args) throws IOException {
        BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(System.in));
        int n = Integer.parseInt(bufferedReader.readLine());
        int[][] candidates = new int[3][5];
        candidates[0][CANDIDATE_NUMBER] = 1;
        candidates[1][CANDIDATE_NUMBER] = 2;
        candidates[2][CANDIDATE_NUMBER] = 3;
        for (int i = 0; i < n; i++) {
            StringTokenizer stringTokenizer = new StringTokenizer(bufferedReader.readLine());
            int first = Integer.parseInt(stringTokenizer.nextToken());
            int second = Integer.parseInt(stringTokenizer.nextToken());
            int third = Integer.parseInt(stringTokenizer.nextToken());
            candidates[0][first]++;
            candidates[1][second]++;
            candidates[2][third]++;
            candidates[0][TOTAL_SCORE] += first;
            candidates[1][TOTAL_SCORE] += second;
            candidates[2][TOTAL_SCORE] += third;
        }
        Arrays.sort(candidates, (o1, o2) -> {
            if (o1[TOTAL_SCORE] == o2[TOTAL_SCORE]) {
                return o1[SCORE_THREE] - o2[SCORE_THREE];
            } else if (o1[SCORE_THREE] == o2[SCORE_THREE]) {
                return o1[SCORE_TWO] - o2[SCORE_TWO];
            }
            return o1[TOTAL_SCORE] - o2[TOTAL_SCORE];
        });
        StringBuilder answer = new StringBuilder();
        if (electable(candidates)) {
            answer.append(candidates[2][CANDIDATE_NUMBER]);
        } else {
            answer.append(0);
        }
        answer.append(" ")
                .append(candidates[2][TOTAL_SCORE]);
        System.out.println(answer);
    }

    private static boolean electable(int[][] candidates) {
        if (candidates[2][TOTAL_SCORE] > candidates[1][TOTAL_SCORE]) {
            return true;
        }
        for (int i = candidates.length - 2; i >= 0; i--) {
            if (candidates[i][TOTAL_SCORE] != candidates[2][TOTAL_SCORE]) {
                continue;
            }
            if (candidates[i][TOTAL_SCORE] == candidates[2][TOTAL_SCORE] && candidates[i][SCORE_THREE] == candidates[2][SCORE_THREE]) {
                return false;
            }
        }
        return true;
    }
}
