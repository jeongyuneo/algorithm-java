package Baekjoon;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class BOJ_1753 {

    static class Node implements Comparable<Node>{
        
        int vertex;
        int weight;

        public Node(int vertex, int weight) {
            this.vertex = vertex;
            this.weight = weight;
        }

        @Override
        public int compareTo(Node o) {
            return this.weight - o.weight;
        }
    }

    public static void main(String[] args) throws IOException {
        BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer stringTokenizer = new StringTokenizer(bufferedReader.readLine());
        StringBuilder stringBuilder = new StringBuilder();
        int v = Integer.parseInt(stringTokenizer.nextToken());
        int e = Integer.parseInt(stringTokenizer.nextToken());
        int k = Integer.parseInt(bufferedReader.readLine());
        List<Node>[] adjList = new List[v+1];
        for (int i = 1; i <= v; i++) {
            adjList[i] = new ArrayList<>();
        }
        for (int i = 0; i < e; i++) {
            stringTokenizer = new StringTokenizer(bufferedReader.readLine());
            int from = Integer.parseInt(stringTokenizer.nextToken());
            int to = Integer.parseInt(stringTokenizer.nextToken());
            int w = Integer.parseInt(stringTokenizer.nextToken());
            adjList[from].add(new Node(to, w));
        }

        int[] distance = new int[v+1];
        boolean[] visited = new boolean[v+1];
        PriorityQueue<Node> priorityQueue = new PriorityQueue<>();
        priorityQueue.offer(new Node(k, 0));

        Arrays.fill(distance, Integer.MAX_VALUE);
        distance[k] = 0;
        while (!priorityQueue.isEmpty()) {
            Node current = priorityQueue.poll();
            if (visited[current.vertex]) {
                continue;
            }
            visited[current.vertex] = true;

            for (int j = 0; j < adjList[current.vertex].size(); j++) {
                Node node = adjList[current.vertex].get(j);
                if (distance[node.vertex] > distance[current.vertex] + node.weight) {
                    distance[node.vertex] = distance[current.vertex] + node.weight;
                    priorityQueue.offer(new Node(node.vertex, distance[node.vertex]));
                }
            }
        }

        for (int i = 1; i <= v; i++) {
            if (distance[i] != Integer.MAX_VALUE) {
                stringBuilder.append(distance[i]);
            } else {
                stringBuilder.append("INF");
            }
            stringBuilder.append("\n");
        }
        System.out.println(stringBuilder);
    }
}
