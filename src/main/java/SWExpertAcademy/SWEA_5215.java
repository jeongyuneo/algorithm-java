package SWExpertAcademy;

import java.util.Scanner;

public class SWEA_5215 {

    private static int N;
    private static int limitCalorie;
    private static int[][] ingredients;
    private static int maxScore;
    private static int scoreSum;
    private static int calorieSum;

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int testCase = scanner.nextInt();
        for (int n = 1; n <= testCase; n++) {
            N = scanner.nextInt();
            limitCalorie = scanner.nextInt();
            ingredients = new int[N][2];
            for (int i = 0; i < N; i++) {
                for (int j = 0; j < 2; j++) {
                    ingredients[i][j] = scanner.nextInt();
                }
            }
            maxScore = 0;
            scoreSum = 0;
            calorieSum = 0;
            subSet(0);
            System.out.printf("#%d %d\n", n, maxScore);
        }
    }

    private static void subSet(int cnt) {
        if (cnt == N) {
            if (limitCalorie >= calorieSum && maxScore < scoreSum) {
                maxScore = scoreSum;
            }
            return;
        }
        subSet(cnt+1);
        scoreSum += ingredients[cnt][0];
        calorieSum += ingredients[cnt][1];
        subSet(cnt+1);
        scoreSum -= ingredients[cnt][0];
        calorieSum -= ingredients[cnt][1];
    }
}
