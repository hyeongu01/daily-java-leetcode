package y2026.m08;

import utils.TestCase;
import utils.TestRunner;

import java.util.Arrays;
import java.util.List;
import java.util.Stack;

class Log {
    int functionId;
    int type; // 0: start, 1: end
    int timestamp;

    public Log(String log) {
        String[] list = log.split(":");
        functionId = Integer.parseInt(list[0]);
        type = list[1].equals("start") ? 0 : 1;
        timestamp = Integer.parseInt(list[2]);
    }
}

/**
 * https://leetcode.com/problems/exclusive-time-of-functions/
 * <p>
 * 자료구조: Stack
 * 접근 방식: 괄호와 같이 end 신호가 오면 start 신호를 함께 제거하며 시간을 증/감
 */
public class D13_ExclusiveTimeOfFunctions {

    public static void main(String[] args) {
        D13_ExclusiveTimeOfFunctions solution = new D13_ExclusiveTimeOfFunctions();

        TestRunner.run("Exclusive Time of Functions",
                input -> solution.exclusiveTime((int) input[0], Arrays.asList((String[]) input[1])),
                new TestCase<>(new Object[]{2, new String[]{"0:start:0", "1:start:2", "1:end:5", "0:end:6"}}, new int[]{3, 4}),
                new TestCase<>(new Object[]{1, new String[]{"0:start:0", "0:start:2", "0:end:5", "0:start:6", "0:end:6", "0:end:7"}}, new int[]{8}),
                new TestCase<>(new Object[]{2, new String[]{"0:start:0", "0:start:2", "0:end:5", "1:start:6", "1:end:6", "0:end:7"}}, new int[]{7, 1})
        );

    }

    public int[] exclusiveTime(int n, List<String> logs) {
        int[] runningTimes = new int[n];
        Stack<Log> runningStack = new Stack<>();

        for (String log : logs) {
            Log l = new Log(log);

            // 새 작업이 들어오면 작업 스택에 추가
            // end 신호가 들어오면 스택의 작업을 제거하고 동작 시각을 기록 (괄호처럼 짝이 맞아야함.)
            // 이전 작업물이 있는 경우 중단된 시간만큼 runningTimes 를 감소
            if (l.type == 0) {
                runningStack.push(l);
            } else {
                Log startLog = runningStack.pop();
                int runtime = l.timestamp - startLog.timestamp + 1;
                runningTimes[startLog.functionId] += runtime;
                if (!runningStack.empty()) {
                    Log prevLog = runningStack.peek();
                    runningTimes[prevLog.functionId] -= runtime;
                }
            }
        }
        return runningTimes;
    }
}
