package Baekjoon;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.Comparator;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

public class BOJ_11000 {

    private static final int START_TIME = 0;
    private static final int END_TIME = 1;

    public static void main(String[] args) throws IOException {
        BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(System.in));
        int n = Integer.parseInt(bufferedReader.readLine());
        int[][] classTimes = new int[n][2];
        for (int i = 0; i < n; i++) {
            StringTokenizer stringTokenizer = new StringTokenizer(bufferedReader.readLine());
            classTimes[i] = new int[]{Integer.parseInt(stringTokenizer.nextToken()), Integer.parseInt(stringTokenizer.nextToken())};
        }
        Arrays.sort(classTimes, Comparator.comparing(classTime -> classTime[START_TIME]));
        PriorityQueue<Integer> endTimes = new PriorityQueue<>();
        endTimes.offer(classTimes[0][END_TIME]);
        for (int i = 1; i < n; i++) {
            if (endTimes.peek() <= classTimes[i][START_TIME]) {
                endTimes.poll();
            }
            endTimes.offer(classTimes[i][END_TIME]);
        }
        System.out.println(endTimes.size());
    }
}
