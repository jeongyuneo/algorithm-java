package Baekjoon;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class BOJ_1817 {

    public static void main(String[] args) throws IOException {
        BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer stringTokenizer = new StringTokenizer(bufferedReader.readLine());
        int n = Integer.parseInt(stringTokenizer.nextToken());
        int m = Integer.parseInt(stringTokenizer.nextToken());
        int box = 0;
        if (n > 0) {
            box++;
            stringTokenizer = new StringTokenizer(bufferedReader.readLine());
            int weight = 0;
            for (int i = 0; i < n; i++) {
                int book = Integer.parseInt(stringTokenizer.nextToken());
                weight += book;
                if (weight > m) {
                    box++;
                    weight = book;
                }
            }
        }
        System.out.println(box);
    }
}
