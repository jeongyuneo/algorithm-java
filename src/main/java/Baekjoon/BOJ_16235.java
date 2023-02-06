package Baekjoon;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class BOJ_16235 {

    private static final int[][] DELTAS = {{-1, -1}, {-1, 0}, {-1, 1}, {0, 1}, {1, 1}, {1, 0}, {1, -1}, {0, -1}};
    private static final List<Integer> GROWING_TREES = new ArrayList<>();
    private static final Queue<Integer> DEAD_TREES = new ArrayDeque<>();

    private static PriorityQueue<Integer>[][] trees;
    private static int[][] map;
    private static int[][] nutrients;
    private static int n;
    private static int m;

    public static void main(String[] args) throws IOException {
        BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer stringTokenizer = new StringTokenizer(bufferedReader.readLine());
        n = Integer.parseInt(stringTokenizer.nextToken());
        m = Integer.parseInt(stringTokenizer.nextToken());
        int k = Integer.parseInt(stringTokenizer.nextToken());
        trees = new PriorityQueue[n][n];
        map = new int[n][n];
        nutrients = new int[n][n];
        for (int i = 0; i < n; i++) {
            stringTokenizer = new StringTokenizer(bufferedReader.readLine());
            for (int j = 0; j < n; j++) {
                nutrients[i][j] = Integer.parseInt(stringTokenizer.nextToken());
                map[i][j] = 5;
                trees[i][j] = new PriorityQueue<>();
            }
        }
        for (int i = 0; i < m; i++) {
            stringTokenizer = new StringTokenizer(bufferedReader.readLine());
            trees[Integer.parseInt(stringTokenizer.nextToken()) - 1][Integer.parseInt(stringTokenizer.nextToken()) - 1].add(Integer.parseInt(stringTokenizer.nextToken()));
        }
        while (k-- > 0) {
            takeNutrients();
            reproduce();
            addNutrients();
        }
        System.out.println(m);
    }

    private static void takeNutrients() {
        for (int x = 0; x < n; x++) {
            for (int y = 0; y < n; y++) {
                if (!trees[x][y].isEmpty()) {
                    while (!trees[x][y].isEmpty()) {
                        int age = trees[x][y].poll();
                        if (map[x][y] >= age) {
                            map[x][y] -= age;
                            GROWING_TREES.add(age + 1);
                        } else {
                            DEAD_TREES.offer(age);
                        }
                    }
                    trees[x][y].addAll(GROWING_TREES);
                    GROWING_TREES.clear();
                    m -= DEAD_TREES.size();
                    convertToNutrients(x, y);
                }
            }
        }
    }

    private static void convertToNutrients(int x, int y) {
        while (!DEAD_TREES.isEmpty()) {
            map[x][y] += DEAD_TREES.poll() / 2;
        }
    }

    private static void reproduce() {
        for (int x = 0; x < n; x++) {
            for (int y = 0; y < n; y++) {
                if (!trees[x][y].isEmpty()) {
                    int reproducingTreeCount = 0;
                    for (int age : trees[x][y]) {
                        if (age % 5 == 0) {
                            reproducingTreeCount++;
                        }
                    }
                    if (reproducingTreeCount > 0) {
                        for (int[] delta : DELTAS) {
                            int dx = x + delta[0];
                            int dy = y + delta[1];
                            if (dx >= 0 && dx < n && dy >= 0 && dy < n) {
                                for (int i = 0; i < reproducingTreeCount; i++) {
                                    trees[dx][dy].add(1);
                                }
                                m += reproducingTreeCount;
                            }
                        }
                    }
                }
            }
        }
    }

    private static void addNutrients() {
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                map[i][j] += nutrients[i][j];
            }
        }
    }
}
