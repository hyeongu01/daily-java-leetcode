package y2026.m08;

import utils.TestCase;
import utils.TestRunner;

/**
 * https://leetcode.com/problems/can-place-flowers/
 */
public class D10_CanPlaceFlowers {

    public static void main(String[] args) {
        D10_CanPlaceFlowers solution = new D10_CanPlaceFlowers();

        TestRunner.run("Can Place Flowers",
                input -> solution.canPlaceFlowers((int[]) input[0], (int) input[1]),
                new TestCase<>(new Object[]{new int[]{1, 0, 0, 0, 1}, 1}, true),
                new TestCase<Object[], Boolean>(new Object[]{new int[]{1, 0, 0, 0, 1}, 2}, false)
        );
    }


    public boolean canPlaceFlowers(int[] flowerbed, int n) {
        for (int i = 0; i < flowerbed.length; i++) {
            if (n <= 0) break;
            int prev = (i - 1) >= 0 ? flowerbed[i - 1] : 0;
            int next = (i + 1) < flowerbed.length ? flowerbed[i + 1] : 0;

            if (flowerbed[i] == 0 && prev == 0 && next == 0) {
                flowerbed[i] = 1;
                n --;
                i ++;
            }
        }
        return n <= 0;
    }
}
