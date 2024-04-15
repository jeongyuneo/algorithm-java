package LeetCode;

import java.util.Comparator;
import java.util.Map;
import java.util.TreeMap;

public class LeetCode_12 {

    public String intToRoman(int num) {
        Map<Integer, String> romans = new TreeMap<>(Comparator.reverseOrder());
        romans.put(1000, "M");
        romans.put(900, "CM");
        romans.put(500, "D");
        romans.put(400, "CD");
        romans.put(100, "C");
        romans.put(90, "XC");
        romans.put(50, "L");
        romans.put(40, "XL");
        romans.put(10, "X");
        romans.put(9, "IX");
        romans.put(5, "V");
        romans.put(4, "IV");
        romans.put(1, "I");
        String answer = "";
        for (Map.Entry<Integer, String> entry : romans.entrySet()) {
            answer += entry.getValue().repeat(num / entry.getKey());
            num %= entry.getKey();
        }
        return answer;
    }
}
