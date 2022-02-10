package SWExpertAcademy;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Stack;

public class SWEA_1233 {

    private static final char PLUS = '+';
    private static final char MINUS = '-';
    private static final char MULTIPLE = '*';
    private static final char DIVISION = '/';

    public static void main(String[] args) throws IOException {
        BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder stringBuilder = new StringBuilder();
        for (int t = 1; t <= 10; t++) {
            int nodeNum = Integer.parseInt(bufferedReader.readLine());

            Stack<String> input = new Stack<>();
            for (int i = 0; i < nodeNum; i++) {
                input.push(bufferedReader.readLine());
            }
            boolean isValid = true;
            while (!input.isEmpty()) {
                String[] nodeInfo = input.pop().split(" ");
                char node = nodeInfo[1].charAt(0);
                if (nodeInfo.length == 2 && node < 48 || node > 58) {
                    isValid = false;
                } else if (nodeInfo.length > 2 && node != PLUS && node != MINUS && node != MULTIPLE && node != DIVISION) {
                    isValid = false;
                }
            }
            stringBuilder.append("#")
                    .append(t)
                    .append(" ");
            if (isValid) {
                stringBuilder.append(1);
            } else {
                stringBuilder.append(0);
            }
            stringBuilder.append("\n");
        }
        System.out.println(stringBuilder);
    }
}
