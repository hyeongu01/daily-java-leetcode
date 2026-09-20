package y2026.m09;

import utils.TestCase;
import utils.TestRunner;

import java.util.Arrays;

public class D20_MaximumTwinSumofaLinkedList {

    public static void main(String[] args) {
        Solution solution = new Solution();

        ListNode testInput1 = ListNode.from(new int[]{5, 4, 2, 1});
        ListNode testInput2 = ListNode.from(new int[]{4, 2, 2, 3});
        ListNode testInput3 = ListNode.from(new int[]{1, 100000});

        TestRunner.run("Maximum Twin Sum of a Linked List",
                solution::pairSum,
                new TestCase<>(testInput1, 6),
                new TestCase<>(testInput2, 7),
                new TestCase<>(testInput3, 100001)
        );
    }

    static class ListNode {
        int val;
        ListNode next;

        ListNode(int val) {
            this.val = val;
        }

        ListNode(int val, ListNode next) {
            this.val = val;
            this.next = next;
        }

        @Override
        public String toString() {
            StringBuilder sb = new StringBuilder();
            sb.append("[");

            ListNode current = this;

            while (current != null) {
                if (current != this) sb.append(", ");
                sb.append(current.val);
                current = current.next;
            }
            return sb.append("]").toString();
        }

        static ListNode from(int[] arr) {
            if (arr.length == 0) return null;
            ListNode head = new ListNode(arr[0]);
            ListNode current = head;

            for (int i = 1; i < arr.length; i++) {
                ListNode newNode = new ListNode(arr[i]);
                current.next = newNode;
                current = current.next;
            }
            return head;
        }
    }

    static class Solution {
        public int pairSum(ListNode head) {
            int n = getListNodeSize(head);
            int[] twinSums = new int[n / 2];
            Arrays.fill(twinSums, 0);
            ListNode currentNode = head;

            for (int i = 0; i < n; i++) {
                int targetIndex = i >= (n / 2) ? (n / 2) - 1 - (i % (n / 2)) : i;
                twinSums[targetIndex] += currentNode.val;
                currentNode = currentNode.next;
            }

            int maxSum = twinSums[0];
            for (int sum : twinSums) {
                maxSum = Math.max(sum, maxSum);
            }
            return maxSum;
        }

        private int getListNodeSize(ListNode head) {
            int size = 0;
            ListNode currentNode = head;
            while (currentNode != null) {
                size++;
                currentNode = currentNode.next;
            }
            return size;
        }
    }
}
