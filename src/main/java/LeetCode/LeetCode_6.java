package LeetCode;

public class LeetCode_6 {

    public static void main(String[] args) {
        System.out.println(convert("PAYPALISHIRING", 3));
        System.out.println(convert("PAYPALISHIRING", 4));
    }

    public static String convert(String s, int numRows) {
        StringBuilder[] rows = new StringBuilder[numRows];
        for (int i = 0; i < rows.length; i++) {
            rows[i] = new StringBuilder();
        }
        int current = 0;
        while (current < s.length()) {
            for (int row = 0; row < numRows && current < s.length(); row++) {
                rows[row].append(s.charAt(current++));
            }

            for (int row = numRows - 2; row >= 1 && current < s.length(); row--) {
                rows[row].append(s.charAt(current++));
            }
        }
        StringBuilder result = new StringBuilder();
        for (StringBuilder character : rows) {
            result.append(character);
        }
        return result.toString();
    }
}
