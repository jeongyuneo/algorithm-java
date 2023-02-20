package Baekjoon;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.Queue;
import java.util.StringTokenizer;

public class BOJ_4991 {

    private static final int[][] DELTAS = {{0, 1}, {1, 0}, {0, -1}, {-1, 0}};
    private static final Queue<int[]> ROBOT_CLEANER = new ArrayDeque<>();
    private static final char DIRTY = '*';
    private static final char START = 'o';
    private static final char INPUT_FURNITURE = 'x';
    private static final int CLEAN = 0;
    private static final int FURNITURE = -1;
    private static final int X = 0;
    private static final int Y = 1;
    private static final int CLEANED_DIRTY = 2;

    public static void main(String[] args) throws IOException {
        BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder answer = new StringBuilder();
        while (true) {
            StringTokenizer stringTokenizer = new StringTokenizer(bufferedReader.readLine());
            int width = Integer.parseInt(stringTokenizer.nextToken());
            int height = Integer.parseInt(stringTokenizer.nextToken());
            if (width + height == 0) {
                break;
            }
            ROBOT_CLEANER.clear();
            int dirtySpace = 0;
            int[][] room = new int[height][width];
            for (int i = 0; i < height; i++) {
                String line = bufferedReader.readLine();
                for (int j = 0; j < width; j++) {
                    char input = line.charAt(j);
                    if (input == DIRTY) {
                        room[i][j] = ++dirtySpace;
                    } else if (input == INPUT_FURNITURE) {
                        room[i][j] = FURNITURE;
                    } else if (input == START) {
                        ROBOT_CLEANER.offer(new int[]{i, j, 0});
                    }
                }
            }
            answer.append(getMinMove(width, height, (1 << dirtySpace) - 1, room, new int[1 << dirtySpace][height][width])).append("\n");
        }
        System.out.println(answer);
    }

    private static int getMinMove(int width, int height, int done, int[][] room, int[][][] moves) {
        while (!ROBOT_CLEANER.isEmpty()) {
            int[] current = ROBOT_CLEANER.poll();
            int x = current[X];
            int y = current[Y];
            int cleanedDirties = current[CLEANED_DIRTY];
            if (cleanedDirties == done) {
                return moves[cleanedDirties][x][y];
            }
            for (int[] delta : DELTAS) {
                int dx = x + delta[X];
                int dy = y + delta[Y];
                if (dx >= 0 && dx < height && dy >= 0 && dy < width && room[dx][dy] != FURNITURE) {
                    if (room[dx][dy] == CLEAN) {
                        if (moves[cleanedDirties][dx][dy] == 0) {
                            moves[cleanedDirties][dx][dy] = moves[cleanedDirties][x][y] + 1;
                            ROBOT_CLEANER.offer(new int[]{dx, dy, cleanedDirties});
                        }
                    } else {
                        int afterCleaning = cleanedDirties | (1 << (room[dx][dy] - 1));
                        if (moves[afterCleaning][dx][dy] == 0) {
                            moves[afterCleaning][dx][dy] = moves[cleanedDirties][x][y] + 1;
                            ROBOT_CLEANER.offer(new int[]{dx, dy, afterCleaning});
                        }
                    }
                }
            }
        }
        return -1;
    }
}
