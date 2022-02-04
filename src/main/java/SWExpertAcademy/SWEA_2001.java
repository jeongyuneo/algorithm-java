package SWExpertAcademy;

import java.util.Scanner;

public class SWEA_2001 {

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int testCase = scanner.nextInt();
        for (int t = 1; t <= testCase; t++) {
            int n = scanner.nextInt();
            int m = scanner.nextInt();
            int[][] space = new int[n][n];
            for (int i = 0; i < n; i++) {
                for (int j = 0; j < n; j++) {
                    space[i][j] = scanner.nextInt();
                }
            }
            int x = 0;
            int y = 0;
            int maxNum = 0;
            for (int row = 0; row < n - m + 1; row++) {
                for (int col = 0; col < n - m + 1; col++) {
                    int sum = 0;
                    for (int i = row; i < row + m; i++) {
                        for (int j = col; j < col + m; j++) {
                            sum += space[i][j];
                        }
                    }
                    if (sum > maxNum) {
                        maxNum = sum;
                    }
                }
            }
            System.out.printf("#%d %d\n", t, maxNum);
        }
    }
}
