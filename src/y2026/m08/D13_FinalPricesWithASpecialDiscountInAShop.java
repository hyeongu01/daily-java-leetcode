package y2026.m08;

import utils.TestCase;
import utils.TestRunner;

import java.util.*;

/**
 * https://leetcode.com/problems/final-prices-with-a-special-discount-in-a-shop
 * 자료구조: 배열, 알고리즘: 순회
 *
 * 개선사항
 * - 제약사항이 널럴해서 O(n^2) 임을 가만하고 완전 순회로 문제를 해결함.
 * - Stack 을 사용한 더 효율적인 풀이를 발견해 Stack 자료구조를 사용한 구조로 개선함.
 *
 * 개선 결과
 * - 입력 풀 (n) 이 너무 작아서 그런지 속도, 메모리는 개선한 것이 더 안좋게 나타났다..
 */
class D13_FinalPricesWithASpecialDiscountInAShop {

    public static void main(String[] args) {
        D13_FinalPricesWithASpecialDiscountInAShop solution = new D13_FinalPricesWithASpecialDiscountInAShop();

        TestRunner.run("Final Prices With a Special Discount in a Shop",
                solution::finalPrices,
                new TestCase<>(new int[]{8,4,6,2,3}, new int[]{4,2,4,2,3}),
                new TestCase<>(new int[]{1,2,3,4,5}, new int[]{1,2,3,4,5}),
                new TestCase<>(new int[]{10,1,1,6}, new int[]{9,0,1,6})
        );
    }

    /**
     * 시간복잡도: O(n^2)
     * 완전순회하는 문제가 있음
     * @param prices
     * @return
     */
//    public int[] finalPrices(int[] prices) {
//        int[] result = new int[prices.length];
//
//        for (int i = 0; i < prices.length; i++) {
//            int price = prices[i];
//            for (int j = i + 1; j < prices.length; j++) {
//                if (price >= prices[j]) {
//                    price -= prices[j];
//                    break;
//                }
//            }
//            result[i] = price;
//        }
//        return result;
//    }


    /**
     * 시간복잡도: O(n)
     *
     * int -> Integer 변환 오버헤드 문제 발생
     * Integer.valueOf(n) 으로 int -> Integer 변환을 하는데, 이 때 캐시값의 범위가 -128 ~ 127 임.
     * 이 범위를 넘어가면 새로운 Integer 객체를 생성하는 작업이 필요하여 오버헤드가 발생함.
     * @param prices
     * @return
     */
//    public int[] finalPrices(int[] prices) {
//        int n = prices.length;
//        Deque<Integer> stack = new ArrayDeque<>(n);
//
//        for (int i = 0; i < n; i ++) {
//            while (!stack.isEmpty() && prices[stack.peek()] >= prices[i]) {
//                int idx = stack.pop();
//                prices[idx] -= prices[i];
//            }
//            stack.push(i);
//        }
//        return prices;
//    }

    /**
     * 시간복잡도: O(n)
     *
     * Stack -> int 배열
     * @param prices
     * @return
     */
    public int[] finalPrices(int[] prices) {
        int n = prices.length;
        int[] stack = new int[n];
        int top = -1;

        for (int i = 0; i < n; i ++) {
            while (top > -1 && prices[stack[top]] >= prices[i]) {
                int idx = stack[top --];
                prices[idx] -= prices[i];
            }
            stack[++ top] = i;
        }
        return prices;
    }
}
