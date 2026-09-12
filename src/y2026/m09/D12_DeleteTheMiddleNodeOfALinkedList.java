package y2026.m09;

import utils.TestCase;
import utils.TestRunner;

class D12_DeleteTheMiddleNodeOfALinkedList {

    public static void main(String[] args) {
        Solution solution = new Solution();

        ListNode testList1 = new ListNode(1, new ListNode(3, new ListNode(4, new ListNode(7, new ListNode(1, new ListNode(2, new ListNode(6)))))));
        ListNode testList2 = new ListNode(1, new ListNode(2, new ListNode(3, new ListNode(4))));
        ListNode testList3 = new ListNode(2, new ListNode(1));
        ListNode testList4 = new ListNode(2);

        ListNode resultList1 = new ListNode(1, new ListNode(3, new ListNode(4, new ListNode(1, new ListNode(2, new ListNode(6))))));
        ListNode resultList2 = new ListNode(1, new ListNode(2, new ListNode(4)));
        ListNode resultList3 = new ListNode(2);
        ListNode resultList4 = null;


        TestRunner.run("Delete the Middle Node of a Linked List",
                solution::deleteMiddle,
                new TestCase<>(testList1, resultList1),
                new TestCase<>(testList2, resultList2),
                new TestCase<>(testList3, resultList3),
                new TestCase<>(testList4, resultList4)
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

        @Override
        public boolean equals(Object o) {
            if (this == o) return true;
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
                if (currentNode != this) sb.append(", ");
                sb.append(currentNode.val);
                currentNode = currentNode.next;
            }
            return sb.append("]").toString();
        }
    }

    private static class Solution {
        public ListNode deleteMiddle(ListNode head) {
            int length = getListNodeLength(head);
            if (length == 1) return null;

            int targetIndex = length / 2;
            ListNode targetPrevNode = head;

            for (int i = 0; i < targetIndex - 1; i++) {
                targetPrevNode = targetPrevNode.next;
            }
            targetPrevNode.next = targetPrevNode.next.next;

            return head;
        }

        private int getListNodeLength(ListNode head) {
            int length = 0;
            ListNode current = head;

            while (current != null) {
                length++;
                current = current.next;
            }
            return length;
        }
    }
}
