package Baekjoon;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.StringTokenizer;

public class BOJ_1138 {

    public static void main(String[] args) throws IOException {
        BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(System.in));
        int n = Integer.parseInt(bufferedReader.readLine());
        int[] people = new int[n];
        StringTokenizer stringTokenizer = new StringTokenizer(bufferedReader.readLine());
        for (int i = 0; i < n; i++) {
            people[i] = Integer.parseInt(stringTokenizer.nextToken());
        }
        List<Integer> line = new ArrayList<>();
        for (int persion = n; persion > 0; persion--) {
            line.add(people[persion - 1], persion);
        }
        StringBuilder answer = new StringBuilder();
        for (int person : line) {
            answer.append(person).append(" ");
        }
        System.out.println(answer);
    }
}
