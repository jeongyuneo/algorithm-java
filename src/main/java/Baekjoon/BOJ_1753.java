package Baekjoon;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.StringTokenizer;

public class BOJ_1753 {

    public static void main(String[] args) throws IOException {
        BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer stringTokenizer = new StringTokenizer(bufferedReader.readLine());
        StringBuilder stringBuilder = new StringBuilder();
        int v = Integer.parseInt(stringTokenizer.nextToken());
        int e = Integer.parseInt(stringTokenizer.nextToken());
        int k = Integer.parseInt(bufferedReader.readLine());
        List<int[]>[] adjList = new List[v+1];
        for (int i = 1; i <= v; i++) {
            adjList[i] = new ArrayList<>();
        }
        for (int i = 0; i < e; i++) {
            stringTokenizer = new StringTokenizer(bufferedReader.readLine());
            int from = Integer.parseInt(stringTokenizer.nextToken());
            int to = Integer.parseInt(stringTokenizer.nextToken());
            int w = Integer.parseInt(stringTokenizer.nextToken());
            adjList[from].add(new int[]{to, w});
        }

        int[] distance = new int[v+1];
        boolean[] visited = new boolean[v+1];

        Arrays.fill(distance, Integer.MAX_VALUE);
        distance[k] = 0;
        for (int i = 1; i <= v; i++) {
            int min = Integer.MAX_VALUE;
            int current = 0;
            for (int j = 1; j <= v; j++) {
                if (!visited[j] && min > distance[j]) {
                    min = distance[j];
                    current = j;
                }
            }
            visited[current] = true;
            if (current == 0) {
                break;
            }

            for (int j = 0; j < adjList[current].size(); j++) {
                int[] node = adjList[current].get(j);
                if (!visited[node[0]] && distance[node[0]] > distance[current] + node[1]) {
                    distance[node[0]] = distance[current] + node[1];
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
