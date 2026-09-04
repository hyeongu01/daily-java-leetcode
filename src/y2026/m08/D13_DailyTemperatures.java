package y2026.m08;

import utils.TestCase;
import utils.TestRunner;

import java.util.ArrayDeque;
import java.util.Deque;

/**
 * https://leetcode.com/problems/daily-temperatures
 * <p>
 * 자료구조: Stack, 알고리즘: 단조 스택 (monotonic stack)
 */
class D13_DailyTemperatures {

    public static void main(String[] args) {
        D13_DailyTemperatures solution = new D13_DailyTemperatures();

        TestRunner.run("Daily Temperatures", solution::dailyTemperatures,
                new TestCase<>(new int[]{73, 74, 75, 71, 69, 72, 76, 73}, new int[]{1, 1, 4, 2, 1, 1, 0, 0}),
                new TestCase<>(new int[]{30, 40, 50, 60}, new int[]{1, 1, 1, 0}),
                new TestCase<>(new int[]{30, 60, 90}, new int[]{1, 1, 0})
        );
    }

    public int[] dailyTemperatures(int[] temperatures) {
        int n = temperatures.length;
        int[] result = new int[n];
        Deque<Integer> stack = new ArrayDeque<>();

        for (int i = 0; i < n; i++) {
            while (!stack.isEmpty() && temperatures[i] > temperatures[stack.peek()]) {
                int idx = stack.pop();
                result[idx] = i - idx;
            }
            stack.push(i);
        }
        return result;
    }
}
