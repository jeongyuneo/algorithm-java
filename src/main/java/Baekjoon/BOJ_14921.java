package Baekjoon;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class BOJ_14921 {

    public static void main(String[] args) throws IOException {
        BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(System.in));
        int n = Integer.parseInt(bufferedReader.readLine());
        int[] characters = new int[n];
        StringTokenizer stringTokenizer = new StringTokenizer(bufferedReader.readLine());
        for (int i = 0; i < n; i++) {
            characters[i] = Integer.parseInt(stringTokenizer.nextToken());
        }
        int closestCharacter = Integer.MAX_VALUE;
        int start = 0;
        int end = n - 1;
        while (start < end) {
            int sum = characters[start] + characters[end];
            if (Math.abs(sum) < Math.abs(closestCharacter)) {
                closestCharacter = sum;
                while (start + 1 < n && characters[start] == characters[start + 1]) {
                    start++;
                }
                while (end - 1 >= 0 && characters[end] == characters[end - 1]) {
                    end--;
                }
            }
            if (sum < 0) {
                start++;
            } else {
                end--;
            }
        }
        System.out.println(closestCharacter);
    }
}
