package y2026.m09;

import utils.TestCase;
import utils.TestRunner;

import java.util.ArrayDeque;
import java.util.Queue;

// https://leetcode.com/problems/dota2-senate/description/?envType=study-plan-v2&envId=leetcode-75
class D07_Dota2Senate {

    public static void main(String[] args) {
        D07_Dota2Senate solution = new D07_Dota2Senate();

        TestRunner.run("Dota2 Senate",
                solution::predictPartyVictory,
                new TestCase<>("RD", "Radiant"),
                new TestCase<>("RDD", "Dire")
        );
    }

    /**
     * 플레이어는 아래 작업중 하나를 한다.
     * 1. 자신 기준으로 오른쪽에 위치한 다른 소속의 플레이어의 권한을 박탈한다. (이 때 가장 왼쪽의 사람부터 박탈한다.)
     * 2. 자신 기준으로 왼쪽에 위치한 다른 소속의 플레이어의 권한을 박탈한다. (이 때 가장 왼쪽의 사람부터 박탈한다.)
     * 3. 승리를 선언한다.
     *
     * @param senate 플레이어 소속 문자열
     * @return 최적으로 두었을 때 승리하는 소속 - Radiant | Dire
     */
    public String predictPartyVictory(String senate) {
        Queue<Character> queue = new ArrayDeque<>();
        int numR = 0, numD = 0;
        for (char c: senate.toCharArray()) {
            if (c == 'R') {
                numR ++;
            } else {
                numD ++;
            }
            queue.add(c);
        }

        int bannedRCount = 0, bannedDCount = 0;
        while (true) {
            char current = queue.remove();
            if (current == 'R') {
                if (bannedRCount > 0) {
                    bannedRCount --;
                    numR --;
                    continue;
                }
                bannedDCount ++;
            }
            if (current == 'D') {
                if (bannedDCount > 0) {
                    bannedDCount --;
                    numD --;
                    continue;
                }
                bannedRCount ++;
            }
            queue.add(current);

            if (numR == 0) {
                return "Dire";
            } else if (numD == 0) {
                return "Radiant";
            }
        }
    }
}
