package utils;

import java.lang.reflect.Array;
import java.util.Arrays;
import java.util.List;
import java.util.Objects;
import java.util.function.Function;

/**
 * 테스트 케이스 실행부터 결과 출력까지 자동화하는 유틸.
 *
 * <pre>
 * TestRunner.run("Move Zeroes", MoveZeroes_d09::solve,
 *         new TestCase&lt;&gt;(new int[]{0, 1, 0, 3, 12}, new int[]{1, 3, 12, 0, 0}),
 *         new TestCase&lt;&gt;(new int[]{0}, new int[]{0}));
 * </pre>
 */
public class TestRunner {

    /**
     * 테스트 케이스를 가변인자로 받아 실행한다.
     *
     * @param title    출력 상단에 표시할 제목 (null 이면 생략)
     * @param solution 입력을 받아 결과를 반환하는 풀이
     * @param cases    입력 / 기대값 쌍
     * @return 전부 통과했으면 true
     */
    @SafeVarargs
    public static <T, M> boolean run(String title, Function<T, M> solution, TestCase<T, M>... cases) {
        return run(title, Arrays.asList(cases), solution);
    }

    /** 케이스를 List 로 모아둔 경우. */
    public static <T, M> boolean run(String title, List<TestCase<T, M>> cases, Function<T, M> solution) {
        if (title != null) {
            System.out.println("===== " + title + " =====");
        }

        int passed = 0;
        for (int i = 0; i < cases.size(); i++) {
            TestCase<T, M> tc = cases.get(i);
            // 풀이가 입력 배열을 직접 수정할 수 있으므로 실행 전에 문자열로 보존한다.
            String input = stringify(tc.input);
            String no = String.format("[%d/%d]", i + 1, cases.size());

            try {
                M result = solution.apply(tc.input);
                boolean correct = Objects.deepEquals(result, tc.output);
                if (correct) {
                    passed++;
                }
                System.out.printf("%s | %s | input = %s, result = %s, expected = %s%n",
                        no, correct ? "정답" : "오답", input, stringify(result), stringify(tc.output));
            } catch (Throwable e) {
                System.out.printf("%s | 에러 | input = %s, expected = %s, error = %s: %s%n",
                        no, input, stringify(tc.output), e.getClass().getSimpleName(), e.getMessage());
            }
        }

        System.out.printf("결과: %d/%d 통과%n%n", passed, cases.size());
        return passed == cases.size();
    }

    /** 배열(원시 타입 포함)은 내용까지 펼쳐서 출력한다. */
    private static String stringify(Object value) {
        if (value == null) {
            return "null";
        }
        if (value instanceof Object[]) {
            return Arrays.deepToString((Object[]) value);
        }
        if (value.getClass().isArray()) {
            StringBuilder sb = new StringBuilder("[");
            int length = Array.getLength(value);
            for (int i = 0; i < length; i++) {
                if (i > 0) {
                    sb.append(", ");
                }
                sb.append(Array.get(value, i));
            }
            return sb.append("]").toString();
        }
        return String.valueOf(value);
    }
}