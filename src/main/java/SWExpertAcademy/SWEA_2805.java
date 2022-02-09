package SWExpertAcademy;

import java.io.*;
import java.util.StringTokenizer;

public class SWEA_2805 {

    public static void main(String[] args) throws IOException {
        BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bufferedWriter = new BufferedWriter(new OutputStreamWriter(System.out));
        StringTokenizer stringTokenizer = new StringTokenizer(bufferedReader.readLine());
        int testCast = Integer.parseInt(stringTokenizer.nextToken());
        for (int t = 1; t <= testCast; t++) {
            stringTokenizer = new StringTokenizer(bufferedReader.readLine());
            int n = Integer.parseInt(stringTokenizer.nextToken());
            int[][] farm = new int[n][n];
            String input;
            for (int i = 0; i < n; i++) {
                input = bufferedReader.readLine();
                for (int j = 0; j < n; j++) {
                    farm[i][j] = input.charAt(j)-48;
                }
            }
            int sum = 0;
            int start = n/2;
            int farmRange = 1;
            for (int i = 0; i < n; i++) {
                for (int j = 0; j < farmRange; j++) {
                    sum += farm[i][start+j];
                }
                if (i <= start) {
                    start--;
                    farmRange += 2;
                } else {
                    start++;
                    farmRange -= 2;
                }
            }
            bufferedWriter.write("#" + t + " " + sum + "\n");
        }
        bufferedReader.close();
        bufferedWriter.flush();
        bufferedWriter.close();
    }
}
