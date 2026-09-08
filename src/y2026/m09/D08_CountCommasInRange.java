package y2026.m09;

import utils.TestCase;
import utils.TestRunner;

class D08_CountCommasInRange {

    public static void main(String[] args) {
        D08_CountCommasInRange solution = new D08_CountCommasInRange();

        TestRunner.run("Count Commas in Range",
                solution::countCommas,
                new TestCase<>(1002, 3),
                new TestCase<>(998, 0),
                new TestCase<>(2000, 1001)
        );
    }

    public int countCommas(int n) {
        if (n / 1_000 == 0) { // 1 ~ 999
            return 0;
        } else if (n / 100_000 == 0) { // 1,000 ~ 99,999
            int a = n / 1000;
            return (a - 1) * 1000 + (n % 1000 + 1);
        } else { // 100,000
            return 100_000 - 999;
        }
    }
}
