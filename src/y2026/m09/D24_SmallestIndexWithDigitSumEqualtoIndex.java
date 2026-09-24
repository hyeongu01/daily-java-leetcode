package y2026.m09;

import utils.TestCase;
import utils.TestRunner;

public class D24_SmallestIndexWithDigitSumEqualtoIndex {

    public static void main(String[] args) {
        Solution solution = new Solution();

        TestRunner.run("Smallest Index With Digit Sum Equal to Index",
                solution::smallestIndex,
                new TestCase<>(new int[]{1, 3, 2}, 2),
                new TestCase<>(new int[]{1, 10, 11}, 1),
                new TestCase<>(new int[]{1, 2, 3}, -1)
        );
    }

    static private class Solution {
        public int smallestIndex(int[] nums) {
            int result = -1;
            for (int i = 0; i < nums.length; i++) {
                int sum = nums[i] % 10;
                sum += nums[i] / 10 % 10;
                sum += nums[i] / 100 % 10;
                sum += nums[i] == 1000 ? 1 : 0;
                if (i == sum) {
                    result = i;
                    break;
                }
            }
            return result;
        }
    }
}
