package y2026.m08;

import utils.TestCase;
import utils.TestRunner;

public class D18_FindTheLargestAlmostMissingInteger {

    public static void main(String[] args) {
        D18_FindTheLargestAlmostMissingInteger solution = new D18_FindTheLargestAlmostMissingInteger();

        TestRunner.run("Find the Largest Almost Missing Integer",
                input -> solution.largestInteger((int[]) input[0], (int) input[1]),
                new TestCase<>(new Object[]{new int[]{3, 9, 2, 1, 7}, 3}, 7),
                new TestCase<>(new Object[]{new int[]{3, 9, 7, 2, 1, 7}, 4}, 3),
                new TestCase<>(new Object[]{new int[]{0, 0}, 1}, -1)
        );
    }

    public int largestInteger(int[] nums, int k) {
        int[] counter = new int[51];
        int[] subArrayCounter = new int[51];

        // 초기값
        for (int i = 0; i < k; i++) {
            counter[nums[i]] += 1;
        }
        for (int i = 0; i < counter.length; i++) {
            if (counter[i] >= 1) {
                subArrayCounter[i] += 1;
            }
        }

        for (int i = 1; i <= nums.length - k; i++) {
            counter[nums[i - 1]] -= 1;
            counter[nums[i + k - 1]] += 1;


            for (int j = 0; j < counter.length; j++) {
                if (counter[j] >= 1) {
                    subArrayCounter[j] += 1;
                }
            }
        }

        int max = -1;
        for (int i = 0; i < subArrayCounter.length; i++) {
            if (subArrayCounter[i] == 1) {
                max = i;
            }
        }
        return max;
    }
}
