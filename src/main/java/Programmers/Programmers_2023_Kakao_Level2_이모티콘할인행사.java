package Programmers;

import java.util.Arrays;
import java.util.PriorityQueue;

public class Programmers_2023_Kakao_Level2_이모티콘할인행사 {

    public static void main(String[] args) {
        System.out.println(Arrays.toString(solution(new int[][]{{40, 10000}, {25, 10000}}, new int[]{7000, 9000})));
        System.out.println(Arrays.toString(solution(new int[][]{{40, 2900}, {23, 10000}, {11, 5200}, {5, 5900}, {40, 3100}, {27, 9200}, {32, 6900}}, new int[]{1300, 1500, 1600, 4900})));
    }

    private static final int COUNT = 0;
    private static final int PAY = 1;
    private static final int RATE = 0;
    private static final int PRICE = 1;
    private static final int[] DISCOUNTS = {40, 30, 20, 10};
    private static final PriorityQueue<int[]> PAY_INFOS = new PriorityQueue<>((p1, p2) -> {
        if (p1[COUNT] == p2[COUNT]) {
            return p2[PAY] - p1[PAY];
        }
        return p2[COUNT] - p1[COUNT];
    });

    private static int[][] usersCopy;
    private static int[] emoticonsCopy;
    private static int[] selected;
    private static int emoticonCount;

    public static int[] solution(int[][] users, int[] emoticons) {
        usersCopy = users;
        emoticonsCopy = emoticons;
        emoticonCount = emoticons.length;
        selected = new int[emoticonCount];
        selectDiscounts(0);
        return PAY_INFOS.poll();
    }

    private static void selectDiscounts(int cnt) {
        if (cnt == emoticonCount) {
            calculate();
            return;
        }
        for (int i = 0; i < 4; i++) {
            selected[cnt] = DISCOUNTS[i];
            selectDiscounts(cnt + 1);
        }
    }

    private static void calculate() {
        int join = 0;
        int price = 0;
        for (int[] user : usersCopy) {
            int pay = 0;
            for (int i = 0; i < emoticonCount; i++) {
                if (user[RATE] <= selected[i]) {
                    pay += (int) (emoticonsCopy[i] * (100 - selected[i]) / 100.0);
                }
            }
            if (pay >= user[PRICE]) {
                join++;
            } else {
                price += pay;
            }
        }
        PAY_INFOS.offer(new int[]{join, price});
    }
}
