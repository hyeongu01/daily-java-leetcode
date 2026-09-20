package y2026.m09;

import utils.TestRunner;
import utils.TestCase;

/**
 * https://leetcode.com/problems/max-consecutive-ones-iii
 */
public class D20_MaxConsecutiveOnes3 {

    public static void main(String[] args) {
        Solution solution = new Solution();

        TestRunner.run("Max Consecutive Ones III - 1",
                input -> solution.longestOnes((int[]) input[0], (int) input[1]),
                new TestCase<>(new Object[]{new int[]{1, 1, 1, 0, 0, 0, 1, 1, 1, 1, 0}, 2}, 6),
                new TestCase<>(new Object[]{new int[]{0, 0, 1, 1, 0, 0, 1, 1, 1, 0, 1, 1, 0, 0, 0, 1, 1, 1, 1}, 3}, 10)
        );

        Solution2 solution2 = new Solution2();

        TestRunner.run("Max Consecutive Ones III - 2",
                input -> solution2.longestOnes((int[]) input[0], (int) input[1]),
                new TestCase<>(new Object[]{new int[]{1, 1, 1, 0, 0, 0, 1, 1, 1, 1, 0}, 2}, 6),
                new TestCase<>(new Object[]{new int[]{0, 0, 1, 1, 0, 0, 1, 1, 1, 0, 1, 1, 0, 0, 0, 1, 1, 1, 1}, 3}, 10)
        );
    }

    /**
     * 이진 배열 nums 와 숫자 k 가 주어진다.
     * <p>
     * 최대 k 개의 0을 뒤집을 수 있다면 연속된 1의 개수의 최대값을 구하시오.
     * <p>
     * <p>
     * **코드 분석**
     * - 시간복잡도: O(n)
     * - 공간복잡도: O(1)
     * - 사용 알고리즘: 슬라이딩 윈도우 (left, right 포인터를 사용한 탐색)
     */
    static class Solution {
        public int longestOnes(int[] nums, int k) {
            int left = 0, right = -1;
            int zeroCount = 0;

            do {
                right ++;
                if (nums[right] == 0) {
                    if (zeroCount == k) {
                        right --;
                        break;
                    }
                    zeroCount ++;
                }
            } while (right < nums.length - 1);
            int max = right - left + 1;

            // 한 칸씩 이동하며 가능한 경우를 탐색
            while (++ right < nums.length) {
                if (nums[right] == 0) {
                    // left 에서 다음 0이 나올때까지 이동
                    while (nums[left ++] != 0) {
                        continue;
                    }
                }
                max = Math.max(max, right - left + 1);
            }
            return max;
        }
    }

    /**
     * 두 번쨰 풀이
     */
    static class Solution2 {
        public int longestOnes(int[] nums, int k) {
            int left = 0, right = 0, zeroCount = 0;
            int max = 0;

            while (right < nums.length) {
                if (nums[right] == 0) {
                    zeroCount++;
                }
                while (zeroCount > k) {
                    if (nums[left++] == 0)
                        zeroCount--;
                }
                max = Math.max(max, right - left + 1);
                right++;
            }
            return max;
        }
    }
}
