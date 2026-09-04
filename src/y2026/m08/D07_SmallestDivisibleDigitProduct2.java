package y2026.m08;

import utils.TestCase;
import utils.TestRunner;

import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.Stream;

/**
 * https://leetcode.com/problems/smallest-divisible-digit-product-ii/description/?envType=daily-question&envId=2026-08-07
 */
class D07_SmallestDivisibleDigitProduct2 {
    static String result = null;

    public static void main(String[] args) {
        // 인자 타입이 서로 달라서 Object[] 로 묶어서 전달한다.
        TestRunner.run("Smallest Divisible Digit Product II",
                input -> {
                    result = null; // 케이스마다 전역 결과 초기화
                    return solution((String) input[0], (int) input[1]);
                },
                new TestCase<>(new Object[]{"1234", 256}, "1488"),
                new TestCase<>(new Object[]{"12355", 50}, "12355"),
                new TestCase<>(new Object[]{"11111", 26}, "-1")
        );
//        String a = "123";
//        String b = "124";
//        List<String> temp = new ArrayList<>(List.of(a, b));
//        temp.sort(Comparator.naturalOrder());
//        System.out.println(temp);
//        System.out.println(temp.getFirst());

        System.out.println(getDigitNumbers(256));
        System.out.println(getDigitNumbers(50));
        System.out.println(getDigitNumbers(26));
    }

    /**
     * 접근 방식
     * - t의 한 자리수 약수를 리스트함.
     * - num 부터 시작해서 1씩 증가하며 검증
     * - 검증 시작 전에 가능 여부부터 판단, 가능을 검증하는 기준은 10이상의 약수들의 10 미만 약수들이 원본 숫자와 동일할 것.
     *    (10 미만 약수들로 10 이상 약수들을 만들 수 있을 것.)
     * @param num
     * @param t
     * @return
     */
    static String solution(String num, int t) {
        if (!validator(t)) {
            return "-1";
        }
        int min = Integer.parseInt(num);

        List<Integer> components = getDigitNumbers(t).stream()
                        .filter(item -> item < 10)
                        .toList();
        StringBuffer sb = new StringBuffer();
        getCandidates(components, sb, min, t);
        return result;
    }

    /* 본인 제외한 약수 반환 */
    static List<Integer> getDigitNumbers(int num) {
        List<Integer> res = new ArrayList<>();
        res.add(1);

        for (int i = 2; i <= Math.sqrt(num); i++) {
            if (num % i == 0) {
                res.add(i);
                if (num / i != i) {
                    res.add(num / i);
                }
            }
        }
        res.sort(Comparator.naturalOrder());
        return res;
    }

    static boolean validator(int number) {
        Set<Integer> lessThen10Divisor = getDigitNumbers(number).stream()
                .filter(item -> item < 10)
                .collect(Collectors.toSet());
        Set<Integer> greaterThen10Divisor = getDigitNumbers(number).stream()
                .filter(item -> item >= 10)
                .collect(Collectors.toSet());

        while (!greaterThen10Divisor.isEmpty()) {
            Iterator<Integer> it = greaterThen10Divisor.iterator();
            Integer cur = it.next();
            it.remove();

            List<Integer> curDivisor = getDigitNumbers(cur);
            if (curDivisor.size() == 1) {
                return false;
            }
            List<Integer> lessThen10CurDivisor = curDivisor.stream()
                    .filter(item -> item < 10)
                    .toList();
            List<Integer> greaterThen10CurDivisor = curDivisor.stream()
                    .filter(item -> item >= 10)
                    .toList();

            if (!lessThen10Divisor.containsAll(lessThen10CurDivisor)) {
                return false;
            }
            greaterThen10Divisor.addAll(greaterThen10CurDivisor);
        }
        return true;
    }

    static void getCandidates(List<Integer> candidate, StringBuffer stringBuffer, int minValue, int t) {
        if (!stringBuffer.isEmpty() && stringBuffer.toString().compareTo(Integer.toString(minValue)) >= 0 && validator2(stringBuffer.toString(), t)) {
            if (result == null) {
                result = stringBuffer.toString();
                return;
            }
            List<String> temp = new ArrayList<>(List.of(new String[]{result, stringBuffer.toString()}));
            temp.sort(Comparator.naturalOrder());
            result = temp.getFirst();
            return;
        }
        for (int c: candidate) {
            stringBuffer.append(c);
            getCandidates(candidate, stringBuffer, minValue, t);
            stringBuffer.deleteCharAt(stringBuffer.length() - 1);
        }
    }

    static boolean validator2(String num, int t) {
        int prod = 1;
        for (String c: num.split("")) {
            prod *= Integer.parseInt(c);
        }
        return prod % t == 0;
    }
}
