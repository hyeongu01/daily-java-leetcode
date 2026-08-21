package y2026.m08;

import utils.TestCase;
import utils.TestRunner;

import java.util.ArrayDeque;
import java.util.Deque;

class D21_RemovingStarsFromAString {

    public static void main(String[] args) {
        D21_RemovingStarsFromAString solution = new D21_RemovingStarsFromAString();

        TestRunner.run("Removing Stars From a String",
                solution::removeStars,
                new TestCase<>("leet**cod*e", "lecoe"),
                new TestCase<>("erase*****", "")
        );
    }

    public String removeStars(String s) {
        Deque<Character> stack = new ArrayDeque<>(s.length());
        for (char c : s.toCharArray()) {
            if (c == '*') {
                stack.pop();
            } else {
                stack.push(c);
            }
        }
        StringBuilder sb = new StringBuilder();
        for (char c: stack.reversed()) {
            sb.append(c);
        }
        return sb.toString();
    }
}
