package Baekjoon;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class BOJ_2437 {

    public static void main(String[] args) throws IOException {
        BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(System.in));
        int n = Integer.parseInt(bufferedReader.readLine());
        int[] pendulums = new int[n];
        StringTokenizer stringTokenizer = new StringTokenizer(bufferedReader.readLine());
        for (int i = 0; i < n; i++) {
            pendulums[i] = Integer.parseInt(stringTokenizer.nextToken());
        }
        Arrays.sort(pendulums);
        int weight = 1;
        for (int pendulum : pendulums) {
            if (weight < pendulum) {
                break;
            }
            weight += pendulum;
        }
        System.out.println(weight);
    }
}
