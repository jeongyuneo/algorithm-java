package Baekjoon;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class BOJ_15661 {

    private static int[][] abilities;
    private static int[] teamStart;
    private static int[] teamLink;
    private static boolean[] isTeamStart;
    private static int[] selectedMembers;
    private static int n;
    private static int answer;
    private static int abilityOfStart;
    private static int abilityOfLink;

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
            teamStart = new int[count];
            teamLink = new int[n - count];
            divideTeam(count, 0, 0);
        }
        System.out.println(answer);
    }

    private static void divideTeam(int count, int cnt, int start) {
        if (cnt == count) {
            setRestPeopleToTeamLink();
            updateTeamAbilities(count);
            answer = Math.min(answer, Math.abs(abilityOfStart - abilityOfLink));
            return;
        }
        for (int i = start; i < n; i++) {
            teamStart[cnt] = i;
            isTeamStart[i] = true;
            divideTeam(count, cnt + 1, i + 1);
            isTeamStart[i] = false;
        }
    }

    private static void setRestPeopleToTeamLink() {
        int idx = 0;
        for (int i = 0; i < n; i++) {
            if (!isTeamStart[i]) {
                teamLink[idx++] = i;
            }
        }
    }

    private static void updateTeamAbilities(int count) {
        abilityOfStart = 0;
        abilityOfLink = 0;
        if (count != 1) {
            selectedMembers = new int[count];
            getAbility(teamStart, count, 0, 0, true);
        }
        if (n - count != 1) {
            selectedMembers = new int[n - count];
            getAbility(teamLink, n - count, 0, 0, false);
        }
    }

    private static void getAbility(int[] team, int count, int cnt, int start, boolean isTeamStart) {
        if (cnt == 2) {
            if (isTeamStart) {
                abilityOfStart += abilities[selectedMembers[0]][selectedMembers[1]] + abilities[selectedMembers[1]][selectedMembers[0]];
            } else {
                abilityOfLink += abilities[selectedMembers[0]][selectedMembers[1]] + abilities[selectedMembers[1]][selectedMembers[0]];
            }
            return;
        }
        for (int i = start; i < count; i++) {
            selectedMembers[cnt] = team[i];
            getAbility(team, count, cnt + 1, i + 1, isTeamStart);
        }
    }
}
