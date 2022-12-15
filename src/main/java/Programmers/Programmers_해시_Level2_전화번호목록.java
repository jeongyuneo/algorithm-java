package Programmers;

import java.util.Collections;
import java.util.HashSet;
import java.util.Set;

public class Programmers_해시_Level2_전화번호목록 {

    public static void main(String[] args) {
        System.out.println(solution(new String[]{"119", "97674223", "1195524421"}));
        System.out.println(solution(new String[]{"123", "456", "789"}));
        System.out.println(solution(new String[]{"12", "123", "1235", "567", "88"}));
    }

    public static boolean solution(String[] phoneBook) {
        Set<String> phones = new HashSet<>();
        Collections.addAll(phones, phoneBook);
        for (String phone : phoneBook) {
            for (int i = 0; i < phone.length(); i++) {
                if (phones.contains(phone.substring(0, i))) {
                    return false;
                }
            }
        }
        return true;
    }
}
