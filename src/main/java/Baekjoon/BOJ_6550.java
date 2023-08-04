package Baekjoon;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class BOJ_6550 {

    public static void main(String[] args) throws IOException {
        BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder result = new StringBuilder();
        while (true) {
            String line = bufferedReader.readLine();
            if (line == null || line.isEmpty()) {
                break;
            }
            StringTokenizer stringTokenizer = new StringTokenizer(line);
            char[] s = stringTokenizer.nextToken().toCharArray();
            char[] t = stringTokenizer.nextToken().toCharArray();
            int sLength = s.length;
            int tLength = t.length;
            int start = 0;
            int end = 0;
            while (start < sLength && end < tLength) {
                if (s[start] == t[end]) {
                    start++;
                }
                end++;
            }
            if (start == sLength) {
                result.append("Yes").append("\n");
            } else {
                result.append("No").append("\n");
            }
        }
        System.out.println(result);
    }
}
