package Baekjoon;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class BOJ_14502 {

    private static final int[][] DELTAS = {{0, 1}, {1, 0}, {0, -1}, {-1, 0}};
    private static final int EMPTY_SPACE = 0;
    private static final int WALL = 1;
    private static final int VIRUS = 2;
    private static final int WALL_COUNT = 3;
    private static final List<int[]> VIRUSES = new ArrayList<>();
    private static final List<int[]> EMPTY_SPACES = new ArrayList<>();
    private static final Queue<int[]> MOVES = new ArrayDeque<>();

    private static int[][] laboratory;
    private static boolean[][] isVisited;
    private static int maxSafeZone;
    private static int n;
    private static int m;

    public static void main(String[] args) throws IOException {
        BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer stringTokenizer = new StringTokenizer(bufferedReader.readLine());
        n = Integer.parseInt(stringTokenizer.nextToken());
        m = Integer.parseInt(stringTokenizer.nextToken());
        laboratory = new int[n][m];
        isVisited = new boolean[n][m];
        for (int i = 0; i < n; i++) {
            stringTokenizer = new StringTokenizer(bufferedReader.readLine());
            for (int j = 0; j < m; j++) {
                int input = Integer.parseInt(stringTokenizer.nextToken());
                if (input == EMPTY_SPACE) {
                    EMPTY_SPACES.add(new int[]{i, j});
                } else if (input == VIRUS) {
                    VIRUSES.add(new int[]{i, j});
                }
                laboratory[i][j] = input;
            }
        }

        installWalls(0, 0);
        System.out.println(maxSafeZone);
    }

    private static void installWalls(int cnt, int start) {
        if (cnt == WALL_COUNT) {
            spreadVirus();
            return;
        }
        for (int i = start; i < EMPTY_SPACES.size(); i++) {
            int[] emptySpace = EMPTY_SPACES.get(i);
            laboratory[emptySpace[0]][emptySpace[1]] = WALL;
            installWalls(cnt + 1, i + 1);
            laboratory[emptySpace[0]][emptySpace[1]] = EMPTY_SPACE;
        }
    }

    private static void spreadVirus() {
        int[][] copyOfLaboratory = copyLaboratory();
        initializeVisits();
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < m; j++) {
                if (copyOfLaboratory[i][j] == VIRUS && !isVisited[i][j]) {
                    MOVES.offer(new int[]{i, j});
                    isVisited[i][j] = true;
                    while (!MOVES.isEmpty()) {
                        int[] current = MOVES.poll();
                        int x = current[0];
                        int y = current[1];
                        for (int[] delta : DELTAS) {
                            int dx = x + delta[0];
                            int dy = y + delta[1];
                            if (isInLaboratory(dx, dy) && copyOfLaboratory[dx][dy] == EMPTY_SPACE && !isVisited[dx][dy]) {
                                isVisited[dx][dy] = true;
                                copyOfLaboratory[dx][dy] = VIRUS;
                                MOVES.offer(new int[]{dx, dy});
                            }
                        }
                    }
                }
            }
        }
        checkSafeZoe(copyOfLaboratory);
    }

    private static void checkSafeZoe(int[][] laboratory) {
        int safeZone = 0;
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < m; j++) {
                if (laboratory[i][j] == EMPTY_SPACE) {
                    safeZone++;
                }
            }
        }
        maxSafeZone = Math.max(maxSafeZone, safeZone);
    }

    private static int[][] copyLaboratory() {
        int[][] copyOfLaboratory = new int[n][m];
        for (int i = 0; i < n; i++) {
            copyOfLaboratory[i] = laboratory[i].clone();
        }
        return copyOfLaboratory;
    }

    private static boolean isInLaboratory(int dx, int dy) {
        return dx >= 0 && dx < n && dy >= 0 && dy < m;
    }

    private static void initializeVisits() {
        for (int i = 0; i < n; i++) {
            Arrays.fill(isVisited[i], false);
        }
    }
}
