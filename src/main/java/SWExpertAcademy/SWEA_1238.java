package SWExpertAcademy;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class SWEA_1238 {

    static class Node {

        int number;
        Node link;

        public Node(int number, Node link) {
            this.number = number;
            this.link = link;
        }
    }

    private static int max;

    public static void main(String[] args) throws IOException {
        BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder stringBuilder = new StringBuilder();
        for (int t = 1; t <= 10; t++) {
            StringTokenizer stringTokenizer = new StringTokenizer(bufferedReader.readLine());
            int n = Integer.parseInt(stringTokenizer.nextToken());
            int start = Integer.parseInt(stringTokenizer.nextToken());

            Node[] adjList = new Node[101];
            stringTokenizer = new StringTokenizer(bufferedReader.readLine());
            for (int i = 0; i < n/2; i++) {
                int from = Integer.parseInt(stringTokenizer.nextToken());
                int to = Integer.parseInt(stringTokenizer.nextToken());
                adjList[from] = new Node(to, adjList[from]);
            }

            call(adjList, start);

            stringBuilder.append("#")
                    .append(t)
                    .append(" ")
                    .append(max)
                    .append("\n");
        }
        System.out.println(stringBuilder);
    }

    private static void call(Node[] adjList, int start) {
        Queue<Integer> queue = new LinkedList<>();
        boolean[] visited = new boolean[101];

        queue.offer(start);
        visited[start] = true;

        while (!queue.isEmpty()) {
            int current = queue.poll();
            List<Integer> visitedNumber = new ArrayList<>();
            for (Node node = adjList[current]; node != null; node = node.link) {
                if (!visited[node.number]) {
                    visitedNumber.add(node.number);
                    queue.offer(node.number);
                    visited[node.number] = true;
                }
            }

            if (visitedNumber.size() != 0) {
                max = visitedNumber.stream()
                        .max(Integer::compareTo)
                        .get();
            }
        }
    }
}
