package Baekjoon;

import java.util.Scanner;

public class BOJ_17478 {

    private static final StringBuilder STRING_BUILDER = new StringBuilder();

    private static int testCase;

    private static void addUnderBar(int count) {
        for (int i = 0; i < testCase-count; i++) {
            STRING_BUILDER.append("____");
        }
    }

    private static void answer(int count) {
        if (count == 0) {
            addUnderBar(count);
            STRING_BUILDER.append("\"재귀함수가 뭔가요?\"\n");
            addUnderBar(count);
            STRING_BUILDER.append("\"재귀함수는 자기 자신을 호출하는 함수라네\"\n");
            addUnderBar(count);
            STRING_BUILDER.append("라고 답변하였지.\n");
            return;
        }
        addUnderBar(count);
        STRING_BUILDER.append("\"재귀함수가 뭔가요?\"\n");
        addUnderBar(count);
        STRING_BUILDER.append("\"잘 들어보게. 옛날옛날 한 산 꼭대기에 이세상 모든 지식을 통달한 선인이 있었어.\n");
        addUnderBar(count);
        STRING_BUILDER.append("마을 사람들은 모두 그 선인에게 수많은 질문을 했고, 모두 지혜롭게 대답해 주었지.\n");
        addUnderBar(count);
        STRING_BUILDER.append("그의 답은 대부분 옳았다고 하네. 그런데 어느 날, 그 선인에게 한 선비가 찾아와서 물었어.\"\n");
        answer(count-1);
        addUnderBar(count);
        STRING_BUILDER.append("라고 답변하였지.\n");
    }

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        testCase = scanner.nextInt();
        STRING_BUILDER.append("어느 한 컴퓨터공학과 학생이 유명한 교수님을 찾아가 물었다.\n");
        answer(testCase);
        System.out.println(STRING_BUILDER.toString());
    }
}
