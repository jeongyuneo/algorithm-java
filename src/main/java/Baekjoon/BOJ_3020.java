package Baekjoon;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class BOJ_3020 {

    public static void main(String[] args) throws IOException {
        BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer stringTokenizer = new StringTokenizer(bufferedReader.readLine());
        int n = Integer.parseInt(stringTokenizer.nextToken());
        int h = Integer.parseInt(stringTokenizer.nextToken());
        int half = n / 2;
        int[] floor = new int[half];
        int[] ceiling = new int[half];
        for (int i = 0; i < half; i++) {
            floor[i] = Integer.parseInt(bufferedReader.readLine());
            ceiling[i] = Integer.parseInt(bufferedReader.readLine());
        }
        Arrays.sort(floor);
        Arrays.sort(ceiling);
        int obstacle = n;
        int section = 0;
        for (int i = 1; i <= h; i++) {
            int breakingObstacle = getBreakingObstacle(i, floor) + getBreakingObstacle(h + 1 - i, ceiling);
            if (breakingObstacle < obstacle) {
                obstacle = breakingObstacle;
                section = 1;
            } else if (breakingObstacle == obstacle) {
                section++;
            }
        }
        System.out.println(obstacle + " " + section);
    }

    private static int getBreakingObstacle(int h, int[] cave) {
        int start = 0;
        int end = cave.length;
        while (start < end) {
            int mid = (start + end) / 2;
            if (cave[mid] >= h) {
                end = mid;
            } else {
                start = mid + 1;
            }
        }
        return cave.length - start;
    }
}
