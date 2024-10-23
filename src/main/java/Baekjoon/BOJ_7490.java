package Baekjoon;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

public class BOJ_7490 {

    private static final List<String> FORMULAS = new ArrayList<>();

    private static int n;

    public static void main(String[] args) throws IOException {
        BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(System.in));
        int testCase = Integer.parseInt(bufferedReader.readLine());
        StringBuilder result = new StringBuilder();
        while (testCase-- > 0) {
            n = Integer.parseInt(bufferedReader.readLine());
            findOperation(1, "1");
            FORMULAS.sort(Comparator.naturalOrder());
            for (String formula : FORMULAS) {
                result.append(formula).append("\n");
            }
            FORMULAS.clear();
            result.append("\n");
        }
        System.out.println(result);
    }

    private static void findOperation(int number, String formula) {
        if (number == n) {
            String formulaWithoutSpace = formula.replace(" ", "");
            final String[] numbers = formulaWithoutSpace.split("[+-]");
            final String[] operations = formulaWithoutSpace.split("[1-9]+");
            final int result = operate(numbers, operations);
            if (result == 0) {
                FORMULAS.add(formula);
            }
            return;
        }
        final int next = number + 1;
        findOperation(next, formula + "+" + next);
        findOperation(next, formula + "-" + next);
        findOperation(next, formula + " " + next);
    }

    private static int operate(String[] numbers, String[] operations) {
        int result = Integer.parseInt(numbers[0]);
        int numIdx = 1;
        for (int i = 1, length = operations.length; i < length; i++) {
            String operation = operations[i];
            if (operation.equals("+")) {
                result += Integer.parseInt(numbers[numIdx]);
            } else {
                result -= Integer.parseInt(numbers[numIdx]);
            }
            numIdx++;
        }
        return result;
    }
}
