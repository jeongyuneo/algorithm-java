package Baekjoon;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.StringTokenizer;

public class BOJ_17144 {

    private static final int[][] UPSIDE_DELTAS = {{-1, 0}, {0, 1}, {1, 0}, {0, -1}};
    private static final int[][] DOWNSIDE_DELTAS = {{1, 0}, {0, 1}, {-1, 0}, {0, -1}};
    private static final int[][] SPREAD_DELTAS = {{-1, 0}, {1, 0}, {0, -1}, {0, 1}};
    private static final List<Integer> AIR_CLEANER = new ArrayList<>();

    private static int[][] room;
    private static int[][] spreadRoom;
    private static int r;
    private static int c;
    private static int t;
    private static int dust;

    public static void main(String[] args) throws IOException {
        BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer stringTokenizer = new StringTokenizer(bufferedReader.readLine());
        r = Integer.parseInt(stringTokenizer.nextToken());
        c = Integer.parseInt(stringTokenizer.nextToken());
        t = Integer.parseInt(stringTokenizer.nextToken());
        room = new int[r][c];
        spreadRoom = new int[r][c];
        for (int i = 0; i < r; i++) {
            stringTokenizer = new StringTokenizer(bufferedReader.readLine());
            for (int j = 0; j < c; j++) {
                int input = Integer.parseInt(stringTokenizer.nextToken());
                if (input > 0) {
                    dust += input;
                } else if (input == -1) {
                    AIR_CLEANER.add(i);
                }
                room[i][j] = input;
            }
        }
        operate(0);
    }

    private static void operate(int time) {
        if (time == t) {
            System.out.println(dust);
            return;
        }
        spread();
        updateSpread();
        wind();
        operate(time+1);
    }

    private static void spread() {
        for (int i = 0; i < r; i++) {
            for (int j = 0; j < c; j++) {
                if (room[i][j] > 0 && room[i][j] / 5 > 0) {
                    int spreadDust = Math.floorDiv(room[i][j], 5);
                    int spreadCnt = 0;
                    for (int[] spreadDelta : SPREAD_DELTAS) {
                        int dx = i + spreadDelta[0];
                        int dy = j + spreadDelta[1];
                        if (dx >= 0 && dx < r && dy >= 0 && dy < c && room[dx][dy] != -1) {
                            spreadRoom[dx][dy] += spreadDust;
                            spreadCnt++;
                        }
                    }
                    room[i][j] -= spreadDust * spreadCnt;
                }
            }
        }
    }

    private static void updateSpread() {
        for (int i = 0; i < r; i++) {
            for (int j = 0; j < c; j++) {
                if (spreadRoom[i][j] != 0) {
                    room[i][j] += spreadRoom[i][j];
                    spreadRoom[i][j] = 0;
                }
            }
        }
    }

    private static void wind() {
        int direction = 0;
        int upSideX = AIR_CLEANER.get(0) + UPSIDE_DELTAS[direction][0];
        int upSideY = UPSIDE_DELTAS[direction][1];
        dust -= room[upSideX][upSideY];
        while (direction < UPSIDE_DELTAS.length) {
            int dx = upSideX + UPSIDE_DELTAS[direction][0];
            int dy = upSideY + UPSIDE_DELTAS[direction][1];
            while (dx >= 0 && dx <= AIR_CLEANER.get(0) && dy >= 0 && dy < c) {
                if (dx == AIR_CLEANER.get(0) && dy == 0) {
                    room[upSideX][upSideY] = 0;
                    break;
                }
                room[upSideX][upSideY] = room[dx][dy];
                upSideX = dx;
                upSideY = dy;
                dx += UPSIDE_DELTAS[direction][0];
                dy += UPSIDE_DELTAS[direction][1];
            }
            direction++;
        }

        direction = 0;
        int downSideX = AIR_CLEANER.get(1) + DOWNSIDE_DELTAS[direction][0];
        int downSideY = DOWNSIDE_DELTAS[direction][1];
        dust -= room[downSideX][downSideY];
        while (direction < DOWNSIDE_DELTAS.length) {
            int dx = downSideX + DOWNSIDE_DELTAS[direction][0];
            int dy = downSideY + DOWNSIDE_DELTAS[direction][1];
            while (dx >= AIR_CLEANER.get(1) && dx < r && dy >= 0 && dy < c) {
                if (dx == AIR_CLEANER.get(1) && dy == 0) {
                    room[downSideX][downSideY] = 0;
                    break;
                }
                room[downSideX][downSideY] = room[dx][dy];
                downSideX = dx;
                downSideY = dy;
                dx += DOWNSIDE_DELTAS[direction][0];
                dy += DOWNSIDE_DELTAS[direction][1];
            }
            direction++;
        }
    }
}
