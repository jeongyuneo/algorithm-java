package Baekjoon;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.HashSet;
import java.util.Set;
import java.util.StringTokenizer;

public class BOJ_1269 {

    public static void main(String[] args) throws IOException {
        BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer stringTokenizer = new StringTokenizer(bufferedReader.readLine());
        int lenA = Integer.parseInt(stringTokenizer.nextToken());
        int lenB = Integer.parseInt(stringTokenizer.nextToken());
        Set<Integer> A = new HashSet<>();
        Set<Integer> B = new HashSet<>();
        stringTokenizer = new StringTokenizer(bufferedReader.readLine());
        for (int i = 0; i < lenA; i++) {
            A.add(Integer.parseInt(stringTokenizer.nextToken()));
        }
        stringTokenizer = new StringTokenizer(bufferedReader.readLine());
        for (int i = 0; i < lenB; i++) {
            B.add(Integer.parseInt(stringTokenizer.nextToken()));
        }

        for (int b : B) {
            if (A.contains(b)) {
                lenA--;
            }
        }
        for (int a : A) {
            if (B.contains(a)) {
                lenB--;
            }
        }
        System.out.println(lenA + lenB);
    }
}
