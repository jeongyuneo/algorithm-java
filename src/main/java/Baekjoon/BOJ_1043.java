package Baekjoon;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.StringTokenizer;

public class BOJ_1043 {

    private static int[] parents;
    private static List<Integer>[] parties;
    private static int[] peopleWhoKnowTruth;

    public static void main(String[] args) throws IOException {
        BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer stringTokenizer = new StringTokenizer(bufferedReader.readLine());
        int n = Integer.parseInt(stringTokenizer.nextToken());
        int m = Integer.parseInt(stringTokenizer.nextToken());
        int answer = m;
        parents = new int[n + 1];
        for (int i = 1; i <= n; i++) {
            parents[i] = i;
        }
        stringTokenizer = new StringTokenizer(bufferedReader.readLine());
        int peopleNumberWhoKnowsTruth = Integer.parseInt(stringTokenizer.nextToken());
        peopleWhoKnowTruth = new int[peopleNumberWhoKnowsTruth];
        if (peopleNumberWhoKnowsTruth != 0) {
            for (int i = 0; i < peopleNumberWhoKnowsTruth; i++) {
                int personWhoKnowsTruth = Integer.parseInt(stringTokenizer.nextToken());
                peopleWhoKnowTruth[i] = personWhoKnowsTruth;
            }
            parties = new List[m];
            for (int i = 0; i < m; i++) {
                stringTokenizer = new StringTokenizer(bufferedReader.readLine());
                int participantNumber = Integer.parseInt(stringTokenizer.nextToken());
                int previousParticipant = Integer.parseInt(stringTokenizer.nextToken());
                parties[i] = new ArrayList<>();
                parties[i].add(previousParticipant);
                for (int j = 1; j < participantNumber; j++) {
                    int participant = Integer.parseInt(stringTokenizer.nextToken());
                    parties[i].add(participant);
                    combine(previousParticipant, participant);
                    previousParticipant = participant;
                }
            }
            for (int party = 0; party < m; party++) {
                if (!canLie(party)) {
                    answer--;
                }
            }
        }
        System.out.println(answer);
    }

    private static void combine(int a, int b) {
        int rootA = find(a);
        int rootB = find(b);
        if (rootA == rootB) {
            return;
        }
        if (rootA < rootB) {
            parents[rootB] = rootA;
        } else {
            parents[rootA] = rootB;
        }
    }

    private static int find(int value) {
        if (parents[value] == value) {
            return value;
        }
        return parents[value] = find(parents[value]);
    }

    private static boolean canLie(int party) {
        for (int personWhoKnowTruth : peopleWhoKnowTruth) {
            int root = find(personWhoKnowTruth);
            for (int participant : parties[party]) {
                if (find(participant) == root) {
                    return false;
                }
            }
        }
        return true;
    }
}
