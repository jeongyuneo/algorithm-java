package SWExpertAcademy;

import java.util.Scanner;

public class SWEA_5215 {

    private static int N;
    private static int limitCalorie;
    private static int[][] ingredients;
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
            subSet(0, 0, 0);
            System.out.printf("#%d %d\n", n, maxScore);
        }
    }

    private static void subSet(int cnt, int score, int calorie) {
        // 기저 조건
        // step 01. N-1 인덱스까지의 모든 요소 처리 완료 후, 햄버거 재료의 조합이 완성됨
        if (cnt == N) {
            // step 03. 총칼로리가 L이하이고 햄버거 선호도가 최댓값이라면 갱신
            if (calorie <= limitCalorie) {
                maxScore = Math.max(score, maxScore);
            }
            return;
        } else if (calorie > limitCalorie) {    // 가지 치기
            return;
        }

        // cnt의 인덱스의 요소를 선택
        subSet(cnt+1, score+ingredients[cnt][0], calorie+ingredients[cnt][1]);
        // cnt의 인덱스의 요소를 비선택
        subSet(cnt+1, score, calorie);
    }
}
