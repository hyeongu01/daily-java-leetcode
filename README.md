# Daily java leetCode Repo

## 폴더 구조
```
src/
├── y{yyyy}/m{MM}/       # 푼 날짜 기준으로 연/월 폴더에 문제 풀이를 둔다
│   └── D{dd}_{ProblemName}.java
├── algorithems/         # 여러 문제에서 재사용하는 알고리즘 (예: Gcd)
└── utils/               # 풀이와 무관한 공용 코드
    ├── TestCase.java    # 입력 / 기대값 한 쌍
    └── TestRunner.java  # 테스트 실행 + 결과 출력 자동화
```

## 파일 형식
- 파일명(= 클래스명)은 `D{dd}_{ProblemName}` — 날짜가 앞이라 폴더 안에서 날짜순으로 정렬된다.
    - 예: `src/y2026/m08/D10_GreatestCommonDivisorOfStrings.java`
- 클래스 위 javadoc 에 문제 링크를 남긴다. (`link: https://leetcode.com/problems/...`)
- `main` 에서는 `TestRunner.run(제목, 풀이, 테스트케이스...)` 만 호출한다. 정답 여부 비교와 출력은 러너가 처리한다.

```java
package y2026.m08;

import utils.TestCase;
import utils.TestRunner;

/**
 * link: https://leetcode.com/problems/move-zeroes/
 */
public class D09_MoveZeroes {

    public static void main(String[] args) {
        TestRunner.run("Move Zeroes", D09_MoveZeroes::solve,
                new TestCase<>(new int[]{0, 1, 0, 3, 12}, new int[]{1, 3, 12, 0, 0}),
                new TestCase<>(new int[]{0}, new int[]{0})
        );
    }

    // 풀이 메서드
}
```

출력 예시
```
===== Move Zeroes =====
[1/2] | 정답 | input = [0, 1, 0, 3, 12], result = [1, 3, 12, 0, 0], expected = [1, 3, 12, 0, 0]
[2/2] | 정답 | input = [0], result = [0], expected = [0]
결과: 2/2 통과
```

- 인자가 여러 개인 문제는 배열로 묶어서 넘긴다. 타입이 같으면 `String[]`, 다르면 `Object[]` + 캐스팅.
    - `new TestCase<>(new String[]{"ABCABC", "ABC"}, "ABC")` → `input -> solution.gcdOfStrings(input[0], input[1])`
- 풀이가 입력 배열을 직접 수정하는(in-place) 문제는 복사본에 적용해 반환하는 `solve()` 래퍼를 둔다. (`D09_MoveZeroes` 참고)

## 미해결 문제
- [3348. Smallest Divisible Digit Product II](src/y2026/m08/D07_SmallestDivisibleDigitProduct2.java)
