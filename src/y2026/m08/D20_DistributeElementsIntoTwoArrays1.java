package y2026.m08;

import utils.TestCase;
import utils.TestRunner;

import java.util.ArrayList;
import java.util.List;

public class D20_DistributeElementsIntoTwoArrays1 {

    public static void main(String[] args) {
        D20_DistributeElementsIntoTwoArrays1 solution = new D20_DistributeElementsIntoTwoArrays1();

        TestRunner.run("Distribute Elements Into Two Arrays I",
                solution::resultArray,
                new TestCase<>(new int[]{2, 1, 3}, new int[]{2, 3, 1}),
                new TestCase<>(new int[]{5, 4, 3, 8}, new int[]{5, 3, 4, 8})
        );
    }

    public int[] resultArray(int[] nums) {
        int[] result = new int[nums.length];
        int top = -1;
        List<Integer> arr2 = new ArrayList<>(nums.length);

        result[++top] = nums[0];
        arr2.add(nums[1]);

        for (int i = 2; i < nums.length; i++) {
            if (result[top] > arr2.getLast()) {
                result[++top] = nums[i];
            } else {
                arr2.add(nums[i]);
            }
        }
        for (Integer n : arr2) {
            if (n == null)
                break;
            result[++top] = n;
        }
        return result;
    }
}
