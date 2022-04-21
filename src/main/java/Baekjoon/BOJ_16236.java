package Baekjoon;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class BOJ_16236 {

    private static final int[][] DELTAS = {{0, -1}, {-1, 0}, {1, 0}, {0, 1}};
    private static final int EMPTY = 0;
    private static final int[] BABY_SHARK = new int[2];

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
            int[] feed = findFeed();
            if (feed == null) {
                break;
            }
        }
        System.out.println(time);
    }

    private static int[] findFeed() {
        int[] feed = new int[]{n, n};
        boolean[][] isVisited = new boolean[n][n];
        Queue<int[]> queue = new LinkedList<>();

        boolean hasFeed = false;
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
                            if (feed[0] > dx || feed[0] == dx && feed[1] > dy) {
                                feed[0] = dx;
                                feed[1] = dy;
                            }
                            hasFeed = true;
                        } else if (space[dx][dy] == EMPTY || space[dx][dy] == size) {
                            queue.offer(new int[]{dx, dy});
                        }
                        isVisited[dx][dy] = true;
                    }
                }
            }

            if (hasFeed) {
                eat(feed);
                time += currentTime;
                return feed;
            }
        }
        return null;
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
