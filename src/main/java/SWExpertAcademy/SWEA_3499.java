package SWExpertAcademy;

import java.io.*;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class SWEA_3499 {

    public static void main(String[] args) throws IOException {
        BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bufferedWriter = new BufferedWriter(new OutputStreamWriter(System.out));
        int testCase = Integer.parseInt(bufferedReader.readLine());
        for (int t = 1; t <= testCase; t++) {
            int cardNum = Integer.parseInt(bufferedReader.readLine());
            Queue<String> oddCard = new LinkedList<>();
            Queue<String> evenCard = new LinkedList<>();
            StringTokenizer input = new StringTokenizer(bufferedReader.readLine());
            int center;
            if (cardNum % 2 == 1) {
                center = cardNum / 2 + 1;
            } else {
                center = cardNum / 2;
            }
            for (int i = 1; i <= cardNum; i++) {
                if (i <= center) {
                    oddCard.offer(input.nextToken());
                } else {
                    evenCard.offer(input.nextToken());
                }
            }
            StringBuilder output = new StringBuilder();
            for (int i = 1; i <= cardNum; i++) {
                if (i % 2 == 1) {
                    output.append(oddCard.poll());
                } else {
                    output.append(evenCard.poll());
                }
                output.append(" ");
            }
            bufferedWriter.write("#" + t + " " + output + "\n");
        }
        bufferedReader.close();
        bufferedWriter.flush();
        bufferedWriter.close();
    }
}
