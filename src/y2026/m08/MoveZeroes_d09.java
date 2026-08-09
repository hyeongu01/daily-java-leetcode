package y2026.m08;

import utils.TestCase;
import java.util.Arrays;
import java.util.Objects;

/**
 * link: https://leetcode.com/problems/move-zeroes/
 */
public class MoveZeroes_d09 {

    @SuppressWarnings("unchecked")
    public static void main(String[] args) {
        // 제네릭 배열은 new TestCase<>[3] 으로 만들 수 없어서 raw 타입으로 생성한다.
        TestCase<int[], int[]>[] testCases = new TestCase[]{
            new TestCase<>(new int[]{0, 1, 0, 3, 12}, new int[]{1, 3, 12, 0, 0}),
            new TestCase<>(new int[]{0}, new int[]{0}),
            new TestCase<>(new int[]{1, 0, 1}, new int[]{1, 1, 0}),
        };

        for (TestCase<int[], int[]> tc : testCases) {
            int[] result = Arrays.copyOf(tc.input, tc.input.length); // 입력 출력용으로 원본 보존
            moveZeroes(result);

            System.out.print(Objects.deepEquals(result, tc.output) ? " | 정답 | " : " | 오답 | ");
            System.out.println("input = " + Arrays.toString(tc.input)
                    + " result = " + Arrays.toString(result)
                    + " correct = " + Arrays.toString(tc.output));
        }
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
