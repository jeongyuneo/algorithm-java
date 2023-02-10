package Baekjoon;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class BOJ_4386 {

    private static final int FROM = 0;
    private static final int TO = 1;
    private static final int COST = 2;

    private static int[] parents;

    public static void main(String[] args) throws IOException {
        BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(System.in));
        int n = Integer.parseInt(bufferedReader.readLine());
        double[][] stars = new double[n][n];
        parents = new int[n];
        for (int i = 0; i < n; i++) {
            parents[i] = i;
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

    private static List<double[]> getLines(int n, double[][] stars) {
        List<double[]> lines = new ArrayList<>();
        for (int from = 0; from < n; from++) {
            for (int to = from + 1; to < n; to++) {
                lines.add(new double[]{from, to, Math.sqrt(Math.pow(stars[to][0] - stars[from][0], 2) + Math.pow(stars[to][1] - stars[from][1], 2))});
            }
        }
        lines.sort(Comparator.comparing(line -> line[COST]));
        return lines;
    }

    private static double getMinCost(int n, List<double[]> lines) {
        double cost = 0;
        int combinedCount = 1;
        for (double[] line : lines) {
            if (canCombine((int) line[FROM], (int) line[TO])) {
                cost += line[COST];
                if (++combinedCount == n) {
                    break;
                }
            }
        }
        return cost;
    }

    private static boolean canCombine(int a, int b) {
        int rootA = find(a);
        int rootB = find(b);
        if (rootA == rootB) {
            return false;
        } else if (rootA > rootB) {
            parents[rootA] = rootB;
        } else {
            parents[rootB] = rootA;
        }
        return true;
    }

    private static int find(int value) {
        if (parents[value] == value) {
            return value;
        }
        return parents[value] = find(parents[value]);
    }
}
