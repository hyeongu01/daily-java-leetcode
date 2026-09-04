package y2026.m08;

import utils.TestCase;
import utils.TestRunner;

class D20_ProductOfArrayExceptSelf {

    public static void main(String[] args) {
        D20_ProductOfArrayExceptSelf solution = new D20_ProductOfArrayExceptSelf();

        TestRunner.run("Product of Array Except Self",
                solution::productExceptSelf,
                new TestCase<>(new int[]{1, 2, 3, 4}, new int[]{24, 12, 8, 6}),
                new TestCase<>(new int[]{-1, 1, 0, -3, 3}, new int[]{0, 0, 9, 0, 0}),
                new TestCase<>(new int[]{-1, 0, 0, -3, 3}, new int[]{0, 0, 0, 0, 0})
        );
    }

    public int[] productExceptSelf(int[] nums) {
        int totalProduct = 1;
        int zeroCount = 0;
        for (int n : nums) {
            if (n == 0) {
                zeroCount += 1;
                continue;
            }
            totalProduct *= n;
        }

        int[] result = new int[nums.length];
        if (zeroCount == 0) {
            for (int i = 0; i < nums.length; i++) {
                result[i] = totalProduct / nums[i];
            }
        } else if (zeroCount == 1) {
            for (int i = 0; i < nums.length; i++) {
                result[i] = nums[i] == 0 ? totalProduct : 0;
            }
        }
        return result;
    }
}
