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
            String input;
            int sum = 0;
            int start = n/2;
            int farmRange = 0;
            for (int i = 0; i < n; i++) {
                input = bufferedReader.readLine();
                for (int j = 0; j < n; j++) {
                    int produce = input.charAt(j)-48;
                    if (j >= start && j <= start+farmRange) {
                        sum += produce;
                    }
                }
                if (i < n/2) {
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
