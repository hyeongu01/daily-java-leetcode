package y2026.m08;

import utils.TestCase;
import utils.TestRunner;

import java.util.ArrayDeque;
import java.util.Deque;

/**
 * https://leetcode.com/problems/largest-rectangle-in-histogram
 *
 * i 번쨰 행을 기준으로 left 끝, right 끝을 구하여 최대값을 구하는 방식
 * 시간복잡도: O(n)
 * 공간복잡도: O(n)
 *
 * 자료구조: Stack
 * 알고리즘: Monotonic Stack (left, right 구하는 작업에서 활용)
 */
class D14_LargestRectangleInHistogram {

    public static void main(String[] args) {
        D14_LargestRectangleInHistogram solution = new D14_LargestRectangleInHistogram();

        TestRunner.run("Largest Rectangle in Histogram",
                solution::largestRectangleArea,
                new TestCase<>(new int[]{2, 1, 5, 6, 2, 3}, 10),
                new TestCase<>(new int[]{2, 4}, 4)
        );
    }

    public int largestRectangleArea(int[] heights) {
        int[] left = new int[heights.length];
        int[] right = new int[heights.length];
        Deque<Integer> stack = new ArrayDeque<>();

        // left[] 구하기
        for (int i = 0; i < heights.length; i++) {
            while (!stack.isEmpty() && heights[i] <= heights[stack.peek()]) {
                stack.pop();
            }
            left[i] = stack.peek() == null ? 0 : stack.peek() + 1;
            stack.push(i);
        }
        stack.clear();

        // right[] 구하기
        for (int i = heights.length - 1; i >= 0; i--) {
            while (!stack.isEmpty() && heights[i] <= heights[stack.peek()]) {
                stack.pop();
            }
            right[i] = stack.peek() == null ? heights.length - 1 : stack.peek() - 1;
            stack.push(i);
        }

        // max 값 구하기
        int max = 0;
        for (int i = 0; i < heights.length; i++) {
            max = Math.max(max, (right[i] - left[i] + 1) * heights[i]);
        }
        return max;
    }
}
