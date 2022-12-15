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
        boolean[] isVisited = new boolean[100001];
        isVisited[n] = true;
        Deque<int[]> deque = new ArrayDeque<>();
        deque.offer(new int[]{n, 0});
        while (!deque.isEmpty()) {
            int[] current = deque.poll();
            int location = current[LOCATION];
            int time = current[TIME];
            if (location == k) {
                System.out.println(time);
                break;
            }
            int nextLocation = location * 2;
            if (nextLocation <= 100000 && !isVisited[nextLocation]) {
                isVisited[nextLocation] = true;
                deque.offerFirst(new int[]{nextLocation, time});
            }
            for (int move = -1; move <= 1; move += 2) {
                nextLocation = location + move;
                if (nextLocation >= 0 && nextLocation <= 100000 && !isVisited[nextLocation]) {
                    isVisited[nextLocation] = true;
                    deque.offer(new int[]{nextLocation, time + 1});
                }
            }
        }
    }
}
