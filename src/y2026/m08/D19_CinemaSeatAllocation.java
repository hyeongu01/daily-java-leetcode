package y2026.m08;

import utils.TestCase;
import utils.TestRunner;

import java.util.Arrays;
import java.util.Comparator;

public class D19_CinemaSeatAllocation {

    public static void main(String[] args) {
        D19_CinemaSeatAllocation solution = new D19_CinemaSeatAllocation();

        TestRunner.run("Cinema Seat Allocation",
                input -> solution.maxNumberOfFamilies((int) input[0], (int[][]) input[1]),
                new TestCase<>(new Object[]{3, new int[][]{{1, 2}, {1, 3}, {1, 8}, {2, 6}, {3, 1}, {3, 10}}}, 4),
                new TestCase<>(new Object[]{2, new int[][]{{2, 1}, {1, 8}, {2, 6}}}, 2),
                new TestCase<>(new Object[]{4, new int[][]{{4, 3}, {1, 4}, {4, 6}, {1, 7}}}, 4)
        );
    }

    /**
     * ?? int[n][10] 이 메모리 초과 나서 byte[n] 으로 바꿨는데도 메모리초과?? -> O(n) 공간복잡도도 메모리초과??
     * -> reservedSeats 를 정렬해서 조회하자.
     */
//    public int maxNumberOfFamilies(int n, int[][] reservedSeats) {
//        int result = 0;
//        byte[] availableSeats = new byte[n];
//        Arrays.fill(availableSeats, (byte) 7); // 초기에는 모두 가능함 (0000 0111)
//
//        for (int [] seats : reservedSeats) {
//            if (seats[1] == 2 || seats[1] == 3) {
//                availableSeats[seats[0] - 1] = (byte) (((byte) availableSeats[seats[0] - 1]) & (byte) 3);
//                continue;
//            }
//            if (seats[1] == 5 || seats[1] == 4) {
//                availableSeats[seats[0] - 1] = (byte) (((byte) availableSeats[seats[0] - 1]) & (byte) 1);
//                continue;
//            }
//            if (seats[1] == 7 || seats[1] == 6) {
//                availableSeats[seats[0] - 1] = (byte) (((byte) availableSeats[seats[0] - 1]) & (byte) 4);
//                continue;
//            }
//            if (seats[1] == 9 || seats[1] == 8) {
//                availableSeats[seats[0] - 1] = (byte) (((byte) availableSeats[seats[0] - 1]) & (byte) 6);
//                continue;
//            }
//        }
//
//        for (byte seats: availableSeats) {
//            if (seats == 7) {
//                result += 2;
//                continue;
//            }
//            if (seats == 0) {
//                continue;
//            }
//            result += 1;
//        }
//        return result;
//    }

    /**
     * 시간복잡도: O(N) (N: reservedSeats.length)
     * 공간복잡도: O(1)
     */
    public int maxNumberOfFamilies(int n, int[][] reservedSeats) {
        Arrays.sort(reservedSeats, Comparator.comparingInt(a -> a[0]));
        int result = 0;
        int pointer = 0;
        int counter = 0;

        while (pointer < reservedSeats.length) {
            counter++;
            byte availability = 7;
            int current = reservedSeats[pointer][0];

            while (pointer < reservedSeats.length && reservedSeats[pointer][0] == current) {
                int col = reservedSeats[pointer][1];

                if (col == 2 || col == 3) {
                    availability = (byte) (availability & (byte) 3);
                }
                if (col == 4 || col == 5) {
                    availability = (byte) (availability & (byte) 1);
                }
                if (col == 6 || col == 7) {
                    availability = (byte) (availability & (byte) 4);
                }
                if (col == 8 || col == 9) {
                    availability = (byte) (availability & (byte) 6);
                }

                pointer++;
            }
            // result 계산
            if (availability == 7) {
                result += 2;
            } else if (availability > 0) {
                result += 1;
            }
        }
        result += (n - counter) * 2;
        return result;
    }
}
