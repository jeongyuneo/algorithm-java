package Baekjoon;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class BOJ_10775 {

    public static void main(String[] args) throws IOException {
        BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(System.in));
        int gateNum = Integer.parseInt(bufferedReader.readLine());
        int airPlaneNum = Integer.parseInt(bufferedReader.readLine());
        int[] parents = new int[gateNum + 1];
        for (int i = 1; i <= gateNum; i++) {
            parents[i] = i;
        }
        int dockingCount;
        for (dockingCount = 0; dockingCount < airPlaneNum; dockingCount++) {
            int airplane = Integer.parseInt(bufferedReader.readLine());
            while (parents[airplane] != airplane) {
                airplane = parents[airplane]--;
            }
            if (airplane == 0) {
                break;
            }
            parents[airplane]--;
        }
        System.out.println(dockingCount);
    }
}
