package Programmers;

public class Programmers_완전탐색_Level2_피로도 {

    public static void main(String[] args) {
        System.out.println((solution(80, new int[][]{{80, 20}, {50, 40}, {30, 10}})));
    }

    private static final int MIN = 0;
    private static final int COST = 1;

    private static int[][] dungeonsCopy;
    private static int[] selected;
    private static boolean[] isSelected;
    private static int n;
    private static int answer;

    public static int solution(int k, int[][] dungeons) {
        dungeonsCopy = dungeons;
        n = dungeons.length;
        selected = new int[n];
        isSelected = new boolean[n];
        selectDungeons(k, 0);
        return answer;
    }

    private static void selectDungeons(int k, int cnt) {
        if (cnt == n) {
            updateFindableCount(k);
            return;
        }
        for (int i = 0; i < n; i++) {
            if (!isSelected[i]) {
                isSelected[i] = true;
                selected[cnt] = i;
                selectDungeons(k, cnt + 1);
                isSelected[i] = false;
            }
        }
    }

    private static void updateFindableCount(int k) {
        int count = 0;
        for (int order : selected) {
            if (k >= dungeonsCopy[order][MIN]) {
                count++;
                k -= dungeonsCopy[order][COST];
            } else {
                break;
            }
        }
        answer = Math.max(answer, count);
    }
}
