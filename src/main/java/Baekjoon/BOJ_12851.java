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
            way = 0;
            Queue<Integer> subin = new LinkedList<>();
            boolean[] isVisited = new boolean[100001];
            subin.add(n);
            boolean isFound = false;
            while (!subin.isEmpty()) {
                time++;
                int size = subin.size();
                while (size-- > 0) {
                    int current = subin.poll();
                    isVisited[current] = true;
                    int[] nexts = {current - 1, current + 1, current * 2};
                    for (int next : nexts) {
                        if (next >= 0 && next <= 100000 && !isVisited[next]) {
                            if (next == k) {
                                isFound = true;
                                way++;
                            } else {
                                subin.offer(next);
                            }
                        }
                    }
                }
                if (isFound) {
                    break;
                }
            }
        } else if (n > k) {
            time = n - k;
        }
        System.out.println(time);
        System.out.println(way);
    }
}
