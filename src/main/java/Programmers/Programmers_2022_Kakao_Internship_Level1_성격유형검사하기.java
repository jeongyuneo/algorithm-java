package Programmers;

public class Programmers_2022_Kakao_Internship_Level1_성격유형검사하기 {

    public static void main(String[] args) {
        System.out.println(solution(new String[]{"AN", "CF", "MJ", "RT", "NA"}, new int[]{5, 3, 2, 7, 5}));
        System.out.println(solution(new String[]{"TR", "RT", "TR"}, new int[]{7, 1, 3}));
    }

    private static final char[][] TYPE_OF_CHARACTERS = {{'R', 'T'}, {'C', 'F'}, {'J', 'M'}, {'A', 'N'}};
    private static final int INDEX = 4;
    private static final int TYPE_OF_CHARACTER_NUMBER = 2;
    private static final int DO_NOT_KNOW = 4;

    public static String solution(String[] survey, int[] choices) {
        int[][] scores = new int[INDEX][TYPE_OF_CHARACTER_NUMBER];
        for (int i = 0, questionNumber = survey.length; i < questionNumber; i++) {
            int choice = choices[i];
            char chosenType = survey[i].charAt(0);
            if (choice == DO_NOT_KNOW) {
                continue;
            } else if (choice > DO_NOT_KNOW) {
                chosenType = survey[i].charAt(1);
            }
            int score = Math.abs(DO_NOT_KNOW - choice);
            updateScore(scores, chosenType, score);
        }

        StringBuilder answer = new StringBuilder();
        for (int i = 0; i < INDEX; i++) {
            char firstType = TYPE_OF_CHARACTERS[i][0];
            char secondType = TYPE_OF_CHARACTERS[i][1];
            if (scores[i][0] < scores[i][1]) {
                answer.append(secondType);
            } else if (scores[i][0] > scores[i][1]) {
                answer.append(firstType);
            } else {
                if (firstType < secondType) {
                    answer.append(firstType);
                } else {
                    answer.append(secondType);
                }
            }
        }
        return answer.toString();
    }

    private static void updateScore(int[][] scores, char chosenType, int choice) {
        for (int i = 0; i < INDEX; i++) {
            for (int j = 0; j < TYPE_OF_CHARACTER_NUMBER; j++) {
                if (TYPE_OF_CHARACTERS[i][j] == chosenType) {
                    scores[i][j] += choice;
                    return;
                }
            }
        }
    }
}
