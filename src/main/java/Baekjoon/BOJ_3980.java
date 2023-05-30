package Baekjoon;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.StringTokenizer;

public class BOJ_3980 {

    private static final int PLAYER_COUNT = 11;
    private static final int POSITION = 1;
    private static final int ABILITY = 2;
    private static final boolean[] IS_SELECTED = new boolean[PLAYER_COUNT];

    private static int max;

    public static void main(String[] args) throws IOException {
        BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(System.in));
        int testCase = Integer.parseInt(bufferedReader.readLine());
        StringBuilder answer = new StringBuilder();
        while (testCase-- > 0) {
            List<int[]>[] abilities = new List[PLAYER_COUNT];
            for (int i = 0; i < PLAYER_COUNT; i++) {
                abilities[i] = new ArrayList<>();
            }
            for (int i = 0; i < PLAYER_COUNT; i++) {
                StringTokenizer stringTokenizer = new StringTokenizer(bufferedReader.readLine());
                for (int j = 0; j < PLAYER_COUNT; j++) {
                    int ability = Integer.parseInt(stringTokenizer.nextToken());
                    if (ability != 0) {
                        abilities[i].add(new int[]{i, j, ability});
                    }
                }
            }
            max = 0;
            selectPositions(abilities, 0, 0);
            answer.append(max).append("\n");
        }
        System.out.println(answer);
    }

    private static void selectPositions(List<int[]>[] abilities, int player, int totalAbility) {
        if (player == PLAYER_COUNT) {
            max = Math.max(max, totalAbility);
            return;
        }
        for (int[] ability : abilities[player]) {
            if (!IS_SELECTED[ability[POSITION]]) {
                IS_SELECTED[ability[POSITION]] = true;
                selectPositions(abilities, player + 1, totalAbility + ability[ABILITY]);
                IS_SELECTED[ability[POSITION]] = false;
            }
        }
    }
}
