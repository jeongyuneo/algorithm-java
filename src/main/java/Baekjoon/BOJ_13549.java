package Baekjoon;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class BOJ_13549 {

    private static final int LOCATION = 0;
    private static final int TIME = 1;

    public static void main(String[] args) throws IOException {
        BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer stringTokenizer = new StringTokenizer(bufferedReader.readLine());
        int n = Integer.parseInt(stringTokenizer.nextToken());
        int k = Integer.parseInt(stringTokenizer.nextToken());
        int[] space = new int[100001];
        Arrays.fill(space, Integer.MAX_VALUE);
        space[n] = 0;
        Deque<int[]> deque = new ArrayDeque<>();
        deque.offer(new int[]{n, 0});
        while (!deque.isEmpty()) {
            int[] current = deque.poll();
            int location = current[LOCATION];
            if (location == k) {
                break;
            }
            int time = current[TIME];
            for (int move = -1; move <= 1; move += 2) {
                if (location + move >= 0 && location + move <= 100000 && space[location + move] > time + 1) {
                    space[location + move] = time + 1;
                    deque.offer(new int[]{location + move, time + 1});
                }
            }
            if (location * 2 <= 100000 && space[location * 2] > time) {
                space[location * 2] = time;
                deque.offerFirst(new int[]{location * 2, time});
            }
        }
        System.out.println(space[k]);
    }
}
