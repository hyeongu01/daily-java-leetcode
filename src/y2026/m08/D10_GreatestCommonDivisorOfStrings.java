package y2026.m08;

import utils.TestCase;
import utils.TestRunner;

/**
 * link: https://leetcode.com/problems/greatest-common-divisor-of-strings/
 */
class D10_GreatestCommonDivisorOfStrings {

    public static void main(String[] args) {
        D10_GreatestCommonDivisorOfStrings solution = new D10_GreatestCommonDivisorOfStrings();

        // 인자가 여러 개면 배열로 묶어서 전달한다.
        TestRunner.run("Greatest Common Divisor of Strings",
            input -> solution.gcdOfStrings(input[0], input[1]),
            new TestCase<>(new String[]{"ABCABC", "ABC"}, "ABC"),
            new TestCase<>(new String[]{"ABABAB", "ABAB"}, "AB"),
            new TestCase<>(new String[]{"LEET", "CODE"}, ""),
            new TestCase<>(new String[]{"ABABAB", "AB"}, "AB")
        );
    }

    public String gcdOfStrings(String str1, String str2) {
        int n = gcd(str1.length(), str2.length());

        String result = str1.substring(0, n);

        return canDivideBoth(str1, str2, result) ? result : "";
    }

    boolean canDivideBoth(String str1, String str2, String t) {
        boolean canDivideStr1 = true;
        boolean canDivideStr2 = true;
        int tLength = t.length();

        if (str1.length() % tLength == 0) {
            for (int i = 0; i < str1.length(); i++) {
                if (str1.charAt(i) != t.charAt(i % tLength)) {
                    canDivideStr1 = false;
                    break;
                }
            }
        } else {
            canDivideStr1 = false;
        }

        if (str2.length() % tLength == 0) {
            for (int i = 0; i < str2.length(); i++) {
                if (str2.charAt(i) != t.charAt(i % tLength)) {
                    canDivideStr2 = false;
                    break;
                }
            }
        } else {
            canDivideStr2 = false;
        }
        return canDivideStr1 && canDivideStr2;
    }

    int gcd(int a, int b) {
        return b == 0 ? a : gcd(b, a % b);
    }
}
