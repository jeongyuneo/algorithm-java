package Baekjoon;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class BOJ_2246 {

    private static final int NUMBER = 0;
    private static final int DISTANCE = 1;
    private static final int COST = 2;

    public static void main(String[] args) throws IOException {
        BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(System.in));
        int n = Integer.parseInt(bufferedReader.readLine());
        int[][] condos = new int[n][3];
        for (int i = 0; i < n; i++) {
            StringTokenizer stringTokenizer = new StringTokenizer(bufferedReader.readLine());
            condos[i][NUMBER] = i;
            condos[i][DISTANCE] = Integer.parseInt(stringTokenizer.nextToken());
            condos[i][COST] = Integer.parseInt(stringTokenizer.nextToken());
        }
        List<Integer> candidatesForCase1 = getCandidates(n, condos, DISTANCE, COST);
        List<Integer> candidatesForCase2 = getCandidates(n, condos, COST, DISTANCE);
        int candidateCount = 0;
        for (int i = 0; i < n; i++) {
            if (candidatesForCase1.contains(i) && candidatesForCase2.contains(i)) {
                candidateCount++;
            }
        }
        System.out.println(candidateCount);
    }

    private static List<Integer> getCandidates(int n, int[][] condos, int standard1, int standard2) {
        Arrays.sort(condos, Comparator.comparing(condo -> condo[standard1]));
        List<Integer> candidatesForCase = new ArrayList<>(Collections.singletonList(condos[0][NUMBER]));
        int min = condos[0][standard2];
        for (int i = 1; i < n; i++) {
            int previous = condos[i - 1][standard1];
            int currentMin = condos[i - 1][standard2];
            while (i < n && previous == condos[i][standard1]) {
                if (condos[i][standard2] < min) {
                    candidatesForCase.add(condos[i][NUMBER]);
                    currentMin = Math.min(currentMin, condos[i][standard2]);
                }
                i++;
            }
            if (i >= n) {
                break;
            }
            min = Math.min(min, currentMin);
            if (condos[i][standard2] < min) {
                candidatesForCase.add(condos[i][NUMBER]);
            }
            min = Math.min(min, condos[i][standard2]);
        }
        return candidatesForCase;
    }
}
