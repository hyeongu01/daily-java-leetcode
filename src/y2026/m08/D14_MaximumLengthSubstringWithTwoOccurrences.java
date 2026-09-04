package y2026.m08;

import utils.TestCase;
import utils.TestRunner;

class D14_MaximumLengthSubstringWithTwoOccurrences {

    public static void main(String[] args) {
        D14_MaximumLengthSubstringWithTwoOccurrences solution = new D14_MaximumLengthSubstringWithTwoOccurrences();

        TestRunner.run("Maximum Length Substring With Two Occurrences",
                solution::maximumLengthSubstring,
                new TestCase<>("bcbbbcba", 4),
                new TestCase<>("aaaa", 2)
        );
    }

    /**
     * "연속된" 문자열의 조건이 있는 것을 보니 two pointer 를 사용하여 탐색하면 좋을듯!
     *
     * 자료구조: array
     * 알고리즘: two pointer
     *
     * 시간복잡도: O(n)
     * 공간복잡도: O(1)
     */
    public int maximumLengthSubstring(String s) {
        int[] letterCount = new int[128];
        int startIdx = 0;
        int max = 0;

        for (int i = 0; i < s.length(); i ++) {
            int targetIdx = (int) s.charAt(i);
            letterCount[targetIdx] += 1;
            while (letterCount[targetIdx] > 2) {
                letterCount[(int) s.charAt(startIdx)] -= 1;
                startIdx ++;
            }
            max = Math.max(max, i - startIdx + 1);
        }
        return max;
    }
}
