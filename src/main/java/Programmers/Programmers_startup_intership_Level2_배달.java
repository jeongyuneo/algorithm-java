package Programmers;

import java.util.*;

public class Programmers_startup_intership_Level2_배달 {

    public static void main(String[] args) {
        System.out.println(solution(5, new int[][]{{1, 2, 1}, {2, 3, 3}, {5, 2, 2}, {1, 4, 2}, {5, 3, 1}, {5, 4, 2}}, 3));
        System.out.println(solution(6, new int[][]{{1, 2, 1}, {1, 3, 2}, {2, 3, 2}, {3, 4, 3}, {3, 5, 2}, {3, 5, 3}, {5, 6, 1}}, 4));
    }

    static class Node implements Comparable<Node> {

        int number;
        int distance;

        public Node(int number, int distance) {
            this.number = number;
            this.distance = distance;
        }

        @Override
        public int compareTo(Node node) {
            return this.distance - node.distance;
        }
    }

    private static final int FROM = 0;
    private static final int TO = 1;
    private static final int DISTANCE = 2;

    public static int solution(int N, int[][] road, int K) {
        List<Node>[] town = new List[N + 1];
        for (int i = 1; i <= N; i++) {
            town[i] = new ArrayList<>();
        }
        for (int i = 1, size = road.length; i <= size; i++) {
            int from = road[i - 1][FROM];
            int to = road[i - 1][TO];
            int distance = road[i - 1][DISTANCE];
            town[from].add(new Node(to, distance));
            town[to].add(new Node(from, distance));
        }

        int[] distances = new int[N + 1];
        Arrays.fill(distances, Integer.MAX_VALUE);
        distances[1] = 0;
        PriorityQueue<Node> priorityQueue = new PriorityQueue<>();
        priorityQueue.add(new Node(1, 0));
        while (!priorityQueue.isEmpty()) {
            Node node = priorityQueue.poll();
            if (distances[node.number] >= node.distance) {
                for (Node nextNode : town[node.number]) {
                    if (distances[nextNode.number] > nextNode.distance + node.distance) {
                        distances[nextNode.number] = nextNode.distance + node.distance;
                        priorityQueue.add(new Node(nextNode.number, nextNode.distance + node.distance));
                    }
                }
            }
        }

        int answer = 1;
        for (int i = 2; i <= N; i++) {
            if (distances[i] <= K) {
                answer++;
            }
        }
        return answer;
    }
}
