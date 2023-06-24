package Baekjoon;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Map;
import java.util.TreeMap;

public class BOJ_4358 {

    public static void main(String[] args) throws IOException {
        BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(System.in));
        Map<String, Integer> treeSpecies = new TreeMap<>();
        int totalTree = 0;
        while (true) {
            String species = bufferedReader.readLine();
            if (species == null) {
                break;
            }
            treeSpecies.put(species, treeSpecies.getOrDefault(species, 0) + 1);
            totalTree++;
        }
        StringBuilder answer = new StringBuilder();
        for (Map.Entry<String, Integer> species : treeSpecies.entrySet()) {
            answer.append(String.format("%s %.4f\n", species.getKey(), (double) species.getValue() / totalTree * 100));
        }
        System.out.println(answer);
    }
}
