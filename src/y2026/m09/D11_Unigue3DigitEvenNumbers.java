package y2026.m09;

import utils.TestCase;
import utils.TestRunner;

import java.util.HashSet;
import java.util.Set;

/**
 * https://leetcode.com/problems/unique-3-digit-even-numbers
 *
 * 접근 방식
 * - Set 자료구조를 활용한 중복되지 않는 숫자의 개수 연산
 */
class D11_Unigue3DigitEvenNumbers {

    public static void main(String[] args) {
        D11_Unigue3DigitEvenNumbers solution = new D11_Unigue3DigitEvenNumbers();

        TestRunner.run("Unique 3-Digit Even Numbers",
                solution::totalNumbers,
                new TestCase<>(new int[]{1, 2, 3, 4}, 12),
                new TestCase<>(new int[]{0, 2, 2}, 2),
                new TestCase<>(new int[]{6, 6, 6}, 1),
                new TestCase<>(new int[]{1, 3, 5}, 0)
        );
    }

    // 시간복잡도: O(n^3)
    public int totalNumbers(int[] digits) {
        if (digits.length < 3)
            return 0;
        Set<Integer> hashSet = new HashSet<>();

        for (int i = 0; i < digits.length; i++) {
            if (digits[i] % 2 == 1)
                continue;

            for (int j = 0; j < digits.length; j++) {
                if (j == i)
                    continue;

                for (int k = 0; k < digits.length; k++) {
                    if (k == i || k == j || digits[k] == 0)
                        continue;
                    int num = digits[i] + digits[j] * 10 + digits[k] * 100;
                    hashSet.add(num);
                }
            }
        }
        return hashSet.size();
    }
}
