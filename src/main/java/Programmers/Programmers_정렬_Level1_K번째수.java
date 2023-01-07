package Programmers;

import java.util.Arrays;

public class Programmers_정렬_Level1_K번째수 {

    public static void main(String[] args) {
        System.out.println(Arrays.toString(solution(new int[]{1, 5, 2, 6, 3, 7, 4}, new int[][]{{2, 5, 3}, {4, 4, 1}, {1, 7, 3}})));
    }

    private static final int START = 0;
    private static final int END = 1;
    private static final int K = 2;

    public static int[] solution(int[] array, int[][] commands) {
        int[] answer = new int[commands.length];
        for (int turn = 0; turn < commands.length; turn++) {
            int[] copiedArray = Arrays.copyOfRange(array, commands[turn][START] - 1, commands[turn][END]);
            Arrays.sort(copiedArray);
            answer[turn] = copiedArray[commands[turn][K] - 1];
        }
        return answer;
    }
}
