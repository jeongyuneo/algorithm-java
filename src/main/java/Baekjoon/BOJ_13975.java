package Baekjoon;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

public class BOJ_13975 {

    public static void main(String[] args) throws IOException {
        BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder answer = new StringBuilder();
        int t = Integer.parseInt(bufferedReader.readLine());
        PriorityQueue<Long> files = new PriorityQueue<>();
        while (t-- > 0) {
            files.clear();
            int n = Integer.parseInt(bufferedReader.readLine());
            StringTokenizer stringTokenizer = new StringTokenizer(bufferedReader.readLine());
            for (int i = 0; i < n; i++) {
                files.offer(Long.parseLong(stringTokenizer.nextToken()));
            }
            long minCost = 0;
            while (files.size() >= 2) {
                long cost = files.poll() + files.poll();
                minCost += cost;
                files.offer(cost);
            }
            answer.append(minCost).append("\n");
        }
        System.out.println(answer);
    }
}
