package SWExpertAcademy;

import java.io.*;
import java.util.ArrayList;
import java.util.List;
import java.util.StringTokenizer;

public class SWEA_1228 {

    public static void main(String[] args) throws IOException {
        BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder stringBuilder = new StringBuilder();
        for (int t = 1; t <= 10; t++) {
            int codeLength = Integer.parseInt(bufferedReader.readLine());
            List<Integer> codes = new ArrayList<>();
            StringTokenizer stringTokenizer = new StringTokenizer(bufferedReader.readLine(), " ");
            for (int i = 0; i < codeLength; i++) {
                codes.add(Integer.parseInt(stringTokenizer.nextToken()));
            }
            int commandNum = Integer.parseInt(bufferedReader.readLine());
            StringTokenizer commandLine = new StringTokenizer(bufferedReader.readLine());
            for (int i = 0; i < commandNum; i++) {
                String command = commandLine.nextToken();
                int x = Integer.parseInt(commandLine.nextToken());
                int y = Integer.parseInt(commandLine.nextToken());
                for (int j = 0; j < y; j++) {
                    codes.add(x+j, Integer.parseInt(commandLine.nextToken()));
                }
            }
            stringBuilder.append("#" + t + " ");
            for (int i = 0; i < 10; i++) {
                stringBuilder.append(codes.get(i) + " ");
            }
            stringBuilder.append("\n");
        }
        System.out.println(stringBuilder);
        bufferedReader.close();
    }
}
