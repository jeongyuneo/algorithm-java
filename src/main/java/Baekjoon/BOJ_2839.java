package Baekjoon;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class BOJ_2839 {

    public static void main(String[] args) throws IOException {
        BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(System.in));
        int n = Integer.parseInt(bufferedReader.readLine());

        int cnt = 0;
        while (n > 0) {
            if ((n % 5) == 0) {
                cnt += n / 5;
                break;
            } else {
                n -= 3;
                cnt++;
            }
        }
        if (n < 0) {
            cnt = -1;
        }
        System.out.println(cnt);
    }
}
