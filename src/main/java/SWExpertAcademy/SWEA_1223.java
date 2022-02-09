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
            int sum = 0;
            for (int i = length-1; i >= 0; i--) {
                char next = input.charAt(i);
                if (next >= 48 && next < 58) {
                    if (i == 0) {
                        sum += next-48;
                        break;
                    }
                    numbers.push(next-48);
                } else {
                    if (next == '+') {
                        sum += numbers.pop();
                    } else {
                        numbers.push(numbers.pop() * (input.charAt(--i)-48));
                    }
                    operators.push(String.valueOf(next));
                }
                if (i == 0 && !numbers.isEmpty()) {
                    sum += numbers.pop();
                }
            }
            stringBuilder.append("#" + t + " " + sum + "\n");
        }
        System.out.println(stringBuilder);
    }
}
