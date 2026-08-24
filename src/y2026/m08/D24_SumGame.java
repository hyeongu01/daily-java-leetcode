package y2026.m08;

import utils.TestCase;
import utils.TestRunner;

class D24_SumGame {

    public static void main(String[] args) {
        D24_SumGame solution = new D24_SumGame();

        TestRunner.run("Sum Game",
                solution::sumGame,
                new TestCase<>("5023", false),
                new TestCase<>("25??", true),
                new TestCase<>("?3295???", false)
        );
    }

    public boolean sumGame(String num) {
        int leftSum = 0, rightSum = 0, leftQMCount = 0, rightQMCount = 0;

        for (int i = 0; i < num.length(); i++) {
            char c = num.charAt(i);

            if (c == '?') {
                leftQMCount += i < (num.length() / 2) ? 1 : 0;
                rightQMCount += i < (num.length() / 2) ? 0 : 1;
            } else {
                int n = (int) (c - '0');

                leftSum += i < (num.length() / 2) ? n : 0;
                rightSum += i < (num.length() / 2) ? 0 : n;
            }
        }

        // ? 개수가 많은 쪽이 targetCount, 그 반대쪽이 targetSum
        // 같다면 즉시 값 비교 후 return
        // 오른쪽 ? 가 많다고 계산 후 그렇지 않다면 -1 곱함
        int targetCount = rightQMCount - leftQMCount;
        int targetSum = leftSum - rightSum;

        if (targetCount == 0) {
            return targetSum != 0;
        }

        if (targetCount < 0) {
            targetCount *= -1;
            targetSum *= -1;
        }

        // 무조건 Alice 먼저임
        if (targetCount % 2 == 1) {
            return true;
        } else {
            int boundLine = 9 * (targetCount / 2);
            return targetSum != boundLine;
        }
    }
}
