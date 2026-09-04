package y2026.m08;

import utils.TestCase;
import utils.TestRunner;

import java.util.Arrays;

class D18_MoveZeroes {

    public static void main(String[] args) {
        D18_MoveZeroes solution = new D18_MoveZeroes();

        TestRunner.run("Move Zeroes", solution::solve,
                new TestCase<>(new int[]{0, 1, 0, 3, 12}, new int[]{1, 3, 12, 0, 0}),
                new TestCase<>(new int[]{0}, new int[]{0}),
                new TestCase<>(new int[]{1, 0, 1}, new int[]{1, 1, 0}),
                new TestCase<>(new int[]{1, 2, 3}, new int[]{1, 2, 3}),
                new TestCase<>(new int[]{0, 0, 0}, new int[]{0, 0, 0})
        );
    }

    /** moveZeroes 는 입력을 직접 수정하므로 복사본에 적용해 반환한다. */
    private int[] solve(int[] nums) {
        int[] result = Arrays.copyOf(nums, nums.length);
        moveZeroes(result);
        return result;
    }

    public void moveZeroes(int[] nums) {
        int firstZeroIndex = findNextZero(nums, 0);

        for (int i = firstZeroIndex + 1; i < nums.length && firstZeroIndex != -1; i++) {
            if (nums[i] != 0) {
                nums[firstZeroIndex] = nums[i];
                nums[i] = 0;

                firstZeroIndex = findNextZero(nums, firstZeroIndex);
            }
        }
    }

    private int findNextZero(int[] nums, int start) {
        for (int i = start; i < nums.length; i++) {
            if (nums[i] == 0) {
                return i;
            }
        }
        return -1;
    }
}
