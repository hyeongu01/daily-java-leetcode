package y2026.m09;

import utils.TestCase;
import utils.TestRunner;

import java.util.ArrayDeque;
import java.util.Deque;

class D07_DecodeString {

    public static void main(String[] args) {
        D07_DecodeString solution = new D07_DecodeString();

        TestRunner.run("Decode String",
                solution::decodeString,
                new TestCase<>("3[a]2[bc]", "aaabcbc"),
                new TestCase<>("3[a2[c]]", "accaccacc"),
                new TestCase<>("2[abc]3[cd]ef", "abcabccdcdcdef")
        );
    }

    public String decodeString(String s) {
        Deque<Character> originStack = new ArrayDeque<>();
        Deque<Character> workStack = new ArrayDeque<>();

        for (char c : s.toCharArray()) {
            originStack.addFirst(c);
        }

        while (!originStack.isEmpty()) {
            char c = originStack.removeFirst();

            if (c == '[') {
                int carry = 1;
                int num = 0;

                while (!originStack.isEmpty()) {
                    int n = originStack.getFirst() - (int) '0';
                    if (n >= 0 && n <= 9) {
                        num += carry * n;
                        carry *= 10;
                        originStack.removeFirst();
                    } else
                        break;
                }

                // 문자열 꺼내기
                StringBuilder sb = new StringBuilder();
                while (!workStack.isEmpty() && workStack.getFirst() != ']') {
                    sb.append(workStack.removeFirst());
                }
                workStack.removeFirst();
                String temp = sb.toString().repeat(num);
                for (int i = temp.length() - 1; i >= 0; i--) {
                    workStack.addFirst(temp.charAt(i));
                }

            } else {
                workStack.addFirst(c);
            }
        }

        StringBuilder sb = new StringBuilder();
        for (char c : workStack) {
            sb.append(c);
        }
        return sb.toString();
    }
}
