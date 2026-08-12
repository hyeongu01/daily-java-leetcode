package y2026.m08;

import utils.TestCase;
import utils.TestRunner;

import java.util.HashMap;

/**
 * https://leetcode.com/problems/length-of-longest-subarray-with-at-most-k-frequency/
 * HashMap 자료구조, two pointer 알고리즘
 */
public class D12_LengthOfLongestSubarrayWithAtMostKFrequency {

    public static void main(String[] args) {
        D12_LengthOfLongestSubarrayWithAtMostKFrequency solution = new D12_LengthOfLongestSubarrayWithAtMostKFrequency();

        TestRunner.run("Length of Longest Subarray With at Most K Frequency",
                input -> solution.maxSubarrayLength((int[]) input[0], (int) input[1]),
                new TestCase<>(new Object[]{new int[]{1, 2, 3, 1, 2, 3, 1, 2}, 2}, 6),
                new TestCase<>(new Object[]{new int[]{1, 2, 1, 2, 1, 2, 1, 2}, 1}, 2)
        );
    }

    public int maxSubarrayLength(int[] nums, int k) {
        HashMap<Integer, Integer> map = new HashMap<>();
        int result = 0;

        int startIndex = 0;
        for (int i = 0; i < nums.length; i++) {
            int currentValue = nums[i];

            // map 에서 카운트 + 1
            int currentCount = map.get(currentValue) == null ? 0 : map.get(currentValue);
            map.put(currentValue, currentCount + 1);
            currentCount++;

            // 카운트가 k 보다 크면 작아질 때까지 startIndex + 1
            if (currentCount > k) {
                for (; startIndex < i; startIndex++) {
                    map.put(nums[startIndex], map.get(nums[startIndex]) - 1);
                    if (currentValue == nums[startIndex]) {
                        startIndex++;
                        break;
                    }
                }
            }
            result = Math.max(i - startIndex + 1, result);
        }
        return result;

    }
}
