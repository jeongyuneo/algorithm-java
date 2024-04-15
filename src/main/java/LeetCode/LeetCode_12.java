package LeetCode;

public class LeetCode_12 {

    public String intToRoman(int num) {
        int[] integers = {1000, 900, 500, 400, 100, 90, 50, 40, 10, 9, 5, 4, 1};
        String[] romans = {"M", "CM", "D", "CD", "C", "XC", "L", "XL", "X", "IX", "V", "IV", "I"};
        String answer = "";
        for (int idx = 0, len = integers.length; idx < len; idx++) {
            answer += romans[idx].repeat(num / integers[idx]);
            num %= integers[idx];
        }
        return answer;
    }
}
