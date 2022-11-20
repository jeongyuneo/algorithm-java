package Baekjoon;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class BOJ_5972 {

    static class Node {

        int to;
        int cow;

        public Node(int to, int cow) {
            this.to = to;
            this.cow = cow;
        }
    }

    public static void main(String[] args) throws IOException {
        BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer stringTokenizer = new StringTokenizer(bufferedReader.readLine());
        int n = Integer.parseInt(stringTokenizer.nextToken());
        int m = Integer.parseInt(stringTokenizer.nextToken());
        List<Node>[] adjList = new List[n + 1];
        for (int i = 1; i <= n; i++) {
            adjList[i] = new ArrayList<>();
        }
        for (int i = 0; i < m; i++) {
            stringTokenizer = new StringTokenizer(bufferedReader.readLine());
            int from = Integer.parseInt(stringTokenizer.nextToken());
            int to = Integer.parseInt(stringTokenizer.nextToken());
            int cow = Integer.parseInt(stringTokenizer.nextToken());
            adjList[from].add(new Node(to, cow));
            adjList[to].add(new Node(from, cow));
        }

        int[] cows = new int[n + 1];
        boolean[] isVisited = new boolean[n + 1];
        Arrays.fill(cows, Integer.MAX_VALUE);
        cows[1] = 0;
        PriorityQueue<Node> priorityQueue = new PriorityQueue<>(Comparator.comparing(node -> node.cow));
        priorityQueue.offer(new Node(1, 0));
        while (!priorityQueue.isEmpty()) {
            Node current = priorityQueue.poll();
            if (isVisited[current.to]) {
                continue;
            }
            isVisited[current.to] = true;
            for (Node next : adjList[current.to]) {
                if (cows[next.to] > cows[current.to] + next.cow) {
                    cows[next.to] = cows[current.to] + next.cow;
                    priorityQueue.offer(new Node(next.to, cows[next.to]));
                }
            }
        }
        System.out.println(cows[n]);
    }
}
