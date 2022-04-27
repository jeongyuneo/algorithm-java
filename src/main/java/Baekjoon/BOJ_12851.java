package Baekjoon;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class BOJ_12851 {

    public static void main(String[] args) throws IOException {
        BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer stringTokenizer = new StringTokenizer(bufferedReader.readLine());
        int n = Integer.parseInt(stringTokenizer.nextToken());
        int k = Integer.parseInt(stringTokenizer.nextToken());
        int time = 0;
        int way = 1;
        if (n < k) {
            boolean[] isVisited = new boolean[100001];
            Queue<Integer> queue = new LinkedList<>();
            queue.offer(n);
            way = 0;
            while (!queue.isEmpty()) {
                time++;
                int size = queue.size();
                // 5 -> 1
                // 4, 6, 10 -> 2
                // 3, 5, 8, 5, 7, 12, 9, 11, 20 -> 3
                // 2, 4, 6, 4, 6, 10, ..., 19, 21, 40 -> 4
                while (size-- > 0) {
                    int previous = queue.poll();
                    isVisited[previous] = true;
                    int[] currents = {previous - 1, previous + 1, previous * 2};
                    for (int current : currents) {
                        if (current > 0 && current <= 100000 && !isVisited[current]) {
                            if (current == k) {
                                way++;
                            } else {
                                queue.offer(current);
                            }
                        }
                    }
                }
                if (way != 0) {
                    queue.clear();
                }
            }
        } else if (n > k) {
            time = n - k;
        }

        System.out.println(time);
        System.out.println(way);
    }
}
