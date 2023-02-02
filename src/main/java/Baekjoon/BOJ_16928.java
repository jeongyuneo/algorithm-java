package Baekjoon;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.Queue;
import java.util.StringTokenizer;

public class BOJ_16928 {

    private static final Queue<Integer> QUEUE = new ArrayDeque<>();
    private static final int SIZE = 10;
    private static final int[] MOVE = new int[SIZE * SIZE + 1];
    private static final boolean[] IS_VISITED = new boolean[SIZE * SIZE + 1];
    private static final int[] DICE = {1, 2, 3, 4, 5, 6};

    public static void main(String[] args) throws IOException {
        BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer stringTokenizer = new StringTokenizer(bufferedReader.readLine());
        int n = Integer.parseInt(stringTokenizer.nextToken());
        int m = Integer.parseInt(stringTokenizer.nextToken());
        for (int i = 0; i < n + m; i++) {
            stringTokenizer = new StringTokenizer(bufferedReader.readLine());
            int from = Integer.parseInt(stringTokenizer.nextToken());
            MOVE[from] = Integer.parseInt(stringTokenizer.nextToken());
        }
        QUEUE.offer(1);
        int count = -1;
        while (!QUEUE.isEmpty()) {
            count++;
            int size = QUEUE.size();
            while (size-- > 0) {
                int current = QUEUE.poll();
                if (current == 100) {
                    QUEUE.clear();
                    break;
                }
                if (IS_VISITED[current]) {
                    continue;
                }
                IS_VISITED[current] = true;
                for (int number : DICE) {
                    int next = current + number;
                    if (next > 100) {
                        continue;
                    }
                    if (MOVE[next] != 0) {
                        QUEUE.offer(MOVE[next]);
                    } else {
                        QUEUE.offer(next);
                    }
                }
            }
        }
        System.out.println(count);
    }
}
