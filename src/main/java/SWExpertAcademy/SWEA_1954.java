package SWExpertAcademy;

import java.util.Scanner;

public class SWEA_1954 {

    private static final int[][] DELTAS = {{0, 1}, {1, 0}, {0, -1}, {-1, 0}};

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int testCase = scanner.nextInt();
        for (int n = 1; n <= testCase; n++) {
            int size = scanner.nextInt();
            int[][] space = new int[size][size];
            int num = 1;
            int deltaIdx = 0;

            space[0][0] = num++;
            int dx = 0;
            int dy = 1;
            while (dx >= 0 && dx < size && dy >= 0 && dy < size && space[dx][dy] == 0) {
                space[dx][dy] = num++;
                dx += DELTAS[deltaIdx][0];
                dy += DELTAS[deltaIdx][1];
                if (dx < 0 || dx >= size || dy < 0 || dy >= size || space[dx][dy] != 0) {
                    dx -= DELTAS[deltaIdx][0];
                    dy -= DELTAS[deltaIdx][1];
                    if (++deltaIdx >= DELTAS.length) {
                        deltaIdx = 0;
                    }
                    dx += DELTAS[deltaIdx][0];
                    dy += DELTAS[deltaIdx][1];
                }
            }
            System.out.printf("#%d\n", n);
            for (int[] numbers : space) {
                for (int number : numbers) {
                    System.out.printf("%d ", number);
                }
                System.out.println();
            }
        }
    }
}
