package Baekjoon;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class BOJ_4386 {

    private static final int NUMBER = 0;
    private static final int COST = 1;

    public static void main(String[] args) throws IOException {
        BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(System.in));
        int n = Integer.parseInt(bufferedReader.readLine());
        double[][] stars = new double[n][n];
        for (int i = 0; i < n; i++) {
            StringTokenizer stringTokenizer = new StringTokenizer(bufferedReader.readLine());
            stars[i][0] = Double.parseDouble(stringTokenizer.nextToken());
            stars[i][1] = Double.parseDouble(stringTokenizer.nextToken());
        }
        Arrays.sort(stars, (star1, star2) -> {
            if (star1[0] == star2[0]) {
                return Double.compare(star1[1], star2[1]);
            }
            return Double.compare(star1[0], star2[0]);
        });
        System.out.printf("%.2f", getMinCost(n, getLines(n, stars)));
    }

    private static List<double[]>[] getLines(int n, double[][] stars) {
        List<double[]>[] lines = new List[n];
        for (int i = 0; i < n; i++) {
            lines[i] = new ArrayList<>();
        }
        for (int from = 0; from < n; from++) {
            for (int to = from + 1; to < n; to++) {
                lines[from].add(new double[]{to, Math.sqrt(Math.pow(stars[to][0] - stars[from][0], 2) + Math.pow(stars[to][1] - stars[from][1], 2))});
                lines[to].add(new double[]{from, Math.sqrt(Math.pow(stars[to][0] - stars[from][0], 2) + Math.pow(stars[to][1] - stars[from][1], 2))});
            }
        }
        return lines;
    }

    private static double getMinCost(int n, List<double[]>[] lines) {
        PriorityQueue<double[]> priorityQueue = new PriorityQueue<>(Comparator.comparing(info -> info[COST]));
        boolean[] isVisited = new boolean[n];
        priorityQueue.offer(new double[]{0, 0});
        double cost = 0;
        int combinedCount = 0;
        while (!priorityQueue.isEmpty()) {
            double[] current = priorityQueue.poll();
            int from = (int) current[NUMBER];
            if (isVisited[from]) {
                continue;
            }
            cost += current[COST];
            if (++combinedCount == n) {
                break;
            }
            isVisited[from] = true;
            for (double[] next : lines[from]) {
                if (!isVisited[(int) next[NUMBER]]) {
                    priorityQueue.offer(next);
                }
            }
        }
        return cost;
    }
}
