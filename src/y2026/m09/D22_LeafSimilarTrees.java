package y2026.m09;

import utils.TestCase;
import utils.TestRunner;

import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Deque;
import java.util.List;

public class D22_LeafSimilarTrees {

    public static void main(String[] args) {
        Solution solution = new Solution();


        TestRunner.run("Leaf-Similar Trees",
                input -> solution.leafSimilar((TreeNode) input[0], (TreeNode) input[1]),
                new TestCase<>(new Object[]{TreeNode.fromArray(new Integer[]{3, 5, 1, 6, 2, 9, 8, null, null, 7, 4}), TreeNode.fromArray(new Integer[]{3, 5, 1, 6, 7, 4, 2, null, null, null, null, null, null, 9, 8})}, true),
                new TestCase<>(new Object[]{TreeNode.fromArray(new Integer[]{1, 2, 3}), TreeNode.fromArray(new Integer[]{1, 3, 2})}, false)
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

        static TreeNode fromArray(Integer[] arr) {
            return fromArray(arr, 0);
        }

        private static TreeNode fromArray(Integer[] arr, int index) {
            if (arr[index] == null) return null;
            TreeNode root = new TreeNode(arr[index]);

            if (index * 2 + 1 < arr.length) {
                root.left = fromArray(arr, index * 2 + 1);
            }
            if (index * 2 + 2 < arr.length) {
                root.right = fromArray(arr, index * 2 + 2);
            }
            return root;
        }

        @Override
        public String toString() {
            StringBuilder sb = new StringBuilder();
            printNode(this, 0, sb);
            return sb.toString();
        }

        int size() {
            int count = 0;
            Deque<TreeNode> queue = new ArrayDeque<>();
            queue.addFirst(this);

            while (!queue.isEmpty()) {
                TreeNode current = queue.removeLast();
                count++;

                if (current.left != null) {
                    queue.addFirst(current.left);
                }
                if (current.right != null) {
                    queue.addFirst(current.right);
                }
            }
            return count;
        }

        private void printNode(TreeNode root, int level, StringBuilder sb) {
            sb.repeat("  ", level);
            sb.append(" ").append(root == null ? "null" : root.val).append('\n');

            if (root == null) return;

            printNode(root.left, level + 1, sb);
            printNode(root.right, level + 1, sb);
        }
    }

    static class Solution {
        public boolean leafSimilar(TreeNode root1, TreeNode root2) {
            List<Integer> leaf1 = new ArrayList<>();
            List<Integer> leaf2 = new ArrayList<>();

            findLeafs(root1, leaf1);
            findLeafs(root2, leaf2);

            return leaf1.equals(leaf2);
        }

        private void findLeafs(TreeNode root, List<Integer> leafs) {
            if (root.left == null && root.right == null) {
                leafs.add(root.val);
            }
            if (root.left != null) {
                findLeafs(root.left, leafs);
            }
            if (root.right != null) {
                findLeafs(root.right, leafs);
            }
        }
    }
}
