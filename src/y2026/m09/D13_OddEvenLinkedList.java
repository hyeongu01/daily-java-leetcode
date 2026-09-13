package y2026.m09;

import utils.TestCase;
import utils.TestRunner;

public class D13_OddEvenLinkedList {

    public static void main(String[] args) {
        Solution solution = new Solution();

        ListNode exInput1 = new ListNode(new int[]{1, 2, 3, 4, 5});
        ListNode exResult1 = new ListNode(new int[]{1, 3, 5, 2, 4});

        ListNode exInput2 = new ListNode(new int[]{2, 1, 3, 5, 6, 4, 7});
        ListNode exResult2 = new ListNode(new int[]{2, 3, 6, 7, 1, 5, 4});

        TestRunner.run("Odd Even Linked List",
                solution::oddEvenList,
                new TestCase<>(exInput1, exResult1),
                new TestCase<>(exInput2, exResult2),
                new TestCase<>(null, null),
                new TestCase<>(new ListNode(2), new ListNode(2))
        );
    }

    private static class ListNode {
        int val;
        ListNode next;

        ListNode() {
        }

        ListNode(int val) {
            this.val = val;
        }

        ListNode(int val, ListNode next) {
            this.val = val;
            this.next = next;
        }

        // 편의상 추가한 매서드 Solution 에서는 사용하지 말것!
        ListNode(int[] arr) {
            ListNode currentNode = this;
            this.val = arr[0];

            for (int i = 1; i < arr.length; i++) {
                currentNode.next = new ListNode(arr[i]);
                currentNode = currentNode.next;
            }
        }

        @Override
        public boolean equals(Object o) {
            if (o == this) return true;
            if (!(o instanceof ListNode)) return false;

            ListNode a = this;
            ListNode b = (ListNode) o;

            while (a != null && b != null) {
                if (a.val != b.val) return false;
                a = a.next;
                b = b.next;
            }
            return a == null && b == null;
        }

        @Override
        public String toString() {
            StringBuilder sb = new StringBuilder();
            sb.append("[");

            ListNode currentNode = this;
            while (currentNode != null) {
                if (currentNode != this) {
                    sb.append(", ");
                }
                sb.append(currentNode.val);
                currentNode = currentNode.next;
            }
            return sb.append("]").toString();
        }
    }

    private static class Solution {

        public ListNode oddEvenList(ListNode head) {
            ListNode evenHead = null, oddHead = null;
            ListNode evenCurrentNode = null, oddCurrentNode = null;
            ListNode current = head;
            int count = 0;

            while (current != null) {
                count++;
                if (count % 2 == 1) {
                    if (evenHead == null) {
                        evenHead = current;
                        evenCurrentNode = current;
                    } else {
                        evenCurrentNode.next = current;
                        evenCurrentNode = current;
                    }
                } else {
                    if (oddHead == null) {
                        oddHead = current;
                        oddCurrentNode = current;
                    } else {
                        oddCurrentNode.next = current;
                        oddCurrentNode = current;
                    }
                }
                current = current.next;
            }
            if (evenCurrentNode == null) return null;
            evenCurrentNode.next = null;
            if (oddCurrentNode == null) return evenHead;
            oddCurrentNode.next = null;
            evenCurrentNode.next = oddHead;

            return evenHead;
        }
    }
}
