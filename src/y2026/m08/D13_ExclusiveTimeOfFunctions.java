package y2026.m08;

import utils.TestCase;
import utils.TestRunner;

import java.util.*;

/**
 * https://leetcode.com/problems/exclusive-time-of-functions/
 * <p>
 * 자료구조: Stack
 * 접근 방식: 괄호와 같이 end 신호가 오면 start 신호를 함께 제거하며 시간을 증/감
 *
 * 개선사항:
 * - 기존 Stack class 를 사용하여 스택을 사용함.
 * - java.util.Stack 은 캡슐화가 깨져있고, 순회 방식이 Stack 과 달라 주의가 필요하다. ArrayDeque class 를 사용하는 것이 권장됨.
 */
class D13_ExclusiveTimeOfFunctions {

    public static void main(String[] args) {
        D13_ExclusiveTimeOfFunctions solution = new D13_ExclusiveTimeOfFunctions();

        TestRunner.run("Exclusive Time of Functions",
                input -> solution.exclusiveTime((int) input[0], Arrays.asList((String[]) input[1])),
                new TestCase<>(new Object[]{2, new String[]{"0:start:0", "1:start:2", "1:end:5", "0:end:6"}}, new int[]{3, 4}),
                new TestCase<>(new Object[]{1, new String[]{"0:start:0", "0:start:2", "0:end:5", "0:start:6", "0:end:6", "0:end:7"}}, new int[]{8}),
                new TestCase<>(new Object[]{2, new String[]{"0:start:0", "0:start:2", "0:end:5", "1:start:6", "1:end:6", "0:end:7"}}, new int[]{7, 1})
        );

    }

    private static class Log {
        int functionId;
        boolean isStart;
        int timestamp;

        public Log(String log) {
            String[] list = log.split(":");
            functionId = Integer.parseInt(list[0]);
            isStart = list[1].equals("start");
            timestamp = Integer.parseInt(list[2]);
        }
    }

    public int[] exclusiveTime(int n, List<String> logs) {
        int[] runningTimes = new int[n];
        Deque<Log> runningStack = new ArrayDeque<>();

        for (String log : logs) {
            Log l = new Log(log);
            if (l.isStart) {
                runningStack.push(l);
            } else {
                Log startLog = runningStack.pop();
                int runtime = l.timestamp - startLog.timestamp + 1;
                runningTimes[startLog.functionId] += runtime;
                if (!runningStack.isEmpty()) {
                    Log prevLog = runningStack.peek();
                    runningTimes[prevLog.functionId] -= runtime;
                }
            }
        }
        return runningTimes;
    }
}
