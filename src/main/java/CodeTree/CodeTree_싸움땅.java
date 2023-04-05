package CodeTree;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Comparator;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

public class CodeTree_싸움땅 {

    private static final int[][] DELTAS = {{-1, 0}, {0, 1}, {1, 0}, {0, -1}};
    private static final int EMPTY = 0;
    private static final int X = 0;
    private static final int Y = 1;
    private static final int DIRECTION = 2;
    private static final int INIT_ABILITY = 3;
    private static final int GUN = 4;

    private static PriorityQueue<Integer>[][] guns;
    private static int[][] players;
    private static int[][] map;
    private static int n;

    public static void main(String[] args) throws IOException {
        BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer stringTokenizer = new StringTokenizer(bufferedReader.readLine());
        n = Integer.parseInt(stringTokenizer.nextToken());
        int m = Integer.parseInt(stringTokenizer.nextToken());
        int k = Integer.parseInt(stringTokenizer.nextToken());
        guns = new PriorityQueue[n][n];
        for (int i = 0; i < n; i++) {
            stringTokenizer = new StringTokenizer(bufferedReader.readLine());
            for (int j = 0; j < n; j++) {
                int input = Integer.parseInt(stringTokenizer.nextToken());
                guns[i][j] = new PriorityQueue<>(Comparator.reverseOrder());
                if (input != EMPTY) {
                    guns[i][j].offer(input);
                }
            }
        }
        map = new int[n][n];
        players = new int[m + 1][5];
        for (int player = 1; player <= m; player++) {
            stringTokenizer = new StringTokenizer(bufferedReader.readLine());
            int x = Integer.parseInt(stringTokenizer.nextToken()) - 1;
            int y = Integer.parseInt(stringTokenizer.nextToken()) - 1;
            int direction = Integer.parseInt(stringTokenizer.nextToken());
            int initAbility = Integer.parseInt(stringTokenizer.nextToken());
            players[player] = new int[]{x, y, direction, initAbility, EMPTY};
            map[x][y] = player;
        }
        int[] points = new int[m + 1];
        while (k-- > 0) {
            for (int number = 1; number <= m; number++) {
                int[] player = players[number];
                map[player[X]][player[Y]] = EMPTY;
                int dx = player[X] + DELTAS[player[DIRECTION]][X];
                int dy = player[Y] + DELTAS[player[DIRECTION]][Y];
                if (!isInRange(dx, dy)) {
                    dx -= DELTAS[player[DIRECTION]][X] * 2;
                    dy -= DELTAS[player[DIRECTION]][Y] * 2;
                    players[number][DIRECTION] = (player[DIRECTION] + 2) % 4;
                }
                if (map[dx][dy] == EMPTY) {
                    move(dx, dy, number);
                    selectPowerfulGun(number, player[GUN], dx, dy);
                } else {
                    int enemy = map[dx][dy];
                    int winner = number;
                    int loser = enemy;
                    int player1 = player[INIT_ABILITY] + player[GUN];
                    int player2 = players[enemy][INIT_ABILITY] + players[enemy][GUN];
                    if (player1 < player2 || player1 == player2 && player[INIT_ABILITY] < players[enemy][INIT_ABILITY]) {
                        winner = enemy;
                        loser = number;
                    }
                    points[winner] += Math.abs(player1 - player2);
                    dropGun(dx, dy, loser);
                    moveLoser(dx, dy, loser);
                    move(dx, dy, winner);
                    selectPowerfulGun(winner, players[winner][GUN], dx, dy);
                }
            }
        }
        StringBuilder result = new StringBuilder();
        for (int i = 1; i <= m; i++) {
            result.append(points[i]).append(" ");
        }
        System.out.println(result);
    }

    private static boolean isInRange(int x, int y) {
        return x >= 0 && x < n && y >= 0 && y < n;
    }

    private static void move(int dx, int dy, int number) {
        map[dx][dy] = number;
        players[number][X] = dx;
        players[number][Y] = dy;
    }

    private static void selectPowerfulGun(int number, int gun, int x, int y) {
        if (!guns[x][y].isEmpty() && guns[x][y].peek() > gun) {
            players[number][GUN] = guns[x][y].poll();
            if (gun != EMPTY) {
                guns[x][y].offer(gun);
            }
        }
    }

    private static void moveLoser(int x, int y, int loser) {
        for (int j = 0; j < 4; j++) {
            int nextDirection = (players[loser][DIRECTION] + j) % 4;
            int dx = x + DELTAS[nextDirection][X];
            int dy = y + DELTAS[nextDirection][Y];
            if (isInRange(dx, dy) && map[dx][dy] == EMPTY) {
                move(dx, dy, loser);
                players[loser][DIRECTION] = nextDirection;
                if (!guns[dx][dy].isEmpty()) {
                    players[loser][GUN] = guns[dx][dy].poll();
                }
                return;
            }
        }
    }

    private static void dropGun(int x, int y, int loser) {
        if (players[loser][GUN] != EMPTY) {
            guns[x][y].offer(players[loser][GUN]);
            players[loser][GUN] = EMPTY;
        }
    }
}
