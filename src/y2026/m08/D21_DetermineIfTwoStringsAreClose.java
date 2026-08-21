package y2026.m08;

import utils.TestCase;
import utils.TestRunner;

import java.util.Arrays;

class D21_DetermineIfTwoStringsAreClose {

    public static void main(String[] args) {
        D21_DetermineIfTwoStringsAreClose solution = new D21_DetermineIfTwoStringsAreClose();

        TestRunner.run("Determine if Two Strings Are Close",
                input -> solution.closeStrings((String) input[0], (String) input[1]),
                new TestCase<>(new Object[]{"abc", "bca"}, true),
                new TestCase<>(new Object[]{"a", "aa"}, false),
                new TestCase<>(new Object[]{"cabbba", "abbccc"}, true)
        );
    }

    public boolean closeStrings(String word1, String word2) {
        if (word1.length() != word2.length())
            return false;

        int[] count1 = new int[128];
        int[] count2 = new int[128];

        for (char c : word1.toCharArray()) {
            count1[c] += 1;
        }
        for (char c : word2.toCharArray()) {
            if (count1[c] == 0)
                return false;
            count2[c] += 1;
        }

        Arrays.sort(count1);
        Arrays.sort(count2);

        return Arrays.equals(count1, count2);
    }
}
