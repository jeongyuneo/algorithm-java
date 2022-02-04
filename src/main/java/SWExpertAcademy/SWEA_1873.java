package SWExpertAcademy;

import java.util.Scanner;

public class SWEA_1873 {

    private static final char[] TANKS = {'^', 'v', '>', '<'};
    private static final int[][] DELTAS = {{-1, 0}, {1, 0}, {0, 1}, {0, -1}};

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int testCase = scanner.nextInt();
        for (int n = 1; n <= testCase; n++) {
            int height = scanner.nextInt();
            int width = scanner.nextInt();
            char[][] space = new char[height][width];
            int x = 0;
            int y = 0;
            int tankDirection = 0;
            for (int i = 0; i < height; i++) {
                String next = scanner.next();
                for (int j = 0; j < width; j++) {
                    space[i][j] = next.charAt(j);
                    for (int k = 0; k < TANKS.length; k++) {
                        if (space[i][j] == TANKS[k]) {
                            tankDirection = k;
                            x = i;
                            y = j;
                        }
                    }
                }
            }
            int inputNum = scanner.nextInt();
            String input = scanner.next();
            for (int i = 0; i < inputNum; i++) {
                char exec = input.charAt(i);
                if (exec == 'U' || exec == 'D' || exec == 'L' || exec == 'R') {
                    if (exec == 'U') {
                        tankDirection = 0;
                        space[x][y] = TANKS[tankDirection];
                    } else if (exec == 'D') {
                        tankDirection = 1;
                        space[x][y] = TANKS[tankDirection];
                    } else if (exec == 'R') {
                        tankDirection = 2;
                        space[x][y] = TANKS[tankDirection];
                    } else if (exec == 'L') {
                        tankDirection = 3;
                        space[x][y] = TANKS[tankDirection];
                    }
                    int dx = x + DELTAS[tankDirection][0];
                    int dy = y + DELTAS[tankDirection][1];
                    if (dx >= 0 && dx < height && dy >= 0 && dy < width && space[dx][dy] == '.') {
                        space[dx][dy] = space[x][y];
                        space[x][y] = '.';
                        x = dx;
                        y = dy;
                    }
                } else if (exec == 'S') {
                    int dx = x + DELTAS[tankDirection][0];
                    int dy = y + DELTAS[tankDirection][1];
                    while (dx >= 0 && dx < height && dy >= 0 && dy < width && (space[dx][dy] == '.' || space[dx][dy] == '*' || space[dx][dy] == '-')) {
                        if (space[dx][dy] == '.') {
                            space[dx][dy] = '.';
                        } else if (space[dx][dy] == '*') {
                            space[dx][dy] = '.';
                            break;
                        } else if (space[dx][dy] == '#') {
                            break;
                        }
                        dx += DELTAS[tankDirection][0];
                        dy += DELTAS[tankDirection][1];
                    }
                }
            }
            System.out.print("#" + n + " ");
            for (int i = 0; i < height; i++) {
                for (int j = 0; j < width; j++) {
                    System.out.print(space[i][j] + "");
                }
                System.out.println();
            }
        }
    }
}
