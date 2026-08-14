package algorithems.heap;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.Objects;
import java.util.PriorityQueue;
import java.util.Random;

public class HeapTest {
    private static int passed;
    private static int failed;

    public static void main(String[] args) {
        run("MinHeap basic order", HeapTest::testMinHeapOrder);
        run("MaxHeap basic order", HeapTest::testMaxHeapOrder);
        run("MinHeap randomized", () -> testAgainstPriorityQueue(
                new MinHeap<>(1_000), new PriorityQueue<>(), 42L));
        run("MaxHeap randomized", () -> testAgainstPriorityQueue(
                new MaxHeap<>(1_000), new PriorityQueue<>(Comparator.reverseOrder()), 42L));
        run("MinHeap capacity", () -> testCapacity(new MinHeap<>(3)));
        run("MaxHeap capacity", () -> testCapacity(new MaxHeap<>(3)));
        run("MinHeap empty and reuse", () -> testEmptyAndReuse(new MinHeap<>(3)));
        run("MaxHeap empty and reuse", () -> testEmptyAndReuse(new MaxHeap<>(3)));

        System.out.printf("%nResult: %d passed, %d failed%n", passed, failed);
        if (failed > 0) {
            throw new AssertionError("Heap verification failed: " + failed + " test(s)");
        }
    }

    private static void testMinHeapOrder() {
        assertPopOrder(
                new MinHeap<>(7),
                new int[]{7, -3, 7, 1, 0, -10, 5},
                List.of(-10, -3, 0, 1, 5, 7, 7)
        );
    }

    private static void testMaxHeapOrder() {
        assertPopOrder(
                new MaxHeap<>(7),
                new int[]{7, -3, 7, 1, 0, -10, 5},
                List.of(7, 7, 5, 1, 0, -3, -10)
        );
    }

    private static void assertPopOrder(IHeap<Integer> heap, int[] values, List<Integer> expected) {
        for (int value : values) {
            assertTrue(heap.add(value), "add should succeed: " + value);
        }

        assertEquals(values.length, heap.size(), "size after add");
        assertEquals(expected.getFirst(), heap.peek(), "peek after add");

        List<Integer> actual = new ArrayList<>();
        while (!heap.isEmpty()) {
            actual.add(heap.pop());
        }

        assertEquals(expected, actual, "pop order");
        assertEquals(0, heap.size(), "size after pop");
    }

    private static void testAgainstPriorityQueue(
            IHeap<Integer> heap,
            PriorityQueue<Integer> expected,
            long seed
    ) {
        Random random = new Random(seed);
        for (int i = 0; i < 1_000; i++) {
            int value = random.nextInt(201) - 100;
            assertTrue(heap.add(value), "random add should succeed at index " + i);
            expected.add(value);
        }

        while (!expected.isEmpty()) {
            assertEquals(expected.remove(), heap.pop(), "random pop order");
        }
        assertTrue(heap.isEmpty(), "heap should be empty after random test");
    }

    private static void testCapacity(IHeap<Integer> heap) {
        assertTrue(heap.add(3), "first add");
        assertTrue(heap.add(2), "second add");
        assertTrue(heap.add(1), "third add");
        assertFalse(heap.add(0), "add over capacity");
        assertEquals(3, heap.size(), "size at capacity");
    }

    private static void testEmptyAndReuse(IHeap<Integer> heap) {
        assertTrue(heap.isEmpty(), "new heap should be empty");
        assertEquals(null, heap.peek(), "peek on empty heap");
        assertEquals(null, heap.pop(), "pop on empty heap");

        assertTrue(heap.add(42), "add before reuse");
        assertEquals(42, heap.pop(), "pop before reuse");
        assertTrue(heap.isEmpty(), "heap should be empty before reuse");

        assertTrue(heap.add(7), "add after reuse");
        assertEquals(7, heap.peek(), "peek after reuse");
        assertEquals(7, heap.pop(), "pop after reuse");
    }

    private static void run(String name, Runnable test) {
        try {
            test.run();
            passed++;
            System.out.println("PASS | " + name);
        } catch (Throwable error) {
            failed++;
            System.out.printf("FAIL | %s | %s: %s%n",
                    name, error.getClass().getSimpleName(), error.getMessage());
        }
    }

    private static void assertTrue(boolean condition, String message) {
        if (!condition) {
            throw new AssertionError(message);
        }
    }

    private static void assertFalse(boolean condition, String message) {
        assertTrue(!condition, message);
    }

    private static void assertEquals(Object expected, Object actual, String message) {
        if (!Objects.equals(expected, actual)) {
            throw new AssertionError(message + " | expected=" + expected + ", actual=" + actual);
        }
    }
}
