package Programmers;

import java.util.ArrayDeque;
import java.util.Queue;

public class Programmers_스택큐_Level2_프린터 {

    public static void main(String[] args) {
        System.out.println(solution(new int[]{2, 1, 3, 2}, 2));
        System.out.println(solution(new int[]{1, 1, 9, 1, 1, 1}, 0));
    }

    static class Document implements Comparable<Document> {

        int location;
        int priority;

        public Document(int location, int priority) {
            this.location = location;
            this.priority = priority;
        }

        @Override
        public int compareTo(Document o) {
            return o.priority - priority;
        }
    }

    public static int solution(int[] priorities, int location) {
        Queue<Document> printer = new ArrayDeque<>();
        for (int i = 0; i < priorities.length; i++) {
            int priority = priorities[i];
            printer.offer(new Document(i, priority));
        }
        int order = 1;
        while (!printer.isEmpty()) {
            Document document = printer.poll();
            if (printer.stream().anyMatch(waitingDocument -> waitingDocument.priority > document.priority)) {
                printer.offer(document);
            } else {
                if (document.location == location) {
                    break;
                }
                order++;
            }
        }
        return order;
    }
}
