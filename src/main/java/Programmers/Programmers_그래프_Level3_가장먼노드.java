package Programmers;

import java.util.*;

public class Programmers_그래프_Level3_가장먼노드 {

    private static final int FROM = 0;
    private static final int TO = 1;

    public static void main(String[] args) {
        System.out.println(solution(6, new int[][]{{3, 6}, {4, 3}, {3, 2}, {1, 3}, {1, 2}, {2, 4}, {5, 2}}));
    }

    public static int solution(int n, int[][] vertex) {
        List<Integer>[] edges = new List[n + 1];
        int[] distances = new int[n + 1];
        for (int i = 1; i <= n; i++) {
            edges[i] = new ArrayList<>();
            distances[i] = Integer.MAX_VALUE;
        }
        for (int[] info : vertex) {
            edges[info[FROM]].add(info[TO]);
            edges[info[TO]].add(info[FROM]);
        }
        Queue<Integer> moves = new ArrayDeque<>();
        distances[1] = 0;
        boolean[] isChecked = new boolean[n + 1];
        moves.offer(1);
        while (!moves.isEmpty()) {
            int from = moves.poll();
            if (isChecked[from]) {
                continue;
            }
            isChecked[from] = true;
            for (int to : edges[from]) {
                if (distances[to] > distances[from] + 1) {
                    distances[to] = distances[from] + 1;
                    moves.offer(to);
                }
            }
        }
        Arrays.sort(distances);
        int maxDistance = distances[n];
        int answer = 0;
        for (int i = n; i > 0; i--) {
            if (distances[i] != maxDistance) {
                break;
            }
            answer++;
        }
        return answer;
    }
}
