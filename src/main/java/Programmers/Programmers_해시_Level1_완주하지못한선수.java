package Programmers;

import java.util.HashMap;
import java.util.Map;

public class Programmers_해시_Level1_완주하지못한선수 {

    public static void main(String[] args) {
        System.out.println(solution(new String[]{"leo", "kiki", "eden"}, new String[]{"eden", "kiki"}));
        System.out.println(solution(new String[]{"marina", "josipa", "nikola", "vinko", "filipa"}, new String[]{"josipa", "filipa", "marina", "nikola"}));
        System.out.println(solution(new String[]{"mislav", "stanko", "mislav", "ana"}, new String[]{"stanko", "ana", "mislav"}));
    }

    public static String solution(String[] participant, String[] completion) {
        Map<String, Integer> members = new HashMap<>();
        for (String member : participant) {
            if (members.containsKey(member)) {
                members.put(member, members.get(member) + 1);
            } else {
                members.put(member, 1);
            }
        }

        for (String completionMember : completion) {
            int people = members.get(completionMember);
            if (people > 1) {
                members.put(completionMember, people - 1);
            } else {
                members.remove(completionMember);
            }
        }

        return members.keySet()
                .stream()
                .findFirst()
                .get();
    }
}
