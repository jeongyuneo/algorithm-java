package Programmers;

import java.util.Arrays;
import java.util.PriorityQueue;

public class Programmers_연습문제_Level2_호텔대실 {

    public static void main(String[] args) {
        System.out.println(solution(new String[][]{{"15:00", "17:00"}, {"16:40", "18:20"}, {"14:20", "15:20"}, {"14:10", "19:20"}, {"18:20", "21:20"}}));
    }

    private static final String DELIMITER = ":";
    private static final String DELETE = "";
    private static final int ENTER = 0;
    private static final int LEAVE = 1;
    private static final int CLEANING_TIME = 10;
    private static final int HOUR_UNIT = 100;
    private static final int ONE_HOUR = 60;

    public static int solution(String[][] book_time) {
        int[][] books = getBooks(book_time);
        Arrays.sort(books, (book1, book2) -> {
            if (book1[ENTER] == book2[ENTER]) {
                return book1[LEAVE] - book2[LEAVE];
            }
            return book1[ENTER] - book2[ENTER];
        });
        PriorityQueue<Integer> rooms = new PriorityQueue<>();
        for (int[] book : books) {
            if (rooms.isEmpty()) {
                rooms.add(book[LEAVE]);
                continue;
            }
            if (hasCleanRoom(book[ENTER], rooms.peek())) {
                rooms.poll();
            }
            rooms.add(book[LEAVE]);
        }
        return rooms.size();
    }

    private static int[][] getBooks(String[][] book_time) {
        int n = book_time.length;
        int[][] books = new int[n][2];
        for (int i = 0; i < n; i++) {
            books[i][ENTER] = toInteger(book_time[i][ENTER]);
            int leave = toInteger(book_time[i][LEAVE]) + CLEANING_TIME;
            if (leave % HOUR_UNIT >= ONE_HOUR) {
                leave += HOUR_UNIT - ONE_HOUR;
            }
            books[i][LEAVE] = leave;
        }
        return books;
    }

    private static int toInteger(String book) {
        return Integer.parseInt(book.replace(DELIMITER, DELETE));
    }

    private static boolean hasCleanRoom(int enter, int leave) {
        return enter >= leave;
    }
}
