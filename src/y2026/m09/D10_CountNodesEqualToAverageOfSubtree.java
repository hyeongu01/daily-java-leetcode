package y2026.m09;


import utils.TestCase;
import utils.TestRunner;

import java.util.ArrayList;
import java.util.List;

/**
 * https://leetcode.com/problems/count-nodes-equal-to-average-of-subtree
 *
 * 접근 방식: 재귀함수
 */
public class D10_CountNodesEqualToAverageOfSubtree {

    public static void main(String[] args) {
        Solution solution = new Solution();

        TreeNode testInput1 = new TreeNode(
                4,
                new TreeNode(
                        8,
                        new TreeNode(0),
                        new TreeNode(1)
                ),
                new TreeNode(
                        5,
                        null,
                        new TreeNode(6)
                )
        );

        TreeNode testInput2 = new TreeNode(1, null, null);

        TestRunner.run("Count Nodes Equal to Average of Subtree",
                solution::averageOfSubtree,
                new TestCase<>(testInput1, 5),
                new TestCase<>(testInput2, 5)
        );
    }

    private static class TreeNode {
        int val;
        TreeNode left;
        TreeNode right;

        TreeNode() {
        }

        TreeNode(int val) {
            this.val = val;
        }

        TreeNode(int val, TreeNode left, TreeNode right) {
            this.val = val;
            this.left = left;
            this.right = right;
        }
    }

    private static class Solution {
        List<Integer> sums;
        List<Integer> values;
        List<Integer> counts;

        Solution() {
            this.sums = new ArrayList<>();
            this.values = new ArrayList<>();
            this.counts = new ArrayList<>();
        }

        public int averageOfSubtree(TreeNode root) {
            calcSubTreeSum(root);
            calcCountSum(root);

            int result = 0;
            for (int i = 0; i < values.size(); i++) {
                int avg = sums.get(i) / counts.get(i);
                if (values.get(i) == avg)
                    result++;
            }
            return result;
        }

        private int calcSubTreeSum(TreeNode root) {
            int left = root.left == null ? 0 : calcSubTreeSum(root.left);
            int right = root.right == null ? 0 : calcSubTreeSum(root.right);
            int sum = left + right + root.val;
            sums.add(sum);
            values.add(root.val);
            return sum;
        }

        private int calcCountSum(TreeNode root) {
            int left = root.left == null ? 0 : calcCountSum(root.left);
            int right = root.right == null ? 0 : calcCountSum(root.right);
            int count = left + right + 1;
            counts.add(count);
            return count;
        }
    }


}
