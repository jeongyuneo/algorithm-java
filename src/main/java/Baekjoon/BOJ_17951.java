package Baekjoon;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class BOJ_17951 {

    public static void main(String[] args) throws IOException {
        BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer stringTokenizer = new StringTokenizer(bufferedReader.readLine());
        int n = Integer.parseInt(stringTokenizer.nextToken());
        int k = Integer.parseInt(stringTokenizer.nextToken());
        stringTokenizer = new StringTokenizer(bufferedReader.readLine());
        int[] scores = new int[n];
        int end = 0;
        for (int i = 0; i < n; i++) {
            int score = Integer.parseInt(stringTokenizer.nextToken());
            scores[i] = score;
            end += score;
        }
        int start = 0;
        while (start <= end) {
            int mid = (start + end) / 2;
            int scoreSum = 0;
            int group = 0;
            for (int score : scores) {
                scoreSum += score;
                if (scoreSum >= mid) {
                    scoreSum = 0;
                    group++;
                }
            }
            if (group >= k) {
                start = mid + 1;
            } else {
                end = mid - 1;
            }
        }
        System.out.println(end);
    }
}
