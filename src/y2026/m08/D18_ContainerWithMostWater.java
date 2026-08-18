package y2026.m08;

import utils.TestCase;
import utils.TestRunner;

public class D18_ContainerWithMostWater {

    public static void main(String[] args) {
        D18_ContainerWithMostWater solution = new D18_ContainerWithMostWater();

        TestRunner.run("Container With Most Water",
                solution::maxArea,
                new TestCase<int[], Integer>(new int[]{1, 8, 6, 2, 5, 4, 8, 3, 7}, 49),
                new TestCase<int[], Integer>(new int[]{1, 1}, 1),
                new TestCase<int[], Integer>(new int[]{2, 3, 4, 5, 18, 17, 6}, 17),
                new TestCase<int[], Integer>(new int[]{5, 4, 3, 2, 1}, 6)
        );
    }

//    public int maxArea(int[] height) {
//        int[] left = new int[height.length];
//        int[] right = new int[height.length];
//
//        for (int i = 0; i < height.length; i++) {
//            int idx = 0;
//            for (; idx <= i; idx++) {
//                if (height[idx] >= height[i]) {
//                    break;
//                }
//            }
//            left[i] = idx;
//        }
//
//        for (int i = 0; i < height.length; i++) {
//            int idx = height.length - 1;
//            for (; idx >= i; idx--) {
//                if (height[idx] >= height[i]) {
//                    break;
//                }
//            }
//            right[i] = idx;
//        }
//
//        int max = 0;
//        for (int i = 0; i < height.length; i++) {
//            max = Math.max(max, height[i] * (right[i] - left[i]));
//        }
//        return max;
//    }

    public int maxArea(int[] height) {
        int max = 0;
        int left = 0, right = height.length - 1;

        while (left < right) {
            max = Math.max(max, (right - left) * Math.min(height[left], height[right]));
            if (height[left] < height[right]) {
                left++;
            } else {
                right--;
            }
        }

        return max;
    }
}
