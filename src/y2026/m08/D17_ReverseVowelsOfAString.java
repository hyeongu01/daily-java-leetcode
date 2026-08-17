package y2026.m08;

import utils.TestCase;
import utils.TestRunner;

import java.util.ArrayDeque;
import java.util.Deque;

public class D17_ReverseVowelsOfAString {

    public static void main(String[] args) {
        D17_ReverseVowelsOfAString solution = new D17_ReverseVowelsOfAString();

        TestRunner.run("Reverse Vowels of a String",
                solution::reverseVowels,
                new TestCase<>("IceCreAm", "AceCreIm"),
                new TestCase<>("leetcode", "leotcede")
        );
    }

//    /**
//     * 이렇게 했었는데 결과가 좋지 않아서 다른 풀이를 찾아보니 포인터를 두개 써서 한번에 교체하는 풀이가 있었다.
//     */
//    public String reverseVowels(String s) {
//        Deque<String> stack = new ArrayDeque<>(s.length());
//        StringBuilder sb = new StringBuilder();
//
//        for (String c: s.split("")) {
//            if (isVowel(c.charAt(0))) {
//                stack.push(c);
//            }
//        }
//
//        for (char c: s.toCharArray()) {
//            if (isVowel(c)) {
//                sb.append(stack.pop());
//            } else {
//                sb.append(c);
//            }
//        }
//        return sb.toString();
//    }

    public String reverseVowels(String s) {
        char[] arr = s.toCharArray();
        int left = 0, right = arr.length - 1;

        while (left < right) {
            if (!isVowel(arr[left])) {
                left ++;
                continue;
            }
            if (!isVowel(arr[right])) {
                right --;
                continue;
            }

            char temp = arr[left];
            arr[left] = arr[right];
            arr[right] = temp;

            left ++;
            right --;
        }

        return new String(arr);
    }

    private boolean isVowel(char c) {
        char[] vowels = new char[] { 'a', 'e', 'i', 'o', 'u', 'A', 'E', 'I', 'O', 'U' };

        for (char vowel : vowels) {
            if (c == vowel) {
                return true;
            }
        }
        return false;
    }
}
