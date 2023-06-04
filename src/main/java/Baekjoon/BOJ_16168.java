package Baekjoon;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.StringTokenizer;

public class BOJ_16168 {

    private static int edge;
    private static List<Integer>[] edges;
    private static int[][] circuit;
    private static String result;
    private static boolean isConnected;

    public static void main(String[] args) throws IOException {
        BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer stringTokenizer = new StringTokenizer(bufferedReader.readLine());
        int vertex = Integer.parseInt(stringTokenizer.nextToken());
        edge = Integer.parseInt(stringTokenizer.nextToken());
        edges = new List[vertex];
        circuit = new int[vertex][vertex];
        for (int i = 0; i < vertex; i++) {
            edges[i] = new ArrayList<>();
        }
        for (int i = 0; i < edge; i++) {
            stringTokenizer = new StringTokenizer(bufferedReader.readLine());
            int from = Integer.parseInt(stringTokenizer.nextToken()) - 1;
            int to = Integer.parseInt(stringTokenizer.nextToken()) - 1;
            edges[from].add(to);
            edges[to].add(from);
        }
        result = "NO";
        for (int i = 0; i < vertex; i++) {
            canConnected(i, i, 0);
            if (isConnected) {
                break;
            }
        }
        System.out.println(result);
    }

    private static void canConnected(int from, int number, int cnt) {
        if (cnt == edge) {
            result = "YES";
            isConnected = true;
            return;
        }
        for (int to : edges[from]) {
            if (circuit[from][to] == number || circuit[to][from] == number) {
                continue;
            }
            circuit[from][to] = number;
            circuit[to][from] = number;
            canConnected(to, number, cnt + 1);
        }
    }
}
