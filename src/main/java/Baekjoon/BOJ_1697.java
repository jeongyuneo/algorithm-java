package Baekjoon;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class BOJ_1697 {

    public static void main(String[] args) throws IOException {
        BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer stringTokenizer = new StringTokenizer(bufferedReader.readLine());
        int n = Integer.parseInt(stringTokenizer.nextToken());
        int k = Integer.parseInt(stringTokenizer.nextToken());
        boolean[] map = new boolean[100001];
        Queue<Integer> subin = new LinkedList<>();
        subin.add(n);
        int time = 0;
        boolean isFound = false;
        while (true) {
            int size = subin.size();
            while (size-- > 0) {
                int current = subin.poll();
                if (current == k) {
                    isFound = true;
                    break;
                }
                if (!map[current]) {
                    map[current] = true;
                    int[] movements = {current - 1, current + 1, current * 2};
                    for (int next : movements) {
                        if (next < 0 || next > 100000) {
                            continue;
                        }
                        if (next != k) {
                            subin.offer(next);
                        } else {
                            isFound = true;
                            break;
                        }
                    }
                    if (isFound) {
                        time++;
                        break;
                    }
                }
            }
            if (isFound) {
                break;
            }
            time++;
        }
        System.out.println(time);
    }
}
