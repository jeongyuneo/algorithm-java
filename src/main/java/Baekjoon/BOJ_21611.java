package Baekjoon;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.Queue;
import java.util.StringTokenizer;

public class BOJ_21611 {

    private static final int[][] DELTAS = {{-1, 0}, {1, 0}, {0, -1}, {0, 1}};
    private static final int[] DIRECTIONS = {2, 1, 3, 0};
    private static final Queue<Integer> MARBLES = new ArrayDeque<>();
    private static final Queue<Integer> CONVERTED_MARBLES = new ArrayDeque<>();
    private static final int[] EXPLODED_MARBLES = new int[4];
    private static final int EMPTY = 0;

    private static int[][] map;
    private static int[][] explodingLocations;
    private static int n;

    public static void main(String[] args) throws IOException {
        BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer stringTokenizer = new StringTokenizer(bufferedReader.readLine());
        n = Integer.parseInt(stringTokenizer.nextToken());
        int m = Integer.parseInt(stringTokenizer.nextToken());
        map = new int[n][n];
        for (int i = 0; i < n; i++) {
            stringTokenizer = new StringTokenizer(bufferedReader.readLine());
            for (int j = 0; j < n; j++) {
                map[i][j] = Integer.parseInt(stringTokenizer.nextToken());
            }
        }
        while (m-- > 0) {
            findExplodingLocations(new StringTokenizer(bufferedReader.readLine()));
            destroy();
            findRest();
            explode();
            convert();
        }
        System.out.println(getAnswer());
    }

    private static void findExplodingLocations(StringTokenizer stringTokenizer) {
        int direction = Integer.parseInt(stringTokenizer.nextToken()) - 1;
        int distance = Integer.parseInt(stringTokenizer.nextToken());
        explodingLocations = new int[distance][2];
        int x = n / 2;
        int y = n / 2;
        for (int i = 0; i < distance; i++) {
            x += DELTAS[direction][0];
            y += DELTAS[direction][1];
            explodingLocations[i][0] = x;
            explodingLocations[i][1] = y;
        }
    }

    private static void destroy() {
        for (int[] explodingLocation : explodingLocations) {
            map[explodingLocation[0]][explodingLocation[1]] = EMPTY;
        }
    }

    private static void findRest() {
        int[] moves = new int[]{1, 1, 2, 2};
        int x = n / 2;
        int y = n / 2;
        int dir = 0;
        while (true) {
            int move = moves[dir];
            while (move-- > 0) {
                x += DELTAS[DIRECTIONS[dir]][0];
                y += DELTAS[DIRECTIONS[dir]][1];
                if (map[x][y] != EMPTY) {
                    MARBLES.offer(map[x][y]);
                }
                map[x][y] = EMPTY;
                if (x == 0 && y == 0) {
                    return;
                }
            }
            moves[dir] += 2;
            dir = (dir + 1) % 4;
        }
    }

    private static void explode() {
        boolean isExploded = false;
        int size = MARBLES.size();
        while (size-- > 0) {
            int marble = MARBLES.poll();
            int count = 1;
            while (size > 0 && !MARBLES.isEmpty() && MARBLES.peek() == marble) {
                MARBLES.poll();
                size--;
                count++;
            }
            if (count < 4) {
                while (count-- > 0) {
                    MARBLES.offer(marble);
                }
            } else {
                EXPLODED_MARBLES[marble] += count;
                isExploded = true;
            }
        }
        if (isExploded) {
            explode();
        }
    }

    private static void convert() {
        while (!MARBLES.isEmpty()) {
            int marble = MARBLES.poll();
            int count = 1;
            while (!MARBLES.isEmpty() && MARBLES.peek() == marble) {
                MARBLES.poll();
                count++;
            }
            CONVERTED_MARBLES.offer(count);
            CONVERTED_MARBLES.offer(marble);
        }
        putConvertedMarblesToMap();
    }

    private static void putConvertedMarblesToMap() {
        int[] moves = new int[]{1, 1, 2, 2};
        int x = n / 2;
        int y = n / 2;
        int dir = 0;
        while (true) {
            int move = moves[dir];
            while (move-- > 0) {
                x += DELTAS[DIRECTIONS[dir]][0];
                y += DELTAS[DIRECTIONS[dir]][1];
                if (CONVERTED_MARBLES.isEmpty()) {
                    return;
                }
                map[x][y] = CONVERTED_MARBLES.poll();
                if (x == 0 && y == 0) {
                    CONVERTED_MARBLES.clear();
                    return;
                }
            }
            moves[dir] += 2;
            dir = (dir + 1) % 4;
        }
    }

    private static int getAnswer() {
        int answer = 0;
        for (int i = 1; i < 4; i++) {
            answer += i * EXPLODED_MARBLES[i];
        }
        return answer;
    }
}
