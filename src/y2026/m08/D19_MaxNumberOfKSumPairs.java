package y2026.m08;

import utils.TestCase;
import utils.TestRunner;

import java.util.Arrays;

public class D19_MaxNumberOfKSumPairs {

    public static void main(String[] args) {
        D19_MaxNumberOfKSumPairs solution = new D19_MaxNumberOfKSumPairs();

        TestRunner.run("Max Number of K-Sum Pairs",
                input -> solution.maxOperations((int[]) input[0], (int) input[1]),
                new TestCase<>(new Object[]{new int[]{1, 2, 3, 4}, 5}, 2),
                new TestCase<>(new Object[]{new int[]{3, 1, 3, 4, 3}, 6}, 1)
        );
    }

    public int maxOperations(int[] nums, int k) {
        int left = 0, right = nums.length - 1;
        int count = 0;

        Arrays.sort(nums);
        while (left < right) {
            int sum = nums[left] + nums[right];
            if (sum > k) {
                right--;
            } else if (sum < k) {
                left++;
            } else {
                count++;
                left++;
                right--;
            }
        }
        return count;
    }
}
