package Baekjoon;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class BOJ_3020 {

    public static void main(String[] args) throws IOException {
        BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer stringTokenizer = new StringTokenizer(bufferedReader.readLine());
        int n = Integer.parseInt(stringTokenizer.nextToken());
        int h = Integer.parseInt(stringTokenizer.nextToken());
        int[] floor = new int[h];
        int[] ceiling = new int[h];
        for (int i = 0; i < n; i++) {
            int length = Integer.parseInt(bufferedReader.readLine()) - 1;
            if (i % 2 == 0) {
                floor[length]++;
            } else {
                ceiling[length]++;
            }
        }
        for (int i = h - 2; i >= 0; i--) {
            floor[i] += floor[i + 1];
            ceiling[i] += ceiling[i + 1];
        }
        int obstacle = n;
        int section = 0;
        for (int i = 0; i < h; i++) {
            int breakingObstacle = floor[i] + ceiling[h - i - 1];
            if (breakingObstacle < obstacle) {
                obstacle = breakingObstacle;
                section = 1;
            } else if (breakingObstacle == obstacle) {
                section++;
            }
        }
        System.out.println(obstacle + " " + section);
    }
}
