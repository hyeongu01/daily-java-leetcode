package y2026.m08;

import utils.TestCase;
import utils.TestRunner;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.PriorityQueue;

class D20_FindKPairsWithSmallestSums {

    public static void main(String[] args) {
        D20_FindKPairsWithSmallestSums solution = new D20_FindKPairsWithSmallestSums();

        TestRunner.run("Find K Pairs with Smallest Sums",
                input -> solution.kSmallestPairs((int[]) input[0], (int[]) input[1], (int) input[2]),
                new TestCase<>(
                        new Object[]{new int[]{1, 7, 11}, new int[]{2, 4, 6}, 3},
                        List.of(
                                List.of(1, 2),
                                List.of(1, 4),
                                List.of(1, 6)
                        )
                ),
                new TestCase<>(
                        new Object[]{new int[]{1, 1, 2}, new int[]{1, 2, 3}, 2},
                        List.of(
                                List.of(1, 1),
                                List.of(1, 1)
                        )
                )
        );

    }

    public List<List<Integer>> kSmallestPairs(int[] nums1, int[] nums2, int k) {
        List<List<Integer>> result = new ArrayList<>(k);
        PriorityQueue<int[]> heap = new PriorityQueue<>(
                Comparator.comparingInt((int[] item) -> nums1[item[0]] + nums2[item[1]])
        );
        heap.add(new int[]{0, 0});

        for (int i = 0; i < k; i++) {
            int[] current = heap.poll();

            if (current[1] == 0 && current[0] + 1 < nums1.length) {
                heap.offer(new int[]{current[0] + 1, 0});
            }
            if (current[1] + 1 < nums2.length) {
                heap.offer(new int[]{current[0], current[1] + 1});
            }
            result.add(List.of(
                    nums1[current[0]],
                    nums2[current[1]]
            ));
        }

        return result;
    }
}
