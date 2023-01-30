package Baekjoon;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.Queue;
import java.util.StringTokenizer;

public class BOJ_16953 {

    private static final int MAX = 1000000000;

    public static void main(String[] args) throws IOException {
        BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer stringTokenizer = new StringTokenizer(bufferedReader.readLine());
        long a = Long.parseLong(stringTokenizer.nextToken());
        long b = Long.parseLong(stringTokenizer.nextToken());
        long minOperationCount = -1;
        Queue<Long> queue = new ArrayDeque<>();
        queue.offer(a);
        int operationCount = 1;
        while (!queue.isEmpty()) {
            int size = queue.size();
            while (size-- > 0) {
                long current = queue.poll();
                if (current == b) {
                    minOperationCount = operationCount;
                    break;
                }
                if (current > b) {
                    continue;
                }
                long[] nexts = new long[]{current * 2, current * 10 + 1};
                for (long next : nexts) {
                    if (next <= MAX) {
                        queue.offer(next);
                    }
                }
            }
            operationCount++;
        }
        System.out.println(minOperationCount);
    }
}
