package SWExpertAcademy;

import java.util.Scanner;

public class SWEA_5215 {

    private static int N;
    private static int limitCalorie;
    private static int[][] ingredients;
    private static boolean[] isSelected;
    private static int maxScore;

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
            isSelected = new boolean[N];
            subSet(0);
            System.out.printf("#%d %d\n", n, maxScore);
        }
    }

    private static void subSet(int cnt) {
        // 기저 조건
        // step 01. N-1 인덱스까지의 모든 요소 처리 완료 후, 햄버거 재료의 조합이 완성됨
        if (cnt == N) {
            // step 02. 햄버거의 선호도와 총 칼로리 구하기
            int kcal = 0;
            int score = 0;
            for (int i = 0; i < N; i++) {
                if (isSelected[i]) {
                    score += ingredients[i][0];
                    kcal += ingredients[i][1];
                }
            }

            // step 03. 총칼로리가 L이하이고 햄버거 선호도가 최댓값이라면 갱신
            if (kcal <= limitCalorie) {
                maxScore = Math.max(score, maxScore);
            }
            return;
        }
        isSelected[cnt] = true;
        subSet(cnt+1);
        isSelected[cnt] = false;
        subSet(cnt+1);
    }
}
