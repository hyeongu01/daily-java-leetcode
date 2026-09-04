package y2026.m08;

import utils.TestCase;
import utils.TestRunner;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

class D20_MaximumNumberOfVowelsInASubstringOfGivenLength {

    public static void main(String[] args) {
        D20_MaximumNumberOfVowelsInASubstringOfGivenLength solution = new D20_MaximumNumberOfVowelsInASubstringOfGivenLength();

        TestRunner.run("Maximum Number of Vowels in a Substring of Given Length",
                input -> solution.maxVowels((String) input[0], (int) input[1]),
                new TestCase<>(new Object[]{"abciiidef", 3}, 3),
                new TestCase<>(new Object[]{"aeiou", 2}, 2),
                new TestCase<>(new Object[]{"leetcode", 3}, 2)
        );
    }

    public int maxVowels(String s, int k) {
        int counter = 0;
        Set<Character> vowels = new HashSet<>(List.of('a', 'i', 'u', 'e', 'o'));

        // 초기값
        for (int i = 0; i < k; i++) {
            if (vowels.contains(s.charAt(i))) {
                counter += 1;
            }
        }

        int max = counter;
        for (int i = 1; i <= s.length() - k; i++) {
            if (vowels.contains(s.charAt(i - 1))) {
                counter -= 1;
            }
            if (vowels.contains(s.charAt(i + k - 1))) {
                counter += 1;
            }
            max = Math.max(max, counter);
        }
        return max;
    }
}
