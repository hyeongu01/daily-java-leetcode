package y2026.m08;

import utils.TestCase;
import utils.TestRunner;

import java.util.Arrays;

class D21_AsteroidCollision {

    public static void main(String[] args) {
        D21_AsteroidCollision solution = new D21_AsteroidCollision();

        TestRunner.run("Asteroid Collision", solution::asteroidCollision,
                new TestCase<>(new int[]{5, 10, -5}, new int[]{5, 10}),
                new TestCase<>(new int[]{8, -8}, new int[]{}),
                new TestCase<>(new int[]{10, 2, -5}, new int[]{10})
        );
    }

    public int[] asteroidCollision(int[] asteroids) {
        int[] stack = new int[asteroids.length];
        int head = -1;

        for (int i = 0; i < asteroids.length; i++) {
            int current = asteroids[i];

            if (current > 0) {
                stack[++head] = current;
            } else {
                if (head < 0 || stack[head] < 0) { // stack is empty || peek is minus
                    stack[++head] = current;
                } else if (stack[head] < Math.abs(current)) {
                    head--;
                    i--;
                } else if (stack[head] == Math.abs(current)) {
                    head--;
                }
            }
        }
        return Arrays.copyOf(stack, head + 1);
    }
}
