package y2026.m08;

import utils.TestCase;
import utils.TestRunner;

import java.util.HashMap;

/**
 * link: https://leetcode.com/problems/longest-substring-without-repeating-characters/
 */
class D07_LongestSubstringWithoutRepeatingCharacters {

    public static void main(String[] args) {
        TestRunner.run("Longest Substring Without Repeating Characters",
                D07_LongestSubstringWithoutRepeatingCharacters::lengthOfLongestSubstring,
                new TestCase<>("abccbad", 4),
                new TestCase<>("abcabcbb", 3),
                new TestCase<>("bbbbb", 1),
                new TestCase<>("pwwkew", 3)
        );
    }

    /**
     * 중복된 문자열을 만나고 이전 문자열로 돌아가서 봤던 것들을 또 순휘하여 시간복잡도가 커짐
     * @param s
     * @return
     */
//    static int lengthOfLongestSubstring(String s) {
//        char[] arr = s.toCharArray();
//        int result = 0;
//
//        HashMap<Character, Integer> map = new HashMap<>();
//        int count  = 0, cursor = 0;
//        while (cursor < arr.length) {
//            if (!map.containsKey(arr[cursor])) {
//                count ++;
//                map.put(arr[cursor], cursor);
//            } else {
//                int target = map.get(arr[cursor]);
//                map.clear();
//                result = Math.max(result, count);
//                cursor = target;
//                count = 0;
//            }
//            cursor++;
//        }
//        result = Math.max(result, count);
//
//        return result;
//    }

    /**
     * 시간복잡도: O(n), 공간복잡도: O(1)
     * @param s
     * @return
     */
    static int lengthOfLongestSubstring(String s) {
        char[] arr = s.toCharArray();
        int result = 0;

        HashMap<Character, Integer> map = new HashMap<>();
        int startIndex = 0;
        for (int i = 0; i < arr.length; i++) {
            Integer prevIndex = map.get(arr[i]);

            if (prevIndex != null && prevIndex >= startIndex) {
                startIndex = prevIndex + 1;
            }
            map.put(arr[i], i);
            result = Math.max(result, i - startIndex + 1);
        }
        return result;
    }

    /**
     * 코드리뷰 이후: HashMap -> int[]
     * @param s
     * @return
     */
//    static int lengthOfLongestSubstring(String s) {
//        int result = 0;
//        int[] lastIndex = new int[128]; // 표준 아스키 코드 범위
//        Arrays.fill(lastIndex, -1);
//
//        int startIndex = 0;
//        for (int i = 0; i < s.length(); i++) {
//            char c = s.charAt(i);
//
//            if (lastIndex[c] >= startIndex) {
//                startIndex = lastIndex[c] + 1;
//            }
//            lastIndex[c] = i;
//            result = Math.max(result, i - startIndex + 1);
//        }
//        return result;
//    }
}

/**
 * 코드 리뷰 이후
 *
 * 중복되지 않도록 문자를 처리할 때 HashMap 을 사용했는데, 아스키코드를 배열로 다루는 방법도 있다고 한다. (마지막 방법 참조)
 */
