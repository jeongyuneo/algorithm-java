package Programmers;

public class Programmers_위클리챌린지_Level1_부족한금액계산하기 {

    public static void main(String[] args) {
        System.out.println(solution(3, 20, 4));
    }

    public static long solution(int price, int money, int count) {
        long answer = money;
        for (int i = 1; i <= count; i++) {
            answer -= price * i;
        }
        if (answer > 0) {
            answer = 0;
        }
        return Math.max(-answer, 0);
    }
}
