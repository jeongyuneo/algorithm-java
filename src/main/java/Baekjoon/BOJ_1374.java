package Baekjoon;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.Comparator;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

public class BOJ_1374 {

    private static final int START = 1;
    private static final int END = 2;

    public static void main(String[] args) throws IOException {
        BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(System.in));
        int n = Integer.parseInt(bufferedReader.readLine());
        int[][] classes = new int[n][3];
        for (int i = 0; i < n; i++) {
            StringTokenizer stringTokenizer = new StringTokenizer(bufferedReader.readLine());
            classes[i] = new int[]{Integer.parseInt(stringTokenizer.nextToken()), Integer.parseInt(stringTokenizer.nextToken()), Integer.parseInt(stringTokenizer.nextToken())};
        }
        Arrays.sort(classes, Comparator.comparing(info -> info[START]));
        PriorityQueue<Integer> priorityQueue = new PriorityQueue<>();
        int classroom = 0;
        for (int i = 0; i < n; i++) {
            while (!priorityQueue.isEmpty() && classes[i][START] >= priorityQueue.peek()) {
                priorityQueue.poll();
            }
            priorityQueue.offer(classes[i][END]);
            classroom = Math.max(classroom, priorityQueue.size());
        }
        System.out.println(classroom);
    }
}
