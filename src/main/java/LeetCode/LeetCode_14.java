package LeetCode;

import java.util.Arrays;

public class LeetCode_14 {

    public static void main(String[] args) {
        System.out.println(longestCommonPrefix(new String[]{"flower", "flow", "flight"}));
        System.out.println(longestCommonPrefix(new String[]{"dog", "racecar", "car"}));
        System.out.println(longestCommonPrefix(new String[]{"cir", "car"}));
    }

    public static String longestCommonPrefix(String[] strs) {
        Arrays.sort(strs);
        String first = strs[0];
        String last = strs[strs.length - 1];
        int idx = 0;
        for (int i = 0; i < first.length() && i < last.length(); i++) {
            if (first.charAt(i) == last.charAt(i)) {
                idx++;
                continue;
            }
            break;
        }
        return first.substring(0, idx);
    }
}
