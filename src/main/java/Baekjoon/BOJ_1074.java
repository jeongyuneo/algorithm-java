package Baekjoon;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class BOJ_1074 {

    public static void main(String[] args) throws IOException {
        BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer stringTokenizer = new StringTokenizer(bufferedReader.readLine());
        int n = Integer.parseInt(stringTokenizer.nextToken());
        int r = Integer.parseInt(stringTokenizer.nextToken());
        int c = Integer.parseInt(stringTokenizer.nextToken());
        int size = (int) Math.pow(2, n);
        divide(r, c, 0, 0, size*size, 0);
    }

    private static void divide(int r, int c, int startX, int startY, int size, int visit) {
        if (size==1) {
            System.out.println(visit);
            return;
        }

        size /= 4;
        int half = (int) Math.sqrt(size);
        if (r < startX+half & c < startY+half) {
            divide(r, c, startX, startY, size, visit);
        } else if (r < startX+half && c >= startY+half) {
            visit += size;
            divide(r, c, startX, startY+half, size, visit);
        } else if (r >= startX+half && c < startY+half) {
            visit += size * 2;
            divide(r, c, startX+half, startY, size, visit);
        } else {
            visit += size * 3;
            divide(r, c, startX+half, startY+half, size, visit);
        }
    }
}
