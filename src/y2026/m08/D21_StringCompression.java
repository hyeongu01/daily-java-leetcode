package y2026.m08;

import utils.TestCase;
import utils.TestRunner;

public class D21_StringCompression {

    public static void main(String[] args) {
        D21_StringCompression solution = new D21_StringCompression();

        TestRunner.run("String Compression", solution::compress,
                new TestCase<>(new char[]{'a', 'a', 'b', 'b', 'c', 'c', 'c'}, 6),
                new TestCase<>(new char[]{'a'}, 1),
                new TestCase<>(new char[]{'a', 'b', 'b', 'b', 'b', 'b', 'b', 'b', 'b', 'b', 'b', 'b', 'b'}, 4)
        );
    }

    public int compress(char[] chars) {
        int write = 0;
        int count = 1;

        for (int i = 1; i < chars.length; i++) {
            if (chars[i] == chars[write]) {
                count++;
                continue;
            } else {
                if (count > 1) {
                    String str = Integer.toString(count);
                    for (char c : str.toCharArray()) {
                        chars[++write] = c;
                    }
                }
                chars[++write] = chars[i];
                count = 1;
            }
        }
        if (count > 1) {
            String str = Integer.toString(count);
            for (char c : str.toCharArray()) {
                chars[++write] = c;
            }
        }
        return write + 1;
    }
}
