package y2026.m08;

import utils.TestCase;
import utils.TestRunner;

import java.util.ArrayList;
import java.util.List;


/**
 * https://leetcode.com/problems/greatest-common-divisor-of-strings/
 */
public class D10_KidsWithTheGretestNumberOfCandies {

    public static void main(String[] args) {
        D10_KidsWithTheGretestNumberOfCandies solution = new D10_KidsWithTheGretestNumberOfCandies();

        // 인자 타입이 서로 달라서 Object[] 로 묶어서 전달한다.
        TestRunner.run("Kids With the Greatest Number of Candies",
                input -> solution.kidsWithCandies((int[]) input[0], (int) input[1]),
                new TestCase<>(new Object[]{new int[]{2, 3, 5, 1, 3}, 3}, List.of(true, true, true, false, true)),
                new TestCase<>(new Object[]{new int[]{4, 2, 1, 1, 2}, 1}, List.of(true, false, false, false, false)),
                new TestCase<>(new Object[]{new int[]{12, 1, 12}, 10}, List.of(true, false, true))
        );
    }

    public List<Boolean> kidsWithCandies(int[] candies, int extraCandies) {
        List<Boolean> result = new ArrayList(candies.length);

        int max = getMaxValue(candies);
        for (int n : candies) {
            result.add(n + extraCandies >= max);
        }
        return result;
    }

    int getMaxValue(int[] arr) {
        int max = Integer.MIN_VALUE;
        for (int n : arr) {
            max = Math.max(n, max);
        }
        return max;
    }
}
