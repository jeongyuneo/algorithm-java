package Baekjoon;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class BOJ_1325 {

    public static void main(String[] args) throws IOException {
        BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer stringTokenizer = new StringTokenizer(bufferedReader.readLine());
        int n = Integer.parseInt(stringTokenizer.nextToken());
        int m = Integer.parseInt(stringTokenizer.nextToken());
        List<Integer>[] computers = new List[n + 1];
        for (int i = 1; i <= n; i++) {
            computers[i] = new ArrayList<>();
        }
        for (int i = 0; i < m; i++) {
            stringTokenizer = new StringTokenizer(bufferedReader.readLine());
            int a = Integer.parseInt(stringTokenizer.nextToken());
            int b = Integer.parseInt(stringTokenizer.nextToken());
            computers[a].add(b);
        }
        Queue<Integer> queue = new ArrayDeque<>();
        boolean[] isVisited = new boolean[n + 1];
        int[] counts = new int[n + 1];
        for (int computer = 1; computer <= n; computer++) {
            Arrays.fill(isVisited, false);
            queue.offer(computer);
            isVisited[computer] = true;
            while (!queue.isEmpty()) {
                for (int to : computers[queue.poll()]) {
                    if (!isVisited[to]) {
                        isVisited[to] = true;
                        queue.offer(to);
                        counts[to]++;
                    }
                }
            }
        }
        int max = Arrays.stream(counts).max().getAsInt();
        StringBuilder answer = new StringBuilder();
        for (int computer = 1; computer <= n; computer++) {
            if (counts[computer] == max) {
                answer.append(computer).append(" ");
            }
        }
        System.out.println(answer);
    }
}
