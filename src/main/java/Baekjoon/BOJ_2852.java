package Baekjoon;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Stack;

public class BOJ_2852 {

    private static final String DELIMITER = " ";
    private static final String TIME_DELIMITER = ":";
    private static final int SECONDS_PER_ONE_MINUTE = 60;
    private static final int TEAM = 0;
    private static final int GOAL_TIME = 1;
    private static final int GOAL_COUNT = 2;
    private static final int GAME_END_TIME = 48;

    public static void main(String[] args) throws IOException {
        BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(System.in));
        int n = Integer.parseInt(bufferedReader.readLine());
        Stack<int[]> goalInfos = new Stack<>();
        int[] winningTimes = new int[3];
        while (n-- > 0) {
            String[] goalInfo = bufferedReader.readLine().split(DELIMITER);
            int team = Integer.parseInt(goalInfo[0]);
            String[] timeInfo = goalInfo[1].split(TIME_DELIMITER);
            int goalTime = Integer.parseInt(timeInfo[0]) * SECONDS_PER_ONE_MINUTE + Integer.parseInt(timeInfo[1]);
            if (goalInfos.isEmpty()) {
                goalInfos.add(new int[]{team, goalTime, 1});
                continue;
            }
            int[] goalInfoOfWinningTeam = goalInfos.pop();
            compareGoals(team, goalInfoOfWinningTeam);
            calculateWinningTime(goalInfos, winningTimes, goalTime, goalInfoOfWinningTeam);
        }
        if (!goalInfos.isEmpty()) {
            int[] goalInfoOfWinningTeam = goalInfos.pop();
            winningTimes[goalInfoOfWinningTeam[TEAM]] += GAME_END_TIME * SECONDS_PER_ONE_MINUTE - goalInfoOfWinningTeam[GOAL_TIME];
        }
        System.out.printf("%02d:%02d\n%02d:%02d%n",
                winningTimes[1] / SECONDS_PER_ONE_MINUTE, winningTimes[1] % SECONDS_PER_ONE_MINUTE, winningTimes[2] / SECONDS_PER_ONE_MINUTE, winningTimes[2] % SECONDS_PER_ONE_MINUTE);
    }

    private static void compareGoals(int team, int[] goalInfoOfWinningTeam) {
        if (team == goalInfoOfWinningTeam[TEAM]) {
            goalInfoOfWinningTeam[GOAL_COUNT]++;
        } else {
            goalInfoOfWinningTeam[GOAL_COUNT]--;
        }
    }

    private static void calculateWinningTime(Stack<int[]> goalInfos, int[] winningTimes, int goalTime, int[] goalInfoOfWinningTeam) {
        if (goalInfoOfWinningTeam[GOAL_COUNT] != 0) {
            goalInfos.push(goalInfoOfWinningTeam);
        } else {
            winningTimes[goalInfoOfWinningTeam[TEAM]] += goalTime - goalInfoOfWinningTeam[GOAL_TIME];
        }
    }
}
