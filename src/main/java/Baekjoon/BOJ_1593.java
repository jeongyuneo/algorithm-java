package Baekjoon;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class BOJ_1593 {

    public static void main(String[] args) throws IOException {
        BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer stringTokenizer = new StringTokenizer(bufferedReader.readLine());
        int g = Integer.parseInt(stringTokenizer.nextToken());
        int s = Integer.parseInt(stringTokenizer.nextToken());
        char[] word = bufferedReader.readLine().toCharArray();
        char[] content = bufferedReader.readLine().toCharArray();
        int[] wordCounts = new int['z' + 1];
        for (int i = 0; i < g; i++) {
            wordCounts[word[i]]++;
        }
        int[] counts = new int['z' + 1];
        for (int i = 0; i < g - 1; i++) {
            counts[content[i]]++;
        }
        int start = 0;
        int result = 0;
        while (start + g <= s) {
            counts[content[start + g - 1]]++;
            if (Arrays.equals(wordCounts, counts)) {
                result++;
            }
            counts[content[start++]]--;
        }
        System.out.println(result);
    }
}
