package Baekjoon;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class BOJ_13913 {

    private static final int LOCATION = 0;
    private static final int TIME = 1;

    public static void main(String[] args) throws IOException {
        BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer stringTokenizer = new StringTokenizer(bufferedReader.readLine());
        int n = Integer.parseInt(stringTokenizer.nextToken());
        int k = Integer.parseInt(stringTokenizer.nextToken());
        PriorityQueue<int[]> subin = new PriorityQueue<>(Comparator.comparing(info -> info[TIME]));
        subin.offer(new int[]{n, 0});
        boolean[] isVisited = new boolean[100001];
        int[] times = new int[100001];
        int[] parents = new int[100001];
        Arrays.fill(times, Integer.MAX_VALUE);
        times[n] = 0;
        parents[n] = -1;
        while (!subin.isEmpty()) {
            int[] current = subin.poll();
            int location = current[LOCATION];
            int time = current[TIME];
            if (location == k) {
                List<Integer> traces = new ArrayList<>();
                traces.add(location);
                int parent = parents[location];
                while (parent > 0) {
                    traces.add(parent);
                    parent = parents[parent];
                }
                traces.add(parent);

                StringBuilder answer = new StringBuilder();
                answer.append(time).append("\n");
                for (int i = time; i >= 0; i--) {
                    answer.append(traces.get(i)).append(" ");
                }
                System.out.println(answer);
                break;
            }

            if (isVisited[location]) {
                continue;
            }

            isVisited[location] = true;
            int[] nexts = {location - 1, location + 1, location * 2};
            for (int next : nexts) {
                if (next >= 0 && next <= 100000 && !isVisited[next] && times[next] > time + 1) {
                    times[next] = time + 1;
                    parents[next] = location;
                    subin.offer(new int[]{next, time + 1});
                }
            }
        }
    }
}
