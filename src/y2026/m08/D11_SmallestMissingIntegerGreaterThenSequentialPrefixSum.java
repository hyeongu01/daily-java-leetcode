package y2026.m08;

import utils.TestCase;
import utils.TestRunner;

import java.util.HashSet;

public class D11_SmallestMissingIntegerGreaterThenSequentialPrefixSum {

    public static void main(String[] args) {
        D11_SmallestMissingIntegerGreaterThenSequentialPrefixSum solution = new D11_SmallestMissingIntegerGreaterThenSequentialPrefixSum();

        TestRunner.run("Smallest Missing Integer Greater Than Sequential Prefix Sum", solution::missingInteger,
                new TestCase<>(new int[]{1, 2, 3, 2, 5}, 6),
                new TestCase<>(new int[]{3, 4, 5, 1, 12, 14, 13}, 15)
        );
    }


    public int missingInteger(int[] nums) {
        int sum = nums[0];

        for (int i = 1; i < nums.length && nums[i - 1] == nums[i] - 1; i++) {
            sum += nums[i];
        }

        HashSet<Integer> set = new HashSet<>();
        for (int n : nums) {
            set.add(n);
        }

        while (set.contains(sum)) {
            sum++;
        }
        return sum;
    }
}
