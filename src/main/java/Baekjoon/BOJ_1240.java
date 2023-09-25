package Baekjoon;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class BOJ_1240 {

    private static final int NODE = 0;
    private static final int DISTANCE = 1;

    public static void main(String[] args) throws IOException {
        BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer stringTokenizer = new StringTokenizer(bufferedReader.readLine());
        int n = Integer.parseInt(stringTokenizer.nextToken());
        int m = Integer.parseInt(stringTokenizer.nextToken());
        List<int[]>[] tree = new List[n];
        for (int i = 0; i < n; i++) {
            tree[i] = new ArrayList<>();
        }
        for (int i = 1; i < n; i++) {
            stringTokenizer = new StringTokenizer(bufferedReader.readLine());
            int from = Integer.parseInt(stringTokenizer.nextToken()) - 1;
            int to = Integer.parseInt(stringTokenizer.nextToken()) - 1;
            int distance = Integer.parseInt(stringTokenizer.nextToken());
            tree[from].add(new int[]{to, distance});
            tree[to].add(new int[]{from, distance});
        }
        StringBuilder result = new StringBuilder();
        Queue<int[]> moves = new ArrayDeque<>();
        boolean[] isVisited = new boolean[n];
        while (m-- > 0) {
            stringTokenizer = new StringTokenizer(bufferedReader.readLine());
            int start = Integer.parseInt(stringTokenizer.nextToken()) - 1;
            int end = Integer.parseInt(stringTokenizer.nextToken()) - 1;
            moves.offer(new int[]{start, 0});
            isVisited[start] = true;
            while (!moves.isEmpty()) {
                int[] current = moves.poll();
                int from = current[NODE];
                int distance = current[DISTANCE];
                if (from == end) {
                    result.append(distance).append("\n");
                    break;
                }
                for (int[] next : tree[from]) {
                    int to = next[NODE];
                    if (!isVisited[to]) {
                        isVisited[to] = true;
                        moves.offer(new int[]{to, distance + next[DISTANCE]});
                    }
                }
            }
            moves.clear();
            Arrays.fill(isVisited, false);
        }
        System.out.println(result);
    }
}
