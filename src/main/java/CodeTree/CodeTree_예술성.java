package CodeTree;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class CodeTree_예술성 {

    private static final int[][] DELTAS = {{0, 1}, {1, 0}, {0, -1}, {-1, 0}};
    private static final Map<Integer, int[]> GROUP = new HashMap<>();
    private static final Queue<int[]> QUEUE = new ArrayDeque<>();
    private static final int[] SELECTED_GROUP = new int[2];

    private static int[][] map;
    private static int[][] copiedMap;
    private static boolean[][] isVisited;
    private static int n;
    private static int middle;
    private static int group;
    private static int totalScore;

    public static void main(String[] args) throws IOException {
        BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(System.in));
        n = Integer.parseInt(bufferedReader.readLine());
        map = new int[n][n];
        for (int i = 0; i < n; i++) {
            StringTokenizer stringTokenizer = new StringTokenizer(bufferedReader.readLine());
            for (int j = 0; j < n; j++) {
                map[i][j] = Integer.parseInt(stringTokenizer.nextToken());
            }
        }
        middle = n / 2;
        isVisited = new boolean[n][n];
        getScore();
        for (int i = 0; i < 3; i++) {
            rotate();
            getScore();
        }
        System.out.println(totalScore);
    }

    private static void setGroup() {
        GROUP.clear();
        copiedMap = new int[n][n];
        group = 1;
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                if (copiedMap[i][j] == 0) {
                    int init = map[i][j];
                    int count = 1;
                    QUEUE.offer(new int[]{i, j});
                    copiedMap[i][j] = group;
                    while (!QUEUE.isEmpty()) {
                        int[] current = QUEUE.poll();
                        int x = current[0];
                        int y = current[1];
                        for (int[] delta : DELTAS) {
                            int dx = x + delta[0];
                            int dy = y + delta[1];
                            if (dx >= 0 && dx < n && dy >= 0 && dy < n && copiedMap[dx][dy] == 0 && map[dx][dy] == init) {
                                QUEUE.offer(new int[]{dx, dy});
                                copiedMap[dx][dy] = group;
                                count++;
                            }
                        }
                    }
                    GROUP.put(group, new int[]{init, count});
                    group++;
                }
            }
        }
    }

    private static void rotate() {
        copiedMap = new int[n][n];
        copiedMap[middle][middle] = map[middle][middle];
        // 십자 모양 회전
        for (int i = 1; i <= middle; i++) {
            copiedMap[middle][middle - 1 * i] = map[middle - 1 * i][middle];
            copiedMap[middle + 1 * i][middle] = map[middle][middle - 1 * i];
            copiedMap[middle][middle + 1 * i] = map[middle + 1 * i][middle];
            copiedMap[middle - 1 * i][middle] = map[middle][middle + 1 * i];
        }
        // 나머지 회전
        for (int i = 0; i < middle; i++) {
            for (int j = 0; j < middle; j++) {
                copiedMap[i][j] = map[middle - j - 1][i]; // 1
                copiedMap[i][middle + 1 + j] = map[middle - j - 1][middle + 1 + i]; // 2
                copiedMap[middle + 1 + i][j] = map[middle + middle - j][i]; // 3
                copiedMap[middle + 1 + i][middle + 1 + j] = map[middle + middle - j][middle + 1 + i]; // 4
            }
        }
        map = copiedMap;
    }

    private static void getScore() {
        setGroup();
        selectGroup(0, 1);
    }

    private static int getBoundary() {
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                if (copiedMap[i][j] == SELECTED_GROUP[0]) {
                    initVisited();
                    QUEUE.offer(new int[]{i, j});
                    isVisited[i][j] = true;
                    int count = 0;
                    while (!QUEUE.isEmpty()) {
                        int[] current = QUEUE.poll();
                        int x = current[0];
                        int y = current[1];
                        for (int[] delta : DELTAS) {
                            int dx = x + delta[0];
                            int dy = y + delta[1];
                            if (dx >= 0 && dx < n && dy >= 0 && dy < n && !isVisited[dx][dy]) {
                                if (copiedMap[dx][dy] == SELECTED_GROUP[0]) {
                                    QUEUE.offer(new int[]{dx, dy});
                                    isVisited[dx][dy] = true;
                                } else if (copiedMap[dx][dy] == SELECTED_GROUP[1]) {
                                    count++;
                                }
                            }
                        }
                    }
                    return count;
                }

            }
        }
        return 0;
    }

    private static void initVisited() {
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                isVisited[i][j] = false;
            }
        }
    }

    private static void selectGroup(int cnt, int start) {
        if (cnt == 2) {
            int sum = GROUP.get(SELECTED_GROUP[0])[1] + GROUP.get(SELECTED_GROUP[1])[1];
            sum *= GROUP.get(SELECTED_GROUP[0])[0] * GROUP.get(SELECTED_GROUP[1])[0];
            sum *= getBoundary();
            totalScore += sum;
            return;
        }

        for (int i = start; i < group; i++) {
            SELECTED_GROUP[cnt] = i;
            selectGroup(cnt + 1, i + 1);
        }
    }
}
