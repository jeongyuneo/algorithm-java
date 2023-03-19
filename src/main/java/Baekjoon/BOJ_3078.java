package Baekjoon;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.Queue;
import java.util.StringTokenizer;

public class BOJ_3078 {

    public static void main(String[] args) throws IOException {
        BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer stringTokenizer = new StringTokenizer(bufferedReader.readLine());
        int n = Integer.parseInt(stringTokenizer.nextToken());
        int k = Integer.parseInt(stringTokenizer.nextToken());
        Queue<Integer>[] students = new Queue[21];
        for (int i = 2; i < 21; i++) {
            students[i] = new ArrayDeque<>();
        }
        long goodFriends = 0;
        for (int ranking = 0; ranking < n; ranking++) {
            int lengthOfName = bufferedReader.readLine().length();
            if (!students[lengthOfName].isEmpty()) {
                while (!students[lengthOfName].isEmpty() && ranking - students[lengthOfName].peek() > k) {
                    students[lengthOfName].poll();
                }
                goodFriends += students[lengthOfName].size();
            }
            students[lengthOfName].offer(ranking);
        }
        System.out.println(goodFriends);
    }
}
