package Programmers;

import java.util.Arrays;

public class Programmers_스택큐_Level2_주식가격 {

    public static void main(String[] args) {
        System.out.println(Arrays.toString(solution(new int[]{1, 2, 3, 2, 3})));
    }

    public static int[] solution(int[] prices) {
        int priceLength = prices.length;
        int[] answer = new int[priceLength];
        for (int i = 0; i < priceLength - 1; i++) {
            int price = prices[i];
            for (int j = i + 1; j < priceLength; j++) {
                answer[i]++;
                if (prices[j] < price) {
                    break;
                }
            }
        }
        return answer;
    }
}
