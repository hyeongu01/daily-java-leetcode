package y2026.m09;

import utils.TestCase;
import utils.TestRunner;

/**
 * https://leetcode.com/problems/maximum-depth-of-binary-tree
 */
public class D22_MaximumDepthofBinaryTree {

    public static void main(String[] args) {
        Solution solution = new Solution();

        TreeNode testInput1 = TreeNode.fromArray(new Integer[]{3, 9, 20, null, null, 15, 7});
        TreeNode testInput2 = TreeNode.fromArray(new Integer[]{1,null,2});

        TestRunner.run("Maximum Depth of Binary Tree",
                solution::maxDepth,
                new TestCase<>(testInput1, 3),
                new TestCase<>(testInput2, 2)
        );
    }


    static class TreeNode {
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

        @Override
        public String toString() {
            StringBuilder sb = new StringBuilder();
            append(sb, "", "", this);
            return sb.toString().stripTrailing();
        }

        private static void append(StringBuilder sb, String prefix, String childPrefix, TreeNode node) {
            sb.append(prefix).append(node == null ? "null" : String.valueOf(node.val)).append('\n');

            // 양쪽 다 비어 있으면 리프 -> 더 내려갈 필요 없음
            if (node == null || (node.left == null && node.right == null)) {
                return;
            }

            // 한쪽만 null 이어도 "null" 을 찍어야 좌/우 구분이 된다
            append(sb, childPrefix + "\u251c\u2500\u2500 ", childPrefix + "\u2502   ", node.left);
            append(sb, childPrefix + "\u2514\u2500\u2500 ", childPrefix + "    ", node.right);
        }

        static TreeNode fromArray(Integer[] arr) {
            return fromArray(arr, 0);
        }

        static TreeNode fromArray(Integer[] arr, int index) {
            TreeNode root = new TreeNode(arr[index]);

            int leftIndex = leftSon(index);
            int rightIndex = rightSon(index);

            if (leftIndex < arr.length && arr[leftIndex] != null) {
                root.left = fromArray(arr, leftIndex);
            }
            if (rightIndex < arr.length && arr[rightIndex] != null) {
                root.right = fromArray(arr, rightIndex);
            }
            return root;
        }

        static TreeNode fromArray(int[] arr) {
            return fromArray(arr, 0);
        }

        static TreeNode fromArray(int[] arr, int index) {
            TreeNode root = new TreeNode(arr[index]);

            int leftIndex = leftSon(index);
            int rightIndex = rightSon(index);

            if (leftIndex < arr.length) {
                root.left = fromArray(arr, leftIndex);
            }
            if (rightIndex < arr.length) {
                root.right = fromArray(arr, rightIndex);
            }
            return root;
        }

        private static int leftSon(int parentIndex) {
            return parentIndex * 2 + 1;
        }

        private static int rightSon(int parentIndex) {
            return parentIndex * 2 + 2;
        }
    }

    static class Solution {
        int max;

        public int maxDepth(TreeNode root) {
            this.max = 0;
            getMaxDepth(root, 1);
            return max;
        }

        private void getMaxDepth(TreeNode root, int level) {
            if (root == null) {
                this.max = Math.max(max, level - 1);
                return;
            }

            getMaxDepth(root.left, level + 1);
            getMaxDepth(root.right, level + 1);
        }
    }
}
