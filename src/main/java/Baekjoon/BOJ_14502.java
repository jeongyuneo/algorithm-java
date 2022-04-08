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

    private static int[][] laboratory;
    private static int maxSafeZone;
    private static int n;
    private static int m;

    public static void main(String[] args) throws IOException {
        BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer stringTokenizer = new StringTokenizer(bufferedReader.readLine());
        n = Integer.parseInt(stringTokenizer.nextToken());
        m = Integer.parseInt(stringTokenizer.nextToken());
        laboratory = new int[n][m];
        for (int i = 0; i < n; i++) {
            stringTokenizer = new StringTokenizer(bufferedReader.readLine());
            for (int j = 0; j < m; j++) {
                int input = Integer.parseInt(stringTokenizer.nextToken());
                if (input == EMPTY_SPACE) {
                    EMPTY_SPACES.add(new int[]{i, j});
                } else if(input == VIRUS) {
                    VIRUSES.add(new int[]{i, j});
                }
                laboratory[i][j] = input;
            }
        }

        installWalls(0, 0);
        System.out.println(maxSafeZone);
    }

    private static void spreadVirus() {
        int[][] laboratoryWithNewWalls = copyLaboratory();

        Queue<int[]> queue = new LinkedList<>();
        for (int[] virus : VIRUSES) {
            queue.offer(virus);
        }
        while (!queue.isEmpty()) {
            int[] virus = queue.poll();
            int x = virus[0];
            int y = virus[1];
            for (int[] delta : DELTAS) {
                int dx = x + delta[0];
                int dy = y + delta[1];
                if (dx >= 0 && dx < n && dy >= 0 && dy < m && laboratoryWithNewWalls[dx][dy] == EMPTY_SPACE) {
                    laboratoryWithNewWalls[dx][dy] = VIRUS;
                    queue.offer(new int[]{dx, dy});
                }
            }
        }
        checkSafeZone(laboratoryWithNewWalls);
    }

    private static int[][] copyLaboratory() {
        int[][] laboratoryWithNewWalls = new int[n][m];
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < m; j++) {
                laboratoryWithNewWalls[i][j] = laboratory[i][j];
            }
        }
        return laboratoryWithNewWalls;
    }

    private static void checkSafeZone(int[][] laboratoryWithNewWalls) {
        int safeZone = 0;
        for (int[] laboratoryWithNewWall : laboratoryWithNewWalls) {
            for (int space : laboratoryWithNewWall) {
                if (space == EMPTY_SPACE) {
                    safeZone++;
                }
            }
        }
        maxSafeZone = Math.max(maxSafeZone, safeZone);
    }

    private static void installWalls(int cnt, int start) {
        if (cnt == WALL_COUNT) {
            spreadVirus();
            return;
        }

        for (int i = start; i < EMPTY_SPACES.size(); i++) {
            laboratory[EMPTY_SPACES.get(i)[0]][EMPTY_SPACES.get(i)[1]] = WALL;
            installWalls(cnt+1, i+1);
            laboratory[EMPTY_SPACES.get(i)[0]][EMPTY_SPACES.get(i)[1]] = EMPTY_SPACE;
        }
    }
}
