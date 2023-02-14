package Baekjoon;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class BOJ_2252 {

    public static void main(String[] args) throws IOException {
        BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer stringTokenizer = new StringTokenizer(bufferedReader.readLine());
        int n = Integer.parseInt(stringTokenizer.nextToken());
        int m = Integer.parseInt(stringTokenizer.nextToken());
        int[] indegrees = new int[n];
        List<Integer>[] students = new List[n];
        for (int i = 0; i < n; i++) {
            students[i] = new ArrayList<>();
        }
        for (int i = 0; i < m; i++) {
            stringTokenizer = new StringTokenizer(bufferedReader.readLine());
            int smaller = Integer.parseInt(stringTokenizer.nextToken()) - 1;
            int taller = Integer.parseInt(stringTokenizer.nextToken()) - 1;
            students[smaller].add(taller);
            indegrees[taller]++;
        }
        Queue<Integer> queue = new ArrayDeque<>();
        for (int i = 0; i < n; i++) {
            if (indegrees[i] == 0) {
                queue.offer(i);
            }
        }
        StringBuilder answer = new StringBuilder();
        while (!queue.isEmpty()) {
            int current = queue.poll();
            answer.append(current + 1).append(" ");
            for (int next : students[current]) {
                if (--indegrees[next] == 0) {
                    queue.offer(next);
                }
            }
        }
        System.out.println(answer);
    }
}
