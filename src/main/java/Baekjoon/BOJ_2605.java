package Baekjoon;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.StringTokenizer;

public class BOJ_2605 {

    public static void main(String[] args) throws IOException {
        BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(System.in));
        int n = Integer.parseInt(bufferedReader.readLine());
        StringTokenizer stringTokenizer = new StringTokenizer(bufferedReader.readLine());
        List<Integer> students = new ArrayList<>();
        for (int i = 1; i <= n; i++) {
            int chosenNumber = Integer.parseInt(stringTokenizer.nextToken());
            students.add(students.size() - chosenNumber, i);
        }

        for (int student : students) {
            System.out.print(student + " ");
        }
    }
}
