package y2026.m08;

import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.Stream;

class Input {
    String num;
    int t;

    Input(String num, int t) {
        this.num = num;
        this.t = t;
    }
}

public class SmallestDivisibleDigitProduct2_d07 {
    static int result = Integer.MAX_VALUE;

    public static void main(String[] args) {
        HashMap<Input, String> testCases = new HashMap<>();

        testCases.put(new Input("1234", 256), "1488");
        testCases.put(new Input("12355", 50), "12355");
        testCases.put(new Input("11111", 26), "-1");

        for (Map.Entry<Input, String> entry: testCases.entrySet()) {
            String res = solution(entry.getKey().num, entry.getKey().t);
            if (res.equals(entry.getValue())) {
                System.out.println(" | 성공 | ");
            } else {
                System.out.println(" | 실패 | ");
            }
        }

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

        System.out.println(result);
        return Integer.toString(result);
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
        System.out.println(stringBuffer.toString());
        if (!stringBuffer.isEmpty() && Integer.parseInt(stringBuffer.toString()) > minValue && Integer.parseInt(stringBuffer.toString()) % t == 0) {
            result = Math.min(Integer.parseInt(stringBuffer.toString()), result);
            return;
        }
        for (int c: candidate) {
            stringBuffer.append(c);
            getCandidates(candidate, stringBuffer, minValue, t);
            stringBuffer.deleteCharAt(stringBuffer.length() - 1);
        }
    }
}
