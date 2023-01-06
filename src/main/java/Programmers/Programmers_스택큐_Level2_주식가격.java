package Programmers;

import java.util.Arrays;
import java.util.Stack;

public class Programmers_스택큐_Level2_주식가격 {

    public static void main(String[] args) {
        System.out.println(Arrays.toString(solution(new int[]{1, 2, 3, 2, 3})));
    }

    private static final int INDEX = 0;
    private static final int PRICE = 1;

    public static int[] solution(int[] prices) {
        int priceLength = prices.length;
        int[] answer = new int[priceLength];
        Stack<int[]> stocks = new Stack<>();
        for (int current = 0; current < priceLength - 1; current++) {
            answer[current] = priceLength - current - 1;
            while (!stocks.isEmpty() && stocks.peek()[PRICE] > prices[current]) {
                int reductionIndex = stocks.pop()[INDEX];
                answer[reductionIndex] = current - reductionIndex;
            }
            stocks.push(new int[]{current, prices[current]});
        }
        return answer;
    }
}
