package Baekjoon;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class BOJ_15661 {

    private static int[][] abilities;
    private static boolean[] isTeamStart;
    private static int n;
    private static int answer;

    public static void main(String[] args) throws IOException {
        BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(System.in));
        n = Integer.parseInt(bufferedReader.readLine());
        abilities = new int[n][n];
        for (int i = 0; i < n; i++) {
            StringTokenizer stringTokenizer = new StringTokenizer(bufferedReader.readLine());
            for (int j = 0; j < n; j++) {
                abilities[i][j] = Integer.parseInt(stringTokenizer.nextToken());
            }
        }
        isTeamStart = new boolean[n];
        answer = Integer.MAX_VALUE;
        for (int count = 1; count <= n / 2; count++) {
            divideTeam(count, 0, 0);
        }
        System.out.println(answer);
    }

    private static void divideTeam(int count, int cnt, int start) {
        if (cnt == count) {
            updateMinAbilityGap();
            return;
        }

        for (int i = start; i < n; i++) {
            isTeamStart[i] = true;
            divideTeam(count, cnt + 1, i + 1);
            isTeamStart[i] = false;
        }
    }

    private static void updateMinAbilityGap() {
        int abilityOfStart = 0;
        int abilityOfLink = 0;
        for (int i = 0; i < n - 1; i++) {
            for (int j = i + 1; j < n; j++) {
                if (isTeamStart[i] && isTeamStart[j]) {
                    abilityOfStart += abilities[i][j] + abilities[j][i];
                } else if (!isTeamStart[i] && !isTeamStart[j]) {
                    abilityOfLink += abilities[i][j] + abilities[j][i];
                }
            }
        }
        answer = Math.min(answer, Math.abs(abilityOfStart - abilityOfLink));
    }
}
