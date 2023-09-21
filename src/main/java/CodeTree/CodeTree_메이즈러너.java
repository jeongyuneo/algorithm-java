package CodeTree;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class CodeTree_메이즈러너 {

    private static final int[][] DELTAS = {{1, 0}, {-1, 0}, {0, 1}, {0, -1}};
    private static final int EMPTY = 0;
    private static final int EXIT = 10;
    private static final int X = 0;
    private static final int Y = 1;
    private static final int SIZE = 2;

    private static int[][] players;
    private static int[][] movingPlayers;
    private static int[][] maze;
    private static int[][] movingMaze;
    private static int totalMove;
    private static int n;
    private static int m;
    private static int exitX;
    private static int exitY;

    public static void main(String[] args) throws IOException {
        BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer stringTokenizer = new StringTokenizer(bufferedReader.readLine());
        n = Integer.parseInt(stringTokenizer.nextToken());
        m = Integer.parseInt(stringTokenizer.nextToken());
        int k = Integer.parseInt(stringTokenizer.nextToken());
        maze = new int[n + 1][n + 1];
        movingMaze = new int[n + 1][n + 1];
        players = new int[n + 1][n + 1];
        movingPlayers = new int[n + 1][n + 1];
        for (int i = 1; i <= n; i++) {
            stringTokenizer = new StringTokenizer(bufferedReader.readLine());
            for (int j = 1; j <= n; j++) {
                maze[i][j] = Integer.parseInt(stringTokenizer.nextToken());
            }
        }
        for (int i = 0; i < m; i++) {
            stringTokenizer = new StringTokenizer(bufferedReader.readLine());
            players[Integer.parseInt(stringTokenizer.nextToken())][Integer.parseInt(stringTokenizer.nextToken())]++;
        }
        stringTokenizer = new StringTokenizer(bufferedReader.readLine());
        exitX = Integer.parseInt(stringTokenizer.nextToken());
        exitY = Integer.parseInt(stringTokenizer.nextToken());
        maze[exitX][exitY] = EXIT;
        while (k-- > 0) {
            movePlayers();
            if (m == 0) {
                break;
            }
            int[] square = findSquare();
            rotateSquare(square[X], square[Y], square[SIZE]);
        }
        System.out.println(totalMove + "\n" + exitX + " " + exitY);
    }

    private static void movePlayers() {
        for (int x = 1; x <= n; x++) {
            for (int y = 1; y <= n; y++) {
                int nowDistance = getDistance(x, y, exitX, exitY);
                if (players[x][y] > 0) {
                    int size = players[x][y];
                    int maxDistance = nowDistance;
                    int nextX = -1;
                    int nextY = -1;
                    boolean canExit = false;
                    boolean canMove = false;
                    for (int[] delta : DELTAS) {
                        int dx = x + delta[0];
                        int dy = y + delta[1];
                        if (dx < 1 || dx > n || dy < 1 || dy > n) {
                            continue;
                        }
                        if (maze[dx][dy] == EXIT) {
                            canExit = true;
                            break;
                        }
                        if (maze[dx][dy] == EMPTY) {
                            int nextDistance = getDistance(dx, dy, exitX, exitY);
                            if (maxDistance > nextDistance) {
                                nextX = dx;
                                nextY = dy;
                                maxDistance = nextDistance;
                                canMove = true;
                            }
                        }
                    }
                    if (canExit) {
                        totalMove += size;
                        m -= size;
                        players[x][y] = 0;
                        continue;
                    }
                    if (canMove) {
                        totalMove += size;
                        movingPlayers[nextX][nextY] += players[x][y];
                    } else {
                        movingPlayers[x][y] += players[x][y];
                    }
                    players[x][y] = 0;
                }
            }
        }
        for (int i = 1; i <= n; i++) {
            for (int j = 1; j <= n; j++) {
                players[i][j] += movingPlayers[i][j];
                movingPlayers[i][j] = 0;
            }
        }
    }

    private static int[] findSquare() {
        int size = 2;
        while (size <= n) {
            for (int startX = 1; startX <= n - size + 1; startX++) {
                for (int startY = 1; startY <= n - size + 1; startY++) {
                    int exit = 0;
                    int player = 0;
                    for (int x = 0; x < size; x++) {
                        for (int y = 0; y < size; y++) {
                            if (players[startX + x][startY + y] > 0) {
                                player += players[startX + x][startY + y];
                            }
                            if (maze[startX + x][startY + y] == EXIT) {
                                exit++;
                            }
                        }
                    }
                    if (exit == 1 && player >= 1) {
                        return new int[]{startX, startY, size};
                    }
                }
            }
            size++;
        }
        return null;
    }

    private static void rotateSquare(int startX, int startY, int size) {
        for (int x = 0; x < size; x++) {
            for (int y = 0; y < size; y++) {
                movingPlayers[y + 1][size - x] += players[startX + x][startY + y];
                players[startX + x][startY + y] = 0;
                movingMaze[y + 1][size - x] = maze[startX + x][startY + y];
                maze[startX + x][startY + y] = 0;
            }
        }
        for (int x = 0; x < size; x++) {
            for (int y = 0; y < size; y++) {
                players[startX + x][startY + y] += movingPlayers[x + 1][y + 1];
                movingPlayers[x + 1][y + 1] = 0;
                maze[startX + x][startY + y] = movingMaze[x + 1][y + 1];
                if (maze[startX + x][startY + y] < 0) {
                    maze[startX + x][startY + y] = 0;
                } else if (maze[startX + x][startY + y] > EMPTY && maze[startX + x][startY + y] != EXIT) {
                    maze[startX + x][startY + y]--;
                }
                movingMaze[x + 1][y + 1] = 0;
                if (maze[startX + x][startY + y] == EXIT) {
                    exitX = startX + x;
                    exitY = startY + y;
                }
            }
        }
    }

    private static int getDistance(int x1, int y1, int x2, int y2) {
        return Math.abs(x1 - x2) + Math.abs(y1 - y2);
    }
}
