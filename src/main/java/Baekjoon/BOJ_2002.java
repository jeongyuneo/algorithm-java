package Baekjoon;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;

public class BOJ_2002 {

    public static void main(String[] args) throws IOException {
        BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(System.in));
        int n = Integer.parseInt(bufferedReader.readLine());
        List<String> inCars = new ArrayList<>();
        for (int i = 0; i < n; i++) {
            inCars.add(bufferedReader.readLine());
        }
        List<String> outCars = new ArrayList<>();
        for (int i = 0; i < n; i++) {
            outCars.add(bufferedReader.readLine());
        }

        int cnt = 0;
        boolean[] isChecked = new boolean[n + 1];
        for (int current = 0; current < n; current++) {
            int idx = outCars.indexOf(inCars.get(current)) - 1;
            while (idx >= 0 && !isChecked[idx] && inCars.indexOf(outCars.get(idx)) > current) {
                isChecked[idx--] = true;
                cnt++;
            }
        }
        System.out.println(cnt);
    }
}
