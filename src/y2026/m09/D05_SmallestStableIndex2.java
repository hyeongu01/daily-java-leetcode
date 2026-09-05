package y2026.m09;

import utils.TestCase;
import utils.TestRunner;

class D05_SmallestStableIndex2 {

    public static void main(String[] args) {
        D05_SmallestStableIndex2 solution = new D05_SmallestStableIndex2();

        TestRunner.run("Smallest Stable Index II",
                input -> solution.firstStableIndex((int[]) input[0], (int) input[1]),
                new TestCase<>(new Object[]{new int[]{5, 0, 1, 4}, 3}, 3),
                new TestCase<>(new Object[]{new int[]{3, 2, 1}, 1}, -1),
                new TestCase<>(new Object[]{new int[]{0}, 0}, 0)
        );
    }

    // 시간복잡도: O(n)
    public int firstStableIndex(int[] nums, int k) {
        int[] maxArr = new int[nums.length];
        int[] minArr = new int[nums.length];

        int currentMax = 0;
        for (int i = 0; i < nums.length; i++) {
            currentMax = Math.max(currentMax, nums[i]);
            maxArr[i] = currentMax;
        }
        int currentMin = Integer.MAX_VALUE;
        for (int i = nums.length - 1; i >= 0; i--) {
            currentMin = Math.min(currentMin, nums[i]);
            minArr[i] = currentMin;
        }

        for (int i = 0; i < nums.length; i++) {
            if (isStable(k, maxArr[i], minArr[i])) return i;
        }
        return -1;
    }

    private boolean isStable(int k, int max, int min) {
        return k >= (max - min);
    }
}
