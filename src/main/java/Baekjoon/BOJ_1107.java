package Baekjoon;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class BOJ_1107 {

    private static boolean[] isBroken;

    public static void main(String[] args) throws IOException {
        BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(System.in));
        int n = Integer.parseInt(bufferedReader.readLine());
        int m = Integer.parseInt(bufferedReader.readLine());
        isBroken = new boolean[10];
        if (m > 0) {
            StringTokenizer stringTokenizer = new StringTokenizer(bufferedReader.readLine());
            for (int i = 0; i < m; i++) {
                isBroken[Integer.parseInt(stringTokenizer.nextToken())] = true;
            }
        }
        int click = Math.abs(n - 100);
        if (n != 100) {
            for (int channel = 0; channel <= 999999; channel++) {
                int pressWithNumberButton = getPressWithNumberButton(channel);
                if (pressWithNumberButton != 0) {
                    click = Math.min(click, pressWithNumberButton + Math.abs(channel - n));
                }
            }
        }
        System.out.println(click);
    }

    private static int getPressWithNumberButton(int channel) {
        if (channel == 0) {
            if (isBroken[channel]) {
                return 0;
            } else {
                return 1;
            }
        }
        int press = 0;
        while (channel > 0) {
            if (isBroken[channel % 10]) {
                return 0;
            }
            press++;
            channel /= 10;
        }
        return press;
    }
}
