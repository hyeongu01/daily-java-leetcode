package y2026.m08;

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

    void print() {
        System.out.println("---");
        System.out.println("functionId: " + functionId);
        System.out.println("type: " + type);
        System.out.println("timestamp: " + timestamp);
        System.out.println("---");
    }
}

public class D12_ExclusiveTimeOfFunctions {

    public static void main(String[] args) {

    }

    public int[] exclusiveTime(int n, List<String> logs) {
        int[] runningTimes = new int[n];
        Stack<Log> runningStack = new Stack<>();
        int t = 0;

        for (String log: logs) {
            Log l = new Log(log);

            if (l.type == 0) {
                if (!runningStack.empty()) {
                    Log processingLog = runningStack.peek();
                    runningTimes[processingLog.functionId] += l.timestamp - processingLog.timestamp;
                }
                runningStack.push(l);
            } else {
                Log startLog = runningStack.pop();
                runningTimes[startLog.functionId] += 1;
            }
        }
        return runningTimes;
    }
}
