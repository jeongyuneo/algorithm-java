package SWExpertAcademy;

import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;
import java.util.Stack;

public class SWEA_1218 {

    static Map<String, String> pairs = new HashMap<>();
    static Stack<String> inputStack = new Stack<>();
    static Stack<String> stack = new Stack<>();
    static String next;

    public static void main(String[] args) {
        pairs.put("(", ")");
        pairs.put("[", "]");
        pairs.put("{", "}");
        pairs.put("<", ">");
        Scanner scanner = new Scanner(System.in);
        for (int t = 1; t <= 10; t++) {
            int n = scanner.nextInt();
            String input = scanner.next();
            for (int i = 0; i < n; i++) {
                inputStack.push(String.valueOf(input.charAt(i)));
            }
            boolean isValid = true;
            while (isValid && !inputStack.isEmpty()) {
                next = inputStack.pop();
                if (pairs.containsKey(next)) {
                    if (!(pairs.get(next).equals(stack.pop()))) {
                        isValid = false;
                        inputStack.clear();
                    }
                } else {
                    stack.push(next);
                }
            }
            stack.clear();
            System.out.printf("#%d %d\n", t, isValid ? 1 : 0);
        }
    }
}
