package Baekjoon;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class BOJ_16236 {

    private static final int[][] DELTAS = {{0, -1}, {-1, 0}, {1, 0}, {0, 1}};
    private static final int EMPTY = 0;
    private static final int[] BABY_SHARK = new int[2];
    private static final PriorityQueue<int[]> EATABLE_FISH = new PriorityQueue<>((o1, o2) -> {
        if (o1[0] == o2[0]) {
            return o1[1] - o2[1];
        }
        return o1[0] - o2[0];
    });

    private static int[][] space;
    private static int n;
    private static int size;
    private static int feeds;
    private static int time;

    public static void main(String[] args) throws IOException {
        BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(System.in));
        n = Integer.parseInt(bufferedReader.readLine());
        space = new int[n][n];
        size = 2;
        feeds = 0;
        for (int i = 0; i < n; i++) {
            StringTokenizer stringTokenizer = new StringTokenizer(bufferedReader.readLine());
            for (int j = 0; j < n; j++) {
                int input = Integer.parseInt(stringTokenizer.nextToken());
                space[i][j] = input;
                if (input == 9) {
                    BABY_SHARK[0] = i;
                    BABY_SHARK[1] = j;
                    space[i][j] = EMPTY;
                }
            }
        }

        while (true) {
            findFeed();
            if (EATABLE_FISH.isEmpty()) {
                break;
            }
            int[] feed = EATABLE_FISH.poll();
            eat(feed);
        }
        System.out.println(time);
    }

    private static void findFeed() {
        EATABLE_FISH.clear();
        boolean[][] isVisited = new boolean[n][n];
        Queue<int[]> queue = new LinkedList<>();

        int currentTime = 0;
        queue.offer(BABY_SHARK);
        while (!queue.isEmpty()) {
            currentTime++;
            int nextSearchNumber = queue.size();
            while (nextSearchNumber-- > 0) {
                int[] current = queue.poll();
                int x = current[0];
                int y = current[1];
                isVisited[x][y] = true;
                for (int[] delta : DELTAS) {
                    int dx = x + delta[0];
                    int dy = y + delta[1];
                    if (dx >= 0 && dx < n && dy >= 0 && dy < n && !isVisited[dx][dy] && space[dx][dy] <= size) {
                        if (space[dx][dy] != EMPTY && space[dx][dy] < size) {
                            EATABLE_FISH.offer(new int[]{dx, dy});
                        } else if (space[dx][dy] == EMPTY || space[dx][dy] == size) {
                            queue.offer(new int[]{dx, dy});
                        }
                        isVisited[dx][dy] = true;
                    }
                }
            }

            if (!EATABLE_FISH.isEmpty()) {
                time += currentTime;
                return;
            }
        }
    }

    public static void eat(int[] feed) {
        int x = feed[0];
        int y = feed[1];
        space[x][y] = EMPTY;
        if (++feeds == size) {
            size++;
            feeds = 0;
        }
        BABY_SHARK[0] = x;
        BABY_SHARK[1] = y;
    }
}
