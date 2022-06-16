package Baekjoon;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class BOJ_15961 {

    public static void main(String[] args) throws IOException {
        BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer stringTokenizer = new StringTokenizer(bufferedReader.readLine());
        int n = Integer.parseInt(stringTokenizer.nextToken());
        int d = Integer.parseInt(stringTokenizer.nextToken());
        int k = Integer.parseInt(stringTokenizer.nextToken());
        int c = Integer.parseInt(stringTokenizer.nextToken());
        int[] rail = new int[n];
        int[] sushi = new int[d + 1];
        sushi[c]++;
        Queue<Integer> pick = new LinkedList<>();
        int max = 1;
        for (int i = 0; i < n; i++) {
            int input = Integer.parseInt(bufferedReader.readLine());
            rail[i] = input;
            if (i < k) {
                if (++sushi[input] == 1) {
                    max++;
                }
                pick.offer(input);
            }
        }

        int cnt = max;
        for (int i = 1; i < n; i++) {
            if (--sushi[pick.poll()] == 0) {
                cnt--;
            }
            int next = rail[(k + i - 1) % n];
            if (++sushi[next] == 1) {
                cnt++;
            }
            pick.offer(next);
            max = Math.max(max, cnt);
        }
        System.out.println(max);
    }
}
