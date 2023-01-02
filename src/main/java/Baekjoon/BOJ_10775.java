package Baekjoon;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class BOJ_10775 {

    public static void main(String[] args) throws IOException {
        BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(System.in));
        int gateNum = Integer.parseInt(bufferedReader.readLine());
        int airPlaneNum = Integer.parseInt(bufferedReader.readLine());
        int[] tries = new int[gateNum + 1];
        int dockingCount = 0;
        for (int i = 0; i < airPlaneNum; i++) {
            int airplane = Integer.parseInt(bufferedReader.readLine());
            while (tries[airplane] != 0) {
                airplane -= tries[airplane]++;
            }
            if (airplane != 0) {
                tries[airplane]++;
                dockingCount++;
            } else {
                break;
            }
        }
        System.out.println(dockingCount);
    }
}
