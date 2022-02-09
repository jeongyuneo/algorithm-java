package SWExpertAcademy;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Stack;

public class SWEA_1223 {

    public static void main(String[] args) throws IOException {
        BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder stringBuilder = new StringBuilder();
        Stack<String> operators;
        Stack<Integer> numbers;
        for (int t = 1; t <= 10; t++) {
            operators = new Stack<>();
            numbers = new Stack<>();
            int length = Integer.parseInt(bufferedReader.readLine());
            String input = bufferedReader.readLine();
            for (int i = 0; i < length; i++) {
                char next = input.charAt(i);
                if (next >= 48 && next < 58) {
                    numbers.push(next-48);
                } else {
                    operators.push(String.valueOf(next));
                }
            }
            int sum = 0;
            while (!operators.isEmpty() && !numbers.isEmpty()) {
                String operator = operators.pop();
                if (operator.equals("+")) {
                    sum += numbers.pop();
                } else {
                    int multiNum = numbers.pop() * numbers.pop();
                    numbers.push(multiNum);
                }
                if (numbers.size() == 1) {
                    sum += numbers.pop();
                }
            }
            stringBuilder.append("#" + t + " " + sum + "\n");
        }
        System.out.println(stringBuilder);
    }
}
