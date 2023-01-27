package Baekjoon;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class BOJ_2660 {

    private static final int MAX_VALUE = 100001;

    public static void main(String[] args) throws IOException {
        BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(System.in));
        int n = Integer.parseInt(bufferedReader.readLine());
        int[][] scores = new int[n][n];
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                if (i != j) {
                    scores[i][j] = MAX_VALUE;
                }
            }
        }
        while (true) {
            StringTokenizer stringTokenizer = new StringTokenizer(bufferedReader.readLine());
            int first = Integer.parseInt(stringTokenizer.nextToken()) - 1;
            int second = Integer.parseInt(stringTokenizer.nextToken()) - 1;
            if (first < 0) {
                break;
            }
            scores[first][second] = 1;
            scores[second][first] = 1;
        }
        for (int pass = 0; pass < n; pass++) {
            for (int start = 0; start < n; start++) {
                if (pass == start) {
                    continue;
                }
                for (int end = 0; end < n; end++) {
                    if (pass == end || start == end) {
                        continue;
                    }
                    scores[start][end] = Math.min(scores[start][end], scores[start][pass] + scores[pass][end]);
                }
            }
        }
        int[] finalScores = new int[n];
        int candidateScore = n;
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                finalScores[i] = Math.max(finalScores[i], scores[i][j]);
            }
            candidateScore = Math.min(candidateScore, finalScores[i]);
        }
        StringBuilder answer = new StringBuilder();
        int candidateCount = 0;
        for (int i = 0; i < n; i++) {
            if (finalScores[i] == candidateScore) {
                candidateCount++;
                answer.append(i + 1).append(" ");
            }
        }
        answer.insert(0, candidateCount + "\n");
        answer.insert(0, candidateScore + " ");
        System.out.println(answer);
    }
}
