package y2026.m08;

import utils.TestCase;
import utils.TestRunner;

import java.util.ArrayList;
import java.util.List;

class D31_FindTheMinimumAndMaximumNumberOfNodesBetweenCriticalPoints {

    public static void main(String[] args) {
        D31_FindTheMinimumAndMaximumNumberOfNodesBetweenCriticalPoints solution = new D31_FindTheMinimumAndMaximumNumberOfNodesBetweenCriticalPoints();


        ListNode testCase1 = new ListNode(3, new ListNode(1));
        ListNode testCase2 = new ListNode(5, new ListNode(3, new ListNode(1, new ListNode(2, new ListNode(5, new ListNode(1, new ListNode(2)))))));
        ListNode testCase3 = new ListNode(1, new ListNode(3, new ListNode(2, new ListNode(2, new ListNode(3, new ListNode(2, new ListNode(2, new ListNode(2, new ListNode(7)))))))));

        TestRunner.run("Find the Minimum and Maximum Number of Nodes Between Critical Points",
                solution::nodesBetweenCriticalPoints,
                new TestCase<>(testCase1, new int[]{-1, -1}),
                new TestCase<>(testCase2, new int[]{1, 3}),
                new TestCase<>(testCase3, new int[]{3, 3})
        );
    }

    static class ListNode {
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
    }

    public int[] nodesBetweenCriticalPoints(ListNode head) {
        ListNode current = head;
        int currentIndex = 0;
        List<Integer> criticalPointIndexList = new ArrayList<>();
        int min = Integer.MAX_VALUE;

        while (current.next.next != null) {
            if ((current.val > current.next.val && current.next.val < current.next.next.val)
                    || (current.val < current.next.val && current.next.val > current.next.next.val)) {
                criticalPointIndexList.add(currentIndex + 1);

                if (criticalPointIndexList.size() >= 2) {
                    min = Math.min(min, currentIndex + 1 - criticalPointIndexList.get(criticalPointIndexList.size() - 2));
                }
            }
            current = current.next;
            currentIndex++;
        }

        if (criticalPointIndexList.size() < 2)
            return new int[]{-1, -1};

        int max = criticalPointIndexList.getLast() - criticalPointIndexList.getFirst();
        return new int[]{min, max};
    }
}
