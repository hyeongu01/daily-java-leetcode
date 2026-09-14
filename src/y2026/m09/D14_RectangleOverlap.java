package y2026.m09;

import utils.TestCase;
import utils.TestRunner;

/**
 * https://leetcode.com/problems/rectangle-overlap
 */
public class D14_RectangleOverlap {

    public static void main(String[] args) {
        Solution solution = new Solution();

        TestRunner.run("Rectangle Overlap",
                input -> solution.isRectangleOverlap((int[]) input[0], (int[]) input[1]),
                new TestCase<>(new Object[]{new int[]{0,0,2,2}, new int[]{1,1,3,3}}, true),
                new TestCase<>(new Object[]{new int[]{0,0,1,1}, new int[]{1,0,2,1}}, false),
                new TestCase<>(new Object[]{new int[]{0,0,1,1}, new int[]{2,2,3,3}}, false)
        );
    }

    private static class Solution {
        public boolean isRectangleOverlap(int[] rec1, int[] rec2) {
            Rectangle r1 = new Rectangle(rec1);
            Rectangle r2 = new Rectangle(rec2);

            return !(r1.x2 <= r2.x1 || r1.y2 <= r2.y1 || r1.x1 >= r2.x2 || r1.y1 >= r2.y2);
        }

        private static class Rectangle {
            int x1, y1;
            int x2, y2;

            Rectangle(int[] arr) {
                this.x1 = arr[0];
                this.y1 = arr[1];
                this.x2 = arr[2];
                this.y2 = arr[3];
            }
        }
    }
}
