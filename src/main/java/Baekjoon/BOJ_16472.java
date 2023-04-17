package Baekjoon;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.HashMap;
import java.util.Map;

public class BOJ_16472 {

    public static void main(String[] args) throws IOException {
        BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(System.in));
        int n = Integer.parseInt(bufferedReader.readLine());
        String input = bufferedReader.readLine();
        int maxLength = 1;
        int start = 0;
        if (input.length() > 1) {
            Map<Character, Integer> map = new HashMap<>();
            map.put(input.charAt(start), start);
            int end = 1;
            while (end < input.length()) {
                char alphabet = input.charAt(end);
                map.put(alphabet, end);
                if (map.size() - 1 >= n) {
                    int removeIndex = input.length();
                    char removeAlphabet = ' ';
                    for (Map.Entry<Character, Integer> entry : map.entrySet()) {
                        if (entry.getValue() < removeIndex) {
                            removeIndex = entry.getValue();
                            removeAlphabet = entry.getKey();
                        }
                    }
                    map.remove(removeAlphabet);
                    start = removeIndex + 1;
                }
                maxLength = Math.max(maxLength, end - start + 1);
                end++;
            }
        }
        System.out.println(maxLength);
    }
}
