package Baekjoon;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class BOJ_5883 {

    public static void main(String[] args) throws IOException {
        BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(System.in));
        int n = Integer.parseInt(bufferedReader.readLine());
        int[] customers = new int[n];
        Set<Integer> volumes = new HashSet<>();
        for (int i = 0; i < n; i++) {
            int input = Integer.parseInt(bufferedReader.readLine());
            customers[i] = input;
            volumes.add(input);
        }
        List<Integer> copyOfCustomers = new ArrayList<>();
        int maxLength = 1;
        for (int volume : volumes) {
            for (int customer : customers) {
                if (customer != volume) {
                    copyOfCustomers.add(customer);
                }
            }
            int length = 1;
            for (int i = 1; i < copyOfCustomers.size(); i++) {
                if (Objects.equals(copyOfCustomers.get(i - 1), copyOfCustomers.get(i))) {
                    length++;
                    maxLength = Math.max(maxLength, length);
                } else {
                    length = 1;
                }
            }
            copyOfCustomers.clear();
        }
        System.out.println(maxLength);
    }
}
