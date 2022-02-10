package Baekjoon;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class BOJ_2563 {

    private static final int PAPER_LENGTH = 100;
    private static final int BLACK_PAPER_LENGTH = 10;
    private static final boolean[][] PAPER = new boolean[PAPER_LENGTH+1][PAPER_LENGTH+1];

    public static void main(String[] args) throws IOException {
        BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer stringTokenizer;
        int testCase = Integer.parseInt(bufferedReader.readLine());
        int result = 0;
        for (int t = 0; t < testCase; t++) {
            stringTokenizer = new StringTokenizer(bufferedReader.readLine());
            int blackPaperX = Integer.parseInt(stringTokenizer.nextToken());
            int blackPaperY = Integer.parseInt(stringTokenizer.nextToken());
            for (int i = 0; i < BLACK_PAPER_LENGTH; i++) {
                for (int j = 0; j < BLACK_PAPER_LENGTH; j++) {
                    if (!PAPER[blackPaperX+i][blackPaperY+j]) {
                        PAPER[blackPaperX+i][blackPaperY+j] = true;
                        result++;
                    }
                }
            }
        }
        System.out.println(result);
    }
}
