package Baekjoon;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.Deque;

public class BOJ_12904 {

    public static void main(String[] args) throws IOException {
        BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(System.in));
        String s = bufferedReader.readLine();
        String t = bufferedReader.readLine();
        Deque<Character> deque = new ArrayDeque<>();
        for (int i = 0; i < t.length(); i++) {
            deque.offer(t.charAt(i));
        }
        int sLength = s.length();
        boolean isLast = true;
        while (deque.size() != sLength) {
            char lastCharacter;
            if (isLast) {
                lastCharacter = deque.removeLast();
            } else {
                lastCharacter = deque.removeFirst();
            }

            if (lastCharacter == 'B') {
                isLast = !isLast;
            }
        }

        StringBuilder stringBuilder = new StringBuilder();

        if (isLast) {
            while (deque.size() > 0) {
                stringBuilder.append(deque.removeFirst());
            }
        } else {
            while (deque.size() > 0) {
                stringBuilder.append(deque.removeLast());
            }
        }

        if (stringBuilder.toString().equals(s)) {
            System.out.println(1);
        } else {
            System.out.println(0);
        }
    }
}
