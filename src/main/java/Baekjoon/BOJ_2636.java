package Baekjoon;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.Queue;
import java.util.Stack;
import java.util.StringTokenizer;

public class BOJ_2636 {

    private static final int[][] DELTAS = {{0, 1}, {1, 0}, {0, -1}, {-1, 0}};
    private static final int CHEESE = 1;
    private static final int AIR = 0;

    public static void main(String[] args) throws IOException {
        BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer stringTokenizer = new StringTokenizer(bufferedReader.readLine());
        int height = Integer.parseInt(stringTokenizer.nextToken());
        int width = Integer.parseInt(stringTokenizer.nextToken());
        int[][] map = new int[height][width];
        int cheeseCount = 0;
        for (int i = 0; i < height; i++) {
            stringTokenizer = new StringTokenizer(bufferedReader.readLine());
            for (int j = 0; j < width; j++) {
                map[i][j] = Integer.parseInt(stringTokenizer.nextToken());
                if (map[i][j] == CHEESE) {
                    cheeseCount++;
                }
            }
        }
        Queue<int[]> air = new ArrayDeque<>();
        boolean[][] isAir = new boolean[height][width];
        int time = 0;
        Stack<Integer> cheeses = new Stack<>();
        int previousCheeseCount = cheeseCount;
        while (true) {
            time++;
            int[][] copiedMap = new int[height][width];
            for (int i = 0; i < height; i++) {
                for (int j = 0; j < width; j++) {
                    copiedMap[i][j] = map[i][j];
                    isAir[i][j] = false;
                }
            }
            air.offer(new int[]{0, 0});
            isAir[0][0] = true;
            while (!air.isEmpty()) {
                int[] current = air.poll();
                int x = current[0];
                int y = current[1];
                for (int[] delta : DELTAS) {
                    int dx = x + delta[0];
                    int dy = y + delta[1];
                    if (dx >= 0 && dx < height && dy >= 0 && dy < width && !isAir[dx][dy]) {
                        if (map[dx][dy] == AIR) {
                            air.offer(new int[]{dx, dy});
                        } else {
                            cheeseCount--;
                            copiedMap[dx][dy] = AIR;
                        }
                        isAir[dx][dy] = true;
                    }
                }
            }
            map = copiedMap;
            if (cheeseCount > 0) {
                previousCheeseCount = cheeseCount;
            } else {
                break;
            }
        }
        System.out.printf("%d\n%d", time, previousCheeseCount);
    }
}
