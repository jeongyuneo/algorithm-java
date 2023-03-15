package Baekjoon;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.StringTokenizer;

public class BOJ_2262 {

    public static void main(String[] args) throws IOException {
        BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(System.in));
        int n = Integer.parseInt(bufferedReader.readLine());
        StringTokenizer stringTokenizer = new StringTokenizer(bufferedReader.readLine());
        List<Integer> teams = new ArrayList<>();
        for (int i = 0; i < n; i++) {
            teams.add(Integer.parseInt(stringTokenizer.nextToken()));
        }
        int sum = 0;
        for (int team = n; team > 1; team--) {
            int index = teams.indexOf(team);
            if (index == 0) {
                sum += team - teams.get(index + 1);
            } else if (index == teams.size() - 1) {
                sum += team - teams.get(index - 1);
            } else {
                sum += Math.min(team - teams.get(index - 1), team - teams.get(index + 1));
            }
            teams.remove(index);
        }
        System.out.println(sum);
    }
}
