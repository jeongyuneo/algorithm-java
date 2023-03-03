package Baekjoon;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class BOJ_2412 {

    static class Groove {

        int x;
        int y;

        public Groove(int x, int y) {
            this.x = x;
            this.y = y;
        }

        @Override
        public boolean equals(Object o) {
            if (this == o) return true;
            if (o == null || getClass() != o.getClass()) return false;
            Groove groove = (Groove) o;
            return x == groove.x && y == groove.y;
        }

        @Override
        public int hashCode() {
            return Objects.hash(x, y);
        }
    }

    private static final Queue<int[]> QUEUE = new ArrayDeque<>();
    private static final Set<Groove> GROOVES = new HashSet<>();
    private static final int X = 0;
    private static final int Y = 1;
    private static final int MOVE = 2;

    public static void main(String[] args) throws IOException {
        BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer stringTokenizer = new StringTokenizer(bufferedReader.readLine());
        int n = Integer.parseInt(stringTokenizer.nextToken());
        int top = Integer.parseInt(stringTokenizer.nextToken());
        for (int i = 0; i < n; i++) {
            stringTokenizer = new StringTokenizer(bufferedReader.readLine());
            GROOVES.add(new Groove(Integer.parseInt(stringTokenizer.nextToken()), Integer.parseInt(stringTokenizer.nextToken())));
        }
        System.out.println(getMove(top));
    }

    private static int getMove(int top) {
        QUEUE.offer(new int[]{0, 0, 0});
        while (!QUEUE.isEmpty()) {
            int[] current = QUEUE.poll();
            int x = current[X];
            int y = current[Y];
            int move = current[MOVE];
            if (y == top) {
                return move;
            }
            for (int i = -2; i <= 2; i++) {
                for (int j = -2; j <= 2; j++) {
                    int dx = x + i;
                    int dy = y + j;
                    Groove next = new Groove(dx, dy);
                    if (GROOVES.contains(next)) {
                        QUEUE.offer(new int[]{dx, dy, move + 1});
                        GROOVES.remove(next);
                    }
                }
            }
        }
        return -1;
    }
}
