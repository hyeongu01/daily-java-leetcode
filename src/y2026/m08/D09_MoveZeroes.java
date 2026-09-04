package y2026.m08;

import utils.TestCase;
import utils.TestRunner;

import java.util.Arrays;

/**
 * link: https://leetcode.com/problems/move-zeroes/
 */
class D09_MoveZeroes {

    public static void main(String[] args) {
        TestRunner.run("Move Zeroes", D09_MoveZeroes::solve,
                new TestCase<>(new int[]{0, 1, 0, 3, 12}, new int[]{1, 3, 12, 0, 0}),
                new TestCase<>(new int[]{0}, new int[]{0}),
                new TestCase<>(new int[]{1, 0, 1}, new int[]{1, 1, 0})
        );
    }

    /** moveZeroes 는 입력을 직접 수정하므로 복사본에 적용해 반환한다. */
    static int[] solve(int[] nums) {
        int[] result = Arrays.copyOf(nums, nums.length);
        moveZeroes(result);
        return result;
    }

    public static void moveZeroes(int[] nums) {
        int firstZeroPointer = 0;

        // 최초 0 찾기
        while (firstZeroPointer < nums.length && nums[firstZeroPointer] != 0) {
            firstZeroPointer ++;
        }

        for (int i = firstZeroPointer + 1; i < nums.length; i++) {
            if (nums[i] != 0) {
                // nums[firstZeroPointer] 의 값은 항상 0임
                nums[firstZeroPointer++] = nums[i];
                nums[i] = 0;
            }
        }
    }
}
