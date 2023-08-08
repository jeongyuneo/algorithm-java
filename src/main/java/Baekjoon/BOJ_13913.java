package Baekjoon;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class BOJ_13913 {

    private static final int LIMIT = 100000;

    private static int[] roots;

    public static void main(String[] args) throws IOException {
        BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer stringTokenizer = new StringTokenizer(bufferedReader.readLine());
        int n = Integer.parseInt(stringTokenizer.nextToken());
        int k = Integer.parseInt(stringTokenizer.nextToken());
        roots = new int[LIMIT + 1];
        roots[n] = -1;
        System.out.println(getResult(n, k));
    }

    private static StringBuilder getResult(int n, int k) {
        StringBuilder result = new StringBuilder();
        result.append(getMinTime(n, k)).append("\n");
        Stack<Integer> wayToFindSister = getWayToFindSister(k);
        while (!wayToFindSister.isEmpty()) {
            result.append(wayToFindSister.pop()).append(" ");
        }
        return result;
    }

    private static Stack<Integer> getWayToFindSister(int k) {
        Stack<Integer> wayToFindSister = new Stack<>();
        wayToFindSister.push(k);
        int root = k;
        while (roots[root] != -1) {
            wayToFindSister.push(roots[root]);
            root = roots[root];
        }
        return wayToFindSister;
    }

    private static int getMinTime(int n, int k) {
        boolean[] isVisited = new boolean[LIMIT + 1];
        int[] times = new int[LIMIT + 1];
        Arrays.fill(times, Integer.MAX_VALUE);
        times[n] = 0;
        Queue<int[]> subin = new ArrayDeque<>();
        subin.offer(new int[]{n, 0});
        while (!subin.isEmpty()) {
            int[] current = subin.poll();
            int location = current[0];
            int time = current[1];
            if (location == k) {
                return time;
            }
            isVisited[location] = true;
            if (location - 1 >= 0 && !isVisited[location - 1] && times[location - 1] > time + 1) {
                roots[location - 1] = location;
                times[location - 1] = time + 1;
                subin.offer(new int[]{location - 1, time + 1});
            }
            if (location + 1 <= LIMIT && !isVisited[location + 1] && times[location + 1] > time + 1) {
                roots[location + 1] = location;
                times[location + 1] = time + 1;
                subin.offer(new int[]{location + 1, time + 1});
            }
            if (location * 2 <= LIMIT && !isVisited[location * 2] && times[location * 2] > time + 1) {
                roots[location * 2] = location;
                times[location * 2] = time + 1;
                subin.offer(new int[]{location * 2, time + 1});
            }
        }
        return 0;
    }
}
