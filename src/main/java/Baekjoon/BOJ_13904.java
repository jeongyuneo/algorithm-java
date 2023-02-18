package Baekjoon;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.Comparator;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

public class BOJ_13904 {

    private static final int REST_DAY = 0;
    private static final int SCORE = 1;

    public static void main(String[] args) throws IOException {
        BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(System.in));
        int n = Integer.parseInt(bufferedReader.readLine());
        int[][] tasks = new int[n][2];
        int lastDay = 0;
        for (int i = 0; i < n; i++) {
            StringTokenizer stringTokenizer = new StringTokenizer(bufferedReader.readLine());
            int restDay = Integer.parseInt(stringTokenizer.nextToken());
            tasks[i][REST_DAY] = restDay;
            tasks[i][SCORE] = Integer.parseInt(stringTokenizer.nextToken());
            lastDay = Math.max(lastDay, restDay);
        }
        Arrays.sort(tasks, (task1, task2) -> {
            if (task1[REST_DAY] == task2[REST_DAY]) {
                return task2[SCORE] - task1[SCORE];
            }
            return task2[REST_DAY] - task1[REST_DAY];
        });
        System.out.println(getScore(n, tasks, lastDay));
    }

    private static int getScore(int n, int[][] tasks, int lastDay) {
        PriorityQueue<Integer> scores = new PriorityQueue<>(Comparator.reverseOrder());
        int score = 0;
        int idx = 0;
        for (int day = lastDay; day >= 1; day--) {
            while (idx < n && tasks[idx][REST_DAY] == day) {
                scores.offer(tasks[idx++][SCORE]);
            }
            if (!scores.isEmpty()) {
                score += scores.poll();
            }
        }
        return score;
    }
}
