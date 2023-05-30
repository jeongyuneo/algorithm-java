package Baekjoon;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class BOJ_3980 {

    private static final int PLAYER_COUNT = 11;
    private static final int[][] ABILITIES = new int[PLAYER_COUNT][PLAYER_COUNT];
    private static final boolean[] IS_SELECTED = new boolean[PLAYER_COUNT];

    private static int max;

    public static void main(String[] args) throws IOException {
        BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(System.in));
        int testCase = Integer.parseInt(bufferedReader.readLine());
        StringBuilder answer = new StringBuilder();
        while (testCase-- > 0) {
            for (int i = 0; i < PLAYER_COUNT; i++) {
                StringTokenizer stringTokenizer = new StringTokenizer(bufferedReader.readLine());
                for (int j = 0; j < PLAYER_COUNT; j++) {
                    ABILITIES[i][j] = Integer.parseInt(stringTokenizer.nextToken());
                }
            }
            max = 0;
            selectPositions(ABILITIES, 0, 0);
            answer.append(max).append("\n");
            initialize();
        }
        System.out.println(answer);
    }

    private static void selectPositions(int[][] abilities, int player, int totalAbility) {
        if (player == PLAYER_COUNT) {
            max = Math.max(max, totalAbility);
            return;
        }
        for (int position = 0; position < PLAYER_COUNT; position++) {
            int ability = abilities[player][position];
            if (ability > 0 && !IS_SELECTED[position]) {
                IS_SELECTED[position] = true;
                selectPositions(abilities, player + 1, totalAbility + ability);
                IS_SELECTED[position] = false;
            }
        }
    }

    private static void initialize() {
        for (int i = 0; i < PLAYER_COUNT; i++) {
            Arrays.fill(ABILITIES[i], 0);
        }
    }
}
