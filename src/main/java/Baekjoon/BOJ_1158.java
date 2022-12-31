package Baekjoon;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.StringTokenizer;

public class BOJ_1158 {

    public static void main(String[] args) throws IOException {
        BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer stringTokenizer = new StringTokenizer(bufferedReader.readLine());
        StringBuilder stringBuilder = new StringBuilder();
        int n = Integer.parseInt(stringTokenizer.nextToken());
        int k = Integer.parseInt(stringTokenizer.nextToken());
        List<Integer> array = new ArrayList<>();
        for (int i = 1; i <= n; i++) {
            array.add(i);
        }
        int idx = k - 1;
        stringBuilder.append("<");
        while (array.size() > 0) {
            stringBuilder.append(array.get(idx)).append(", ");
            array.remove(idx--);
            int cnt = 0;
            while (cnt < k) {
                cnt++;
                if (++idx >= array.size()) {
                    idx = 0;
                }
            }
        }
        stringBuilder.setLength(stringBuilder.length() - 2);
        stringBuilder.append(">");
        System.out.println(stringBuilder);
    }
}
