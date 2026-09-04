package y2026.m09;

import utils.TestCase;
import utils.TestRunner;

/**
 * link: https://leetcode.com/problems/smallest-stable-index-i
 */
class D04_SmallestStableIndex1 {

    public static void main(String[] args) {
        D04_SmallestStableIndex1 solution = new D04_SmallestStableIndex1();

        TestRunner.run("Smallest Stable Index I",
                input -> solution.firstStableIndex((int[]) input[0], (int) input[1]),
                new TestCase<>(new Object[]{new int[]{5, 0, 1, 4}, 3}, 3),
                new TestCase<>(new Object[]{new int[]{3, 2, 1}, 1}, -1),
                new TestCase<>(new Object[]{new int[]{0}, 0}, 0)
        );
    }

    public int firstStableIndex(int[] nums, int k) {
        int max = nums[0];
        int min = nums[0];
        for (int n : nums) {
            min = Math.min(min, n);
        }
        // 0번째 먼저 체크
        if (isStable(k, max, min))
            return 0;

        // 1번부터 끝까지 순회. O(n^2)
        for (int i = 1; i < nums.length; i++) {
            // max, min 다시 계산
            max = Math.max(max, nums[i]);
            if (nums[i - 1] == min) {
                min = nums[i];
                for (int j = i; j < nums.length; j++) {
                    min = Math.min(min, nums[j]);
                }
            }
            if (isStable(k, max, min))
                return i;
        }
        return -1;
    }

    private boolean isStable(int k, int max, int min) {
        return max - min <= k;
    }
}
